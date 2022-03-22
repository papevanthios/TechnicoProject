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

@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private PropertyOwnerRepository propertyOwnerRepository;

    private static final String LINE_DELIMITER = "---------------------------------------";

    /**
     * Creating property, checking the fields for null and then saving it to the repository.
     * @param property property information.
     * @param propertyOwnerId property owner ID.
     * @return a response result with appropriate message.
     */
    @Override
    public ResponseResultDto<Property> createProperty(Property property, int propertyOwnerId) {
        log.info("");
        log.info("Creating a property...");
        log.info(LINE_DELIMITER);

        // Search property owner by ID
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty()) {
            log.error("The property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }

        // Check if the property owner has properties
        if (property == null) {
            log.error("The property owner has no properties.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "The property owner has no properties.");
        }

        // Set property owner to the property and save it
        property.setPropertyOwner(propertyOwnerOpt.get());
        try {
            propertyRepository.save(property);
        }
        catch (Exception e) {
            log.error("An error occurred during saving in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was created and saved in DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was created.");
    }

    /**
     * @param propertyId
     * @return
     */
    @Override
    public ResponseResultDto<Property> searchPropertyByPropertyId(int propertyId) {
        log.info("");
        log.info("Searching a property by propertyId...");
        log.info(LINE_DELIMITER);

        Property property = null;
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (propertyRep.getPropertyId() == propertyId)
                    property = propertyRep;
        }
        catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (property == null) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }
        log.info("Property was found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was found.");
    }

    @Override
    public ResponseResultDto<Property> searchPropertyByPropertyIdNumber(long propertyIdNumber) {
        log.info("");
        log.info("Searching a property by propertyIdNumber...");
        log.info(LINE_DELIMITER);

        Property property = null;
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (propertyRep.getPropertyIdentificationNumber() == propertyIdNumber)
                    property = propertyRep;
        }
        catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (property == null) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }
        log.info("Property was found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was found.");
    }

    @Override
    public ResponseResultDto<List<Property>> searchPropertyByVAT(int propertyOwnerVAT) {
        log.info("");
        log.info("Searching a property by property owner's VAT...");
        log.info(LINE_DELIMITER);

        List<Property> propertyList = new ArrayList<>();
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (Integer.parseInt(propertyRep.getPropertyOwner().getVatNumber()) == propertyOwnerVAT)
                    propertyList.add(propertyRep);
        }
        catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyList.isEmpty()) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }
        log.info("Properties were found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyList, ResponseStatus.SUCCESS, "Properties were found.");
    }

    @Override
    public ResponseResultDto<Property> updatePropertyFields(int propertyId, Property property) {
        log.info("");
        log.info("Updating property fields...");
        log.info(LINE_DELIMITER);

        // Check if property is null
        if (    property.getPropertyType() == null &&
                property.getPropertyAddress() == null &&
                property.getPropertyIdentificationNumber() == null &&
                property.getYearOfConstruction() == null) {
            log.error("You entered a null property.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property.");
        }

        // Check if Property with propertyId exists.
        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyId);
        }
        catch (Exception e) {
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
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property type is incorrect.");
        }

        try {
            if (property.getPropertyAddress() != null)
                propertyOpt.get().setPropertyAddress(property.getPropertyAddress());
        }
        catch (Exception e) {
            log.error("The property address is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property address is incorrect.");
        }

        try {
            if (property.getPropertyIdentificationNumber() != null)
                propertyOpt.get().setPropertyIdentificationNumber(property.getPropertyIdentificationNumber());
        }
        catch (Exception e) {
            log.error("The property identification number is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property identification number is incorrect.");
        }

        try {
            if (property.getYearOfConstruction() != null)
                propertyOpt.get().setYearOfConstruction(property.getYearOfConstruction());
        }
        catch (Exception e) {
            log.error("The property year of construction is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property year of construction is incorrect.");
        }

        try {
            propertyRepository.save(propertyOpt.get());
        }
        catch (Exception e) {
            log.error("An error occurred during saving in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was updated and saved in DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOpt.get(), ResponseStatus.SUCCESS, "Property was updated.");
    }

    @Override
    public ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property) {
        log.info("");
        log.info("Updating property fields and property owner...");
        log.info(LINE_DELIMITER);

        // Check if Property Owner exists.
        Optional<PropertyOwner> propertyOwnerOpt;
        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOwnerOpt.isEmpty()) {
            log.error("Property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "Property owner was not found.");
        }

        // Check if Property exists and Update Fields.
        ResponseResultDto<Property> propertyUpdDto = updatePropertyFields(propertyId, property);
        Property propertyUpd = propertyUpdDto.getData();
        if (propertyUpd == null)
            return new ResponseResultDto<>(null, propertyUpdDto.getStatus(), propertyUpdDto.getMessage());

        // Setting Property Owner.
        try {
            propertyUpd.setPropertyOwner(propertyOwnerOpt.get());
            propertyRepository.save(propertyUpd);
        }
        catch (Exception e) {
            log.error("An error occurred during saving in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property was updated and saved in DB.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyUpd, ResponseStatus.SUCCESS, "Property was updated.");
    }

    @Override
    public ResponseResultDto<Boolean> deleteProperty(int propertyIdNumber) {
        log.info("");
        log.info("Deleting a property...");
        log.info(LINE_DELIMITER);

        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyIdNumber);
        }
        catch (Exception e) {
            log.error("An error occurred during searching in DB.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOpt.isEmpty()) {
            log.error("Property was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        }

        if (propertyOpt.get().getPropertyRepairOrderList().isEmpty()) {
            try {
                propertyRepository.delete(propertyOpt.get());
            }
            catch (Exception e) {
                log.error("An error occurred during deleting from DB.");
                log.info(LINE_DELIMITER);
                return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
            }
        }
        log.info("Property was deleted.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "Property was deleted.");
    }
}
