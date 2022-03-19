package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyOwnerServiceImpl implements PropertyOwnerService{
    private PropertyOwnerRepository propertyOwnerRepository;

    // Creating property Owner
    public PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) throws PropertyOwnerException {
        if (    propertyOwner == null ||
                propertyOwner.getVatNumber() == 0 ||
                propertyOwner.getFirstName() == null ||
                propertyOwner.getLastName() == null ||
                propertyOwner.getAddress() == null ||
                propertyOwner.getPhoneNumber() == null ||
                propertyOwner.getEmail() == null ||
                propertyOwner.getUsername() == null ||
                propertyOwner.getPassword() == null)
            throw new PropertyOwnerException("Missing Property Owner information.");
        return propertyOwnerRepository.save(propertyOwner);
    }

    // search by vat number: maria
    @Override
    public PropertyOwner searchByVAT(int propertyOwnerVAT) throws PropertyOwnerException {
        PropertyOwner propertyOwner = null;
        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (propertyOwnerRep.getVatNumber() == propertyOwnerVAT)
                propertyOwner = propertyOwnerRep;
        if (propertyOwner == null)
            throw new PropertyOwnerException("Property owner not found.");
        return propertyOwner;
    }
    // search by email: maria
    @Override
    public PropertyOwner searchByEmail(String propertyOwnerEmail) throws PropertyOwnerException {
        PropertyOwner propertyOwner = null;
        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getEmail(), propertyOwnerEmail))
                propertyOwner = propertyOwnerRep;
        if (propertyOwner == null)
            throw new PropertyOwnerException("Property owner not found.");
        return propertyOwner;
    }

    // UPDATE - ARIS (needs to be corrected.............)
    @Override
    public PropertyOwner updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) throws PropertyOwnerException {

        Optional<PropertyOwner> propertyOwnerDb = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerDb.isEmpty())
            throw new PropertyOwnerException("The property owner cannot be found.");
        propertyOwnerDb.get().setVatNumber(propertyOwner.getVatNumber());
//        propertyOwnerDb.get().setFirstName(propertyOwner.getFirstName());
//        propertyOwnerDb.get().setLastName(propertyOwner.getLastName());
//        propertyOwnerDb.get().setAddress(propertyOwner.getAddress());
//        propertyOwnerDb.get().setPhoneNumber(propertyOwner.getPhoneNumber());
//        propertyOwnerDb.get().setEmail(propertyOwner.getEmail());
//        propertyOwnerDb.get().setUsername(propertyOwner.getUsername());
//        propertyOwnerDb.get().setPassword(propertyOwner.getPassword());
        // what about updating propertyOwner's property/properties???
        return propertyOwnerRepository.save(propertyOwnerDb.get());
    }

    // DELETE - ARIS
    @Override
    public boolean deletePropertyOwner(int propertyOwnerId) throws PropertyOwnerException {
        Optional<PropertyOwner> propertyOwnerDb = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerDb.isEmpty())
            throw new PropertyOwnerException("The property owner cannot be found.");
        if (propertyOwnerDb.get().getProperty() == null) {
            propertyOwnerRepository.delete(propertyOwnerDb.get());
            return true;
        }
        throw new PropertyOwnerException("The property owner has properties.");
    }
}
