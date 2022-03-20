package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
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
        if (property == null ||
                property.getPropertyIdentificationNumber() == 0 ||
                property.getPropertyAddress() == null ||
                property.getPropertyType() == null ||
                property.getYearOfConstruction() == 0)
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

    // update property
    @Override
    public Property updateProperty(int propertyId, Property property) throws PropertyException {
        return null;
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
