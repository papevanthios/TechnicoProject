package gr.codehub.accenture.technicoproject.service;

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
    public Property createProperty(Property property, int propertyOwnerId) throws PropertyException {
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            throw new PropertyException("The property owner does not exist");
        if (property == null)
            throw new PropertyException("Missing Property information.");
        property.setPropertyOwner(propertyOwnerOpt.get());
        return propertyRepository.save(property);
    }

    @Override
    public Property searchPropertyByPropertyIdNumber(long propertyIdNumber) throws PropertyException {
        Property property = null;
        for (Property propertyRep : propertyRepository.findAll())
            if (propertyRep.getPropertyIdentificationNumber() == propertyIdNumber)
                property = propertyRep;
        if (property == null)
            throw new PropertyException("Property not found.");
        return property;
    }

    @Override
    public List<Property> searchPropertyByVAT(int propertyOwnerVAT) throws PropertyException {
        List<Property> property = new ArrayList<>();
        for (Property propertyRep : propertyRepository.findAll())
            if (Integer.parseInt(propertyRep.getPropertyOwner().getVatNumber()) == propertyOwnerVAT)
                property.add(propertyRep);
        if (property.isEmpty())
            throw new PropertyException("Property not found.");
        return property;
    }

    @Override
    public Property updatePropertyFields(int propertyId, Property property) throws PropertyException {
        // Check if Property exists.
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (propertyOpt.isEmpty())
            throw new PropertyException("The Property can not be found.");

        // Check every possible field for user input, and update it.
        try {
            if (property.getPropertyType() != null)
                propertyOpt.get().setPropertyType(property.getPropertyType());
        } catch (Exception e) {
            throw new PropertyException("The Property Type is incorrect.");
        }

        try {
            if (property.getPropertyAddress() != null)
                propertyOpt.get().setPropertyAddress(property.getPropertyAddress());
        } catch (Exception e) {
            throw new PropertyException("The Address is incorrect.");
        }

        try {
            if (property.getPropertyIdentificationNumber() != null)
                propertyOpt.get().setPropertyIdentificationNumber(property.getPropertyIdentificationNumber());
        } catch (Exception e) {
            throw new PropertyException("The repair type is incorrect.");
        }

        try {
            if (property.getYearOfConstruction() != null)
                propertyOpt.get().setYearOfConstruction(property.getYearOfConstruction());
        } catch (Exception e) {
            throw new PropertyException("The cost of repair is incorrect.");
        }

        return propertyRepository.save(propertyOpt.get());
    }

    @Override
    public Property updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property) throws PropertyException {
        // Check if Property Owner exists.
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            throw new PropertyException("The Property Owner does not exist.");

        // Check if Property exists and Update Fields.
        Property propertyUpd = updatePropertyFields(propertyId, property);

        // Setting Property Owner.
        propertyUpd.setPropertyOwner(propertyOwnerOpt.get());
        return propertyRepository.save(propertyUpd);
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
