package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyOwnerServiceImpl implements PropertyOwnerService {
    private PropertyOwnerRepository propertyOwnerRepository;

    @Override
    public ResponseResultDto<Boolean> createPropertyOwner(PropertyOwner propertyOwner) {
        if (propertyOwner == null ||
                propertyOwner.getVatNumber() == null)
            return new ResponseResultDto<Boolean>(false, ResponseStatus.PROPERTY_OWNER_NOT_CREATED, "The property owner was not created.");
        try {
            propertyOwnerRepository.save(propertyOwner);
        }
        catch(Exception e){
            return new ResponseResultDto<Boolean>(false, ResponseStatus.PROPERTY_OWNER_NOT_CREATED, "The property owner was not created.");
        }
        return new ResponseResultDto<Boolean>(true, ResponseStatus.SUCCESS, "The property owner has been created.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT){
        PropertyOwner propertyOwner = null;
        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getVatNumber(), propertyOwnerVAT))
                propertyOwner = propertyOwnerRep;

        if (propertyOwner == null)
            return new ResponseResultDto<PropertyOwner>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");

        return new ResponseResultDto<PropertyOwner>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail) {
        PropertyOwner propertyOwner = null;
        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getEmail(), propertyOwnerEmail))
                propertyOwner = propertyOwnerRep;
        if (propertyOwner == null)
            return new ResponseResultDto<PropertyOwner>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        return new ResponseResultDto<PropertyOwner>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId) {
        PropertyOwner propertyOwner = null;
        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getPropertyOwnerId(), propertyOwnerId))
                propertyOwner = propertyOwnerRep;

        if (propertyOwner == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");

        return new ResponseResultDto<PropertyOwner>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public PropertyOwner updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) throws PropertyOwnerException {
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            throw new PropertyOwnerException("The property owner cannot be found.");
        // Check every possible field for user input, and update it.
        try {
            if (propertyOwner.getVatNumber() != null)
                propertyOwnerOpt.get().setVatNumber(propertyOwner.getVatNumber());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The VAT number is incorrect.");
        }

        try {
            if (propertyOwner.getFirstName() != null)
                propertyOwnerOpt.get().setFirstName(propertyOwner.getFirstName());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The first name is incorrect.");
        }

        try {
            if (propertyOwner.getLastName() != null)
                propertyOwnerOpt.get().setLastName(propertyOwner.getLastName());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The last name is incorrect.");
        }

        try {
            if (propertyOwner.getAddress() != null)
                propertyOwnerOpt.get().setAddress(propertyOwner.getAddress());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The address is incorrect.");
        }

        try {
            if (propertyOwner.getPhoneNumber() != null)
                propertyOwnerOpt.get().setPhoneNumber(propertyOwner.getPhoneNumber());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The phone number is incorrect.");
        }

        try {
            if (propertyOwner.getEmail() != null)
                propertyOwnerOpt.get().setEmail(propertyOwner.getEmail());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The email is incorrect.");
        }

        try {
            if (propertyOwner.getUsername() != null)
                propertyOwnerOpt.get().setUsername(propertyOwner.getUsername());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The username is incorrect.");
        }

        try {
            if (propertyOwner.getPassword() != null)
                propertyOwnerOpt.get().setPassword(propertyOwner.getPassword());
        }
        catch (Exception e) {
            throw new PropertyOwnerException("The password is incorrect.");
        }

        return propertyOwnerRepository.save(propertyOwnerOpt.get());
    }

    @Override
    public boolean deletePropertyOwner(int propertyOwnerId) throws PropertyOwnerException {
        Optional<PropertyOwner> propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        if (propertyOwnerOpt.isEmpty())
            throw new PropertyOwnerException("The property owner cannot be found.");
        if (propertyOwnerOpt.get().getPropertyList().isEmpty()) {
            propertyOwnerRepository.delete(propertyOwnerOpt.get());
            return true;
        }
        throw new PropertyOwnerException("The property owner has properties.");
    }
}
