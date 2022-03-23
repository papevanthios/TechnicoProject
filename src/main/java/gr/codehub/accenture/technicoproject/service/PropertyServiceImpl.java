package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of property service methods
 */
@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {
    private static final String LINE_DELIMITER = "---------------------------------------";
    private PropertyRepository propertyRepository;
    private PropertyOwnerRepository propertyOwnerRepository;

    /**
     * Creating property, checking the fields for null and
     * then saving it to the repository.
     *
     * @param property        property information
     * @param propertyOwnerId property owner ID
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Property> createProperty(Property property, int propertyOwnerId) {
        log.info("");
        log.info("Creating a property...");
        log.info(LINE_DELIMITER);

        Property propertyRep;
        propertyRep = propertyRepository.findByPropertyIdentificationNumber(property.getPropertyIdentificationNumber());
        if (propertyRep != null) {
            log.error("Property was already created.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "Property was already created with identificationNumber:" + property.getPropertyIdentificationNumber());
        }

        // Search property owner by ID.
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty()) {
            log.error("The property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }

        // Set property owner to the property, save it and return it.
        property.setPropertyOwner(propertyOwnerOpt.get());
        try {
            propertyRepository.save(property);
        } catch (Exception e) {
            log.error("An error occurred during saving into DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was created and saved into DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was created.");
    }

    /**
     * Searching property by property ID (if exists).
     *
     * @param propertyId property ID
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Property> searchPropertyByPropertyId(int propertyId) {
        log.info("");
        log.info("Searching a property by propertyId...");
        log.info(LINE_DELIMITER);

        // Search property by ID.
        Property property;
        try {
            property = propertyRepository.findByPropertyIdEquals(propertyId);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if property exists and if so it is returned.
        if (property == null) {
            log.error("Property was not found.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found with id:" + propertyId);
        }
        log.info("Property was found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was found.");
    }

    /**
     * Searching property by property identification number (if exists).
     *
     * @param propertyIdNumber property identification number
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Property> searchPropertyByPropertyIdNumber(long propertyIdNumber) {
        log.info("");
        log.info("Searching a property by propertyIdNumber...");
        log.info(LINE_DELIMITER);

        // Search property by property identification number.
        Property property;
        try {
            property = propertyRepository.findByPropertyIdentificationNumber(propertyIdNumber);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if property exists and if so it is returned.
        if (property == null) {
            log.error("Property was not found.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found with identificationNumber:" + propertyIdNumber);
        }
        log.info("Property was found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was found.");
    }

    /**
     * Searching properties by property owner's VAT number (if exist).
     *
     * @param propertyOwnerVAT property owner's VAT number
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<List<Property>> searchPropertyByVAT(int propertyOwnerVAT) {
        log.info("");
        log.info("Searching a property by property owner's VAT...");
        log.info(LINE_DELIMITER);

        // Search property by property owner's VAT number.
        List<Property> propertyList = new ArrayList<>();
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (Integer.parseInt(propertyRep.getPropertyOwner().getVatNumber()) == propertyOwnerVAT)
                    propertyList.add(propertyRep);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // Check if property exists and if so it is returned.
        if (propertyList.isEmpty()) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }
        log.info("Properties were found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyList, ResponseStatus.SUCCESS, "Properties were found.");
    }

    /**
     * Updating property fields (except for property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     *
     * @param propertyId property ID
     * @param property   property information
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Property> updatePropertyFields(int propertyId, Property property) {
        log.info("");
        log.info("Updating property fields...");
        log.info(LINE_DELIMITER);

        // Check if property information is null.
        if (property.getPropertyType() == null &&
                property.getPropertyAddress() == null &&
                property.getPropertyIdentificationNumber() == null &&
                property.getYearOfConstruction() == null) {
            log.error("You entered a null property.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property.");
        }

        // Check if property with propertyId exists.
        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyId);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOpt.isEmpty()) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }

        // Check every possible field for user input, and update it.
        try {
            if (property.getPropertyType() != null)
                propertyOpt.get().setPropertyType(property.getPropertyType());
        } catch (Exception e) {
            log.error("The property type is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_IS_INCORRECT, "The property type is incorrect.");
        }

        try {
            if (property.getPropertyAddress() != null)
                propertyOpt.get().setPropertyAddress(property.getPropertyAddress());
        } catch (Exception e) {
            log.error("The property address is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_IS_INCORRECT, "The property address is incorrect.");
        }

        try {
            if (property.getPropertyIdentificationNumber() != null)
                propertyOpt.get().setPropertyIdentificationNumber(property.getPropertyIdentificationNumber());
        } catch (Exception e) {
            log.error("The property identification number is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_IS_INCORRECT, "The property identification number is incorrect.");
        }

        try {
            if (property.getYearOfConstruction() != null)
                propertyOpt.get().setYearOfConstruction(property.getYearOfConstruction());
        } catch (Exception e) {
            log.error("The property year of construction is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_IS_INCORRECT, "The property year of construction is incorrect.");
        }

        // Save property into DB and return it.
        try {
            propertyRepository.save(propertyOpt.get());
        } catch (Exception e) {
            log.error("An error occurred during saving into DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was updated and saved into DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOpt.get(), ResponseStatus.SUCCESS, "Property was updated.");
    }

    /**
     * Updating property fields (including property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     *
     * @param propertyId      property ID
     * @param propertyOwnerId property owner ID
     * @param property        property information
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property) {
        log.info("");
        log.info("Updating property fields and property owner...");
        log.info(LINE_DELIMITER);

        // Check if property owner with propertyOwnerId exists.
        Optional<PropertyOwner> propertyOwnerOpt;
        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwnerOpt.isEmpty()) {
            log.error("Property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "Property owner was not found.");
        }

        // Check if property exists and update fields.
        ResponseResultDto<Property> propertyUpdDto = updatePropertyFields(propertyId, property);
        Property propertyUpd = propertyUpdDto.getData();
        if (propertyUpd == null)
            return new ResponseResultDto<>(null, propertyUpdDto.getStatus(), propertyUpdDto.getMessage());

        // Setting property owner, save it into DB and return it.
        try {
            propertyUpd.setPropertyOwner(propertyOwnerOpt.get());
            propertyRepository.save(propertyUpd);
        } catch (Exception e) {
            log.error("An error occurred during saving into DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was updated and saved into DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyUpd, ResponseStatus.SUCCESS, "Property was updated.");
    }

    /**
     * Deleting property if there is not any repair.
     *
     * @param propertyIdNumber property identification number
     * @return a response result with appropriate message
     */
    @Override
    public ResponseResultDto<Boolean> deleteProperty(int propertyIdNumber) {
        log.info("");
        log.info("Deleting a property...");
        log.info(LINE_DELIMITER);

        // Check if property with propertyIdNumber exists.
        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyIdNumber);
        } catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOpt.isEmpty()) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }

        // Check if the property has any repair and if not, delete the property.
        if (propertyOpt.get().getPropertyRepairOrderList().isEmpty()) {
            try {
                propertyRepository.delete(propertyOpt.get());
            } catch (Exception e) {
                log.error("An error occurred during deleting from DB.");
                log.info(LINE_DELIMITER);
                return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
            }
            log.info("Property was deleted.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "Property was deleted.");
        }
        log.info("Property has repairs.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_CANNOT_BE_DELETED, "Property has repairs.");
    }
}
