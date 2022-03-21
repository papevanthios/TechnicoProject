package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import gr.codehub.accenture.technicoproject.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private PropertyOwnerRepository propertyOwnerRepository;

    @Override
    public ResponseResultDto<Property> createProperty(Property property, int propertyOwnerId) {
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        if (property == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "The property owner has no properties.");
        property.setPropertyOwner(propertyOwnerOpt.get());
        try {
            propertyRepository.save(property);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was created.");
    }

    @Override
    public ResponseResultDto<Property> searchPropertyByPropertyIdNumber(long propertyIdNumber) {
        Property property = null;
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (propertyRep.getPropertyIdentificationNumber() == propertyIdNumber)
                    property = propertyRep;
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (property == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        return new ResponseResultDto<>(property, ResponseStatus.SUCCESS, "Property was found.");
    }

    @Override
    public ResponseResultDto<List<Property>> searchPropertyByVAT(int propertyOwnerVAT) {
        List<Property> propertyList = new ArrayList<>();
        try {
            for (Property propertyRep : propertyRepository.findAll())
                if (Integer.parseInt(propertyRep.getPropertyOwner().getVatNumber()) == propertyOwnerVAT)
                    propertyList.add(propertyRep);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyList.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");
        return new ResponseResultDto<>(propertyList, ResponseStatus.SUCCESS, "Properties found.");
    }

    @Override
    public ResponseResultDto<Property> updatePropertyFields(int propertyId, Property property) {
        // Check if property is null
        if (    property.getPropertyType() == null &&
                property.getPropertyAddress() == null &&
                property.getPropertyIdentificationNumber() == null &&
                property.getYearOfConstruction() == null)
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property.");

        // Check if Property with propertyId exists.
        Optional<Property> propertyOpt;
        try {
            propertyOpt = propertyRepository.findById(propertyId);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_NOT_FOUND, "Property was not found.");

        // Check every possible field for user input, and update it.
        try {
            if (property.getPropertyType() != null)
                propertyOpt.get().setPropertyType(property.getPropertyType());
        } catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property type is incorrect.");
        }

        try {
            if (property.getPropertyAddress() != null)
                propertyOpt.get().setPropertyAddress(property.getPropertyAddress());
        } catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property address is incorrect.");
        }

        try {
            if (property.getPropertyIdentificationNumber() != null)
                propertyOpt.get().setPropertyIdentificationNumber(property.getPropertyIdentificationNumber());
        } catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property identification number is incorrect.");
        }

        try {
            if (property.getYearOfConstruction() != null)
                propertyOpt.get().setYearOfConstruction(property.getYearOfConstruction());
        } catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_INFORMATION_ARE_INCORRECT, "The property year of construction is incorrect.");
        }

        try {
            propertyRepository.save(propertyOpt.get());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        return new ResponseResultDto<>(propertyOpt.get(), ResponseStatus.SUCCESS, "Property was updated.");
    }

    @Override
    public ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property) {
        // Check if Property Owner exists.
        Optional<PropertyOwner> propertyOwnerOpt;
        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOwnerOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "Property owner was not found.");

        // Check if Property exists and Update Fields.
        ResponseResultDto<Property> propertyUpdDto = updatePropertyFields(propertyId, property);
        Property propertyUpd = propertyUpdDto.getData();
        if (propertyUpd == null)
            return new ResponseResultDto<>(null, propertyUpdDto.getStatus(), propertyUpdDto.getMessage());

        // Setting Property Owner.
        try {
            propertyUpd.setPropertyOwner(propertyOwnerOpt.get());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        return new ResponseResultDto<>(propertyUpd, ResponseStatus.SUCCESS, "Property was updated.");
    }

    @Override
    public boolean deleteProperty(int propertyIdNumber) throws PropertyException {
        Optional<Property> propertyDb = propertyRepository.findById(propertyIdNumber);
        if (propertyDb.isEmpty())
            throw new PropertyException("The property cannot be found.");
        if (propertyDb.get().getPropertyRepairOrderList().isEmpty()) {
            propertyRepository.delete(propertyDb.get());
            return true;
        }
        throw new PropertyException("The property has repairs.");
    }
}
