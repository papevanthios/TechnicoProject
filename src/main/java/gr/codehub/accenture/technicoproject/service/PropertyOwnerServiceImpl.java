package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
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
    public ResponseResultDto<PropertyOwner> createPropertyOwner(PropertyOwner propertyOwner) {
        if (    propertyOwner == null ||
                propertyOwner.getVatNumber() == null)
        {    return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_CREATED, "The property owner was not created.");}

        try {
            propertyOwnerRepository.save(propertyOwner);
        }
        catch(Exception e){
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner has been created.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT){
        PropertyOwner propertyOwner = null;

        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getVatNumber(), propertyOwnerVAT))
                propertyOwner = propertyOwnerRep;

        if (propertyOwner == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");

        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail) {
        PropertyOwner propertyOwner = null;

        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getEmail(), propertyOwnerEmail))
                propertyOwner = propertyOwnerRep;
        if (propertyOwner == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId) {
        PropertyOwner propertyOwner = null;

        for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
            if (Objects.equals(propertyOwnerRep.getPropertyOwnerId(), propertyOwnerId))
                propertyOwner = propertyOwnerRep;

        if (propertyOwner == null)
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");

        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) {
        // Check if property owner is null
        if (    propertyOwner.getVatNumber() == null &&
                propertyOwner.getPhoneNumber() == null &&
                propertyOwner.getPassword() == null &&
                propertyOwner.getUsername() == null &&
                propertyOwner.getEmail() == null &&
                propertyOwner.getAddress() == null &&
                propertyOwner.getFirstName() == null &&
                propertyOwner.getLastName() == null)
        { return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property owner.");}
        // If property owner is not null proceed
        Optional<PropertyOwner> propertyOwnerOpt;

        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch (Exception e){
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        // Check if property owner is empty
        if (propertyOwnerOpt.isEmpty())
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");

        // Check every possible field for user input, and update it.
        try {
            if (propertyOwner.getVatNumber() != null)
                propertyOwnerOpt.get().setVatNumber(propertyOwner.getVatNumber());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The VAT number is incorrect.");
        }

        try {
            if (propertyOwner.getFirstName() != null)
                propertyOwnerOpt.get().setFirstName(propertyOwner.getFirstName());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The first name is incorrect.");
        }

        try {
            if (propertyOwner.getLastName() != null)
                propertyOwnerOpt.get().setLastName(propertyOwner.getLastName());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The last name is incorrect.");
        }

        try {
            if (propertyOwner.getAddress() != null)
                propertyOwnerOpt.get().setAddress(propertyOwner.getAddress());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The address is incorrect.");
        }

        try {
            if (propertyOwner.getPhoneNumber() != null)
                propertyOwnerOpt.get().setPhoneNumber(propertyOwner.getPhoneNumber());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The phone number is incorrect.");
        }

        try {
            if (propertyOwner.getEmail() != null)
                propertyOwnerOpt.get().setEmail(propertyOwner.getEmail());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The email is incorrect.");
        }

        try {
            if (propertyOwner.getUsername() != null)
                propertyOwnerOpt.get().setUsername(propertyOwner.getUsername());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The username is incorrect.");
        }

        try {
            if (propertyOwner.getPassword() != null)
                propertyOwnerOpt.get().setPassword(propertyOwner.getPassword());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The password is incorrect.");
        }

        try {
            propertyOwnerRepository.save(propertyOwnerOpt.get());
        }
        catch (Exception e) {
            return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.ERROR, "An error occurred.");
        }

        return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.SUCCESS, "The property owner's details have been updated.");
    }

    @Override
    public ResponseResultDto<Boolean> deletePropertyOwner(int propertyOwnerId) {

        Optional<PropertyOwner> propertyOwnerOpt;

        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch(Exception e) {
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwnerOpt.isEmpty())
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner cannot be found.");

        if (propertyOwnerOpt.get().getPropertyList().isEmpty()) {
            try {
                propertyOwnerRepository.delete(propertyOwnerOpt.get());
            }
            catch(Exception e){
                return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
            }

        }
        return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "The property owner has been deleted.");
    }
}
