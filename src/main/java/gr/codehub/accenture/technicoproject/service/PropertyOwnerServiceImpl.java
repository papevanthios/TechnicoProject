package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.repository.PropertyOwnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyOwnerServiceImpl implements PropertyOwnerService {
    private PropertyOwnerRepository propertyOwnerRepository;

    private static final String LINE_DELIMITER = "---------------------------------------";

    @Override
    public ResponseResultDto<PropertyOwner> createPropertyOwner(PropertyOwner propertyOwner) {
        log.info("");
        log.info("Creating a property owner...");
        log.info(LINE_DELIMITER);

        try {
            propertyOwnerRepository.save(propertyOwner);
        }
        catch(Exception e) {
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property owner successfully created.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner has been created.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT){
        log.info("");
        log.info("Searching property owner by VAT number...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getVatNumber(), propertyOwnerVAT))
                    propertyOwner = propertyOwnerRep;
        } catch(Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwner == null) {
            log.info("Property owner is null.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }
        log.info("Property owner was successfully found by VAT number.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail) {
        log.info("");
        log.info("Searching property owner by email...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getEmail(), propertyOwnerEmail))
                    propertyOwner = propertyOwnerRep;
        } catch(Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOwner == null) {
            log.info("Property owner is null.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }
        log.info("Property owner was successfully found by email.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId) {
        log.info("");
        log.info("Searching property owner by his/her id...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getPropertyOwnerId(), propertyOwnerId))
                    propertyOwner = propertyOwnerRep;
        }
        catch(Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwner == null) {
            log.info("Property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
         }
        log.info("Property owner was successfully found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    @Override
    public ResponseResultDto<PropertyOwner> updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) {
        log.info("");
        log.info("Updating property owner's details...");
        log.info(LINE_DELIMITER);
        // Check if property owner is null
        if (    propertyOwner.getVatNumber() == null &&
                propertyOwner.getPhoneNumber() == null &&
                propertyOwner.getPassword() == null &&
                propertyOwner.getUsername() == null &&
                propertyOwner.getEmail() == null &&
                propertyOwner.getAddress() == null &&
                propertyOwner.getFirstName() == null &&
                propertyOwner.getLastName() == null)
        {
            log.info("Null property owner.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property owner.");
        }
        // If property owner is not null proceed
        Optional<PropertyOwner> propertyOwnerOpt;

        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch (Exception e){
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        // Check if property owner is empty
        if (propertyOwnerOpt.isEmpty()) {
            log.info("Property owner was not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }

        // Check every possible field for user input, and update it.
        try {
            if (propertyOwner.getVatNumber() != null)
                propertyOwnerOpt.get().setVatNumber(propertyOwner.getVatNumber());
        }
        catch (Exception e) {
            log.info("VAT number is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The VAT number is incorrect.");
        }

        try {
            if (propertyOwner.getFirstName() != null)
                propertyOwnerOpt.get().setFirstName(propertyOwner.getFirstName());
        }
        catch (Exception e) {
            log.info("First name is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The first name is incorrect.");
        }

        try {
            if (propertyOwner.getLastName() != null)
                propertyOwnerOpt.get().setLastName(propertyOwner.getLastName());
        }
        catch (Exception e) {
            log.info("Last name is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The last name is incorrect.");
        }

        try {
            if (propertyOwner.getAddress() != null)
                propertyOwnerOpt.get().setAddress(propertyOwner.getAddress());
        }
        catch (Exception e) {
            log.info("Address is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The address is incorrect.");
        }

        try {
            if (propertyOwner.getPhoneNumber() != null)
                propertyOwnerOpt.get().setPhoneNumber(propertyOwner.getPhoneNumber());
        }
        catch (Exception e) {
            log.info("Phone number is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The phone number is incorrect.");
        }

        try {
            if (propertyOwner.getEmail() != null)
                propertyOwnerOpt.get().setEmail(propertyOwner.getEmail());
        }
        catch (Exception e) {
            log.info("Email is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The email is incorrect.");
        }

        try {
            if (propertyOwner.getUsername() != null)
                propertyOwnerOpt.get().setUsername(propertyOwner.getUsername());
        }
        catch (Exception e) {
            log.info("Username is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The username is incorrect.");
        }

        try {
            if (propertyOwner.getPassword() != null)
                propertyOwnerOpt.get().setPassword(propertyOwner.getPassword());
        }
        catch (Exception e) {
            log.info("Password is incorrect.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_ARE_INCORRECT, "The password is incorrect.");
        }

        try {
            propertyOwnerRepository.save(propertyOwnerOpt.get());
        }
        catch (Exception e) {
            log.info("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Details updated successfully.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.SUCCESS, "The property owner's details have been updated.");
    }

    @Override
    public ResponseResultDto<Boolean> deletePropertyOwner(int propertyOwnerId) {
        log.info("");
        log.info("Deleting property owner...");
        log.info(LINE_DELIMITER);

        Optional<PropertyOwner> propertyOwnerOpt;

        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        }
        catch(Exception e) {
            log.info("ERROR occured.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwnerOpt.isEmpty()) {
            log.info("Property owner not found.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner cannot be found.");
        }

        if (propertyOwnerOpt.get().getPropertyList().isEmpty()) {
            try {
                propertyOwnerRepository.delete(propertyOwnerOpt.get());
                log.info("Successful deletion.");
                log.info(LINE_DELIMITER);
                return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "The property owner has been deleted.");
            }
            catch(Exception e){
                log.info("Exception enabled.");
                log.info(LINE_DELIMITER);
                return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
            }

        }
        log.info("Property owner has properties.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(false, ResponseStatus.ERROR, "Property owner has properties.");
    }
}
