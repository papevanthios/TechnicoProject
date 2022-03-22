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

/**
 * This class implements the interface for the Service of the property owner.
 * <p>
 * The implementations create, search, update or delete a property owner according to the
 * request received from the REST-API.
 * </p>
 * <p>
 * throws ERROR in case of unhandled exceptions, e.g. error occurred by the database.
 */
@Service
@AllArgsConstructor
@Slf4j
public class PropertyOwnerServiceImpl implements PropertyOwnerService {
    private static final String LINE_DELIMITER = "---------------------------------------";
    private PropertyOwnerRepository propertyOwnerRepository;

    /**
     * Creates a property owner by the details inserted from the PropertyOwnerController.
     *
     * @param propertyOwner property owner to represent, not null
     * @return the property owner saved in the database, not null
     */
    @Override
    public ResponseResultDto<PropertyOwner> createPropertyOwner(PropertyOwner propertyOwner) {
        log.info("");
        log.info("Creating a property owner...");
        log.info(LINE_DELIMITER);

        try {// save the owner in database
            propertyOwnerRepository.save(propertyOwner);
        } catch (Exception e) {// in case of unhandled exception
            log.error("Exception enabled.");
            log.info(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Property owner successfully created.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner has been created.");
    }

    /**
     * Obtains the property owner by his VAT number.
     *
     * @param propertyOwnerVAT vat number of the property's owner to represent, exactly 9 digits
     * @return the owner, not null
     * throws ERROR if owner cannot be found
     */
    @Override
    public ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT) {
        log.info("");
        log.info("Searching property owner by VAT number...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {// looping through the database to find owner with specific vat number
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getVatNumber(), propertyOwnerVAT))
                    propertyOwner = propertyOwnerRep;
        } catch (Exception e) {// for an unhandled exception
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwner == null) {// if there is no such owner throw an error
            log.error("Property owner is null.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }
        log.info("Property owner was successfully found by VAT number.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    /**
     * Obtains the property owner by his email.
     *
     * @param propertyOwnerEmail the email of the property owner to represent, [...]@[...].[...]
     * @return the property owner
     * throws  ERROR if the owner cannot be found
     */
    @Override
    public ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail) {
        log.info("");
        log.info("Searching property owner by email...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {// looping through database to find owner with corresponding email
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getEmail(), propertyOwnerEmail))
                    propertyOwner = propertyOwnerRep;
        } catch (Exception e) {// for an unhandled exception
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }
        if (propertyOwner == null) {// if there is no such owner throw an error
            log.error("Property owner is null.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }
        log.info("Property owner was successfully found by email.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    /**
     * Obtains the property owner by his unique id.
     *
     * @param propertyOwnerId id of the owner to represent, from 1 to ...
     * @return the property owner
     * throws ERROR if the owner cannot be found
     */
    @Override
    public ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId) {
        log.info("");
        log.info("Searching property owner by his/her id...");
        log.info(LINE_DELIMITER);

        PropertyOwner propertyOwner = null;
        try {// looping through database to find owner with corresponding id number
            for (PropertyOwner propertyOwnerRep : propertyOwnerRepository.findAll())
                if (Objects.equals(propertyOwnerRep.getPropertyOwnerId(), propertyOwnerId))
                    propertyOwner = propertyOwnerRep;
        } catch (Exception e) {// for an unhandled exception
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwner == null) {// if no such owner exists throw an error
            log.error("Property owner was not found.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }
        log.info("Property owner was successfully found.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwner, ResponseStatus.SUCCESS, "The property owner was found.");
    }

    /**
     * Update the details of the property owner.
     * <p><ul>
     * <li>VAT number
     * <li>phone number
     * <li>username
     * <li>password
     * <li>email
     * <li>address
     * <li>first name
     * <li>last name
     * </ul></p>
     *
     * @param propertyOwnerId the id of the property owner, from 1 to ...
     * @param propertyOwner   the property owner with the desired details to update
     * @return the property owner with the updated details
     * throws ERROR for an unhandled exception or if no updates were found
     */
    @Override
    public ResponseResultDto<PropertyOwner> updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) {
        log.info("");
        log.info("Updating property owner's details...");
        log.info(LINE_DELIMITER);

        // check if all details of the owner are null, if yes throw an error
        if (propertyOwner.getVatNumber() == null &&
                propertyOwner.getPhoneNumber() == null &&
                propertyOwner.getPassword() == null &&
                propertyOwner.getUsername() == null &&
                propertyOwner.getEmail() == null &&
                propertyOwner.getAddress() == null &&
                propertyOwner.getFirstName() == null &&
                propertyOwner.getLastName() == null) {
            log.error("Null property owner.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.NO_UPDATES_FOUND, "You entered a null property owner.");
        }

        // If property owner is not null proceed
        Optional<PropertyOwner> propertyOwnerOpt;

        try {// find owner from database through the primary key
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        } catch (Exception e) {// for an unhandled exception
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.ERROR, "An error occurred.");
        }

        // in case there is no owner found from the primary key, throw a message
        if (propertyOwnerOpt.isEmpty()) {
            log.error("Property owner was not found.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner was not found.");
        }

        // Check every possible field for user input, and update it. If a field is inserted then set it.
        try {
            if (propertyOwner.getVatNumber() != null)// vat number
                propertyOwnerOpt.get().setVatNumber(propertyOwner.getVatNumber());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("VAT number is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The VAT number is incorrect.");
        }

        try {
            if (propertyOwner.getFirstName() != null)// first name
                propertyOwnerOpt.get().setFirstName(propertyOwner.getFirstName());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("First name is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The first name is incorrect.");
        }

        try {
            if (propertyOwner.getLastName() != null)// last name
                propertyOwnerOpt.get().setLastName(propertyOwner.getLastName());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Last name is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The last name is incorrect.");
        }

        try {
            if (propertyOwner.getAddress() != null)// address
                propertyOwnerOpt.get().setAddress(propertyOwner.getAddress());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Address is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The address is incorrect.");
        }

        try {
            if (propertyOwner.getPhoneNumber() != null)// phone number
                propertyOwnerOpt.get().setPhoneNumber(propertyOwner.getPhoneNumber());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Phone number is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The phone number is incorrect.");
        }

        try {
            if (propertyOwner.getEmail() != null)// email
                propertyOwnerOpt.get().setEmail(propertyOwner.getEmail());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Email is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The email is incorrect.");
        }

        try {
            if (propertyOwner.getUsername() != null)// username
                propertyOwnerOpt.get().setUsername(propertyOwner.getUsername());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Username is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The username is incorrect.");
        }

        try {
            if (propertyOwner.getPassword() != null)// password
                propertyOwnerOpt.get().setPassword(propertyOwner.getPassword());
        } catch (Exception e) {// if value cannot be set throw an error message
            log.error("Password is incorrect.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(null, ResponseStatus.PROPERTY_OWNER_INFORMATION_IS_INCORRECT, "The password is incorrect.");
        }

        try {
            propertyOwnerRepository.save(propertyOwnerOpt.get());// if input is valid then save to database
        } catch (Exception e) {// for an unhandled exception throw an error message
            log.error("Exception enabled.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.ERROR, "An error occurred.");
        }
        log.info("Details updated successfully.");
        log.info(LINE_DELIMITER);
        return new ResponseResultDto<>(propertyOwnerOpt.get(), ResponseStatus.SUCCESS, "The property owner's details have been updated.");
    }

    /**
     * Deletes the property owner.
     *
     * @param propertyOwnerId id of the property owner, from 1 to ...
     * @return boolean, true for successfull deletion
     * throws ERROR for an unhandled exception or if the owner was not found
     */
    @Override
    public ResponseResultDto<Boolean> deletePropertyOwner(int propertyOwnerId) {
        log.info("");
        log.info("Deleting property owner...");
        log.info(LINE_DELIMITER);

        Optional<PropertyOwner> propertyOwnerOpt;

        try {
            propertyOwnerOpt = propertyOwnerRepository.findById(propertyOwnerId);
        } catch (Exception e) {// for an unhandled exception throw an error message
            log.error("ERROR occurred.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
        }

        if (propertyOwnerOpt.isEmpty()) {// if no owner was found by the primary key
            log.error("Property owner not found.");
            log.error(LINE_DELIMITER);
            return new ResponseResultDto<>(false, ResponseStatus.PROPERTY_OWNER_NOT_FOUND, "The property owner cannot be found.");
        }
        // check if owner has no properties under his possession
        if (propertyOwnerOpt.get().getPropertyList().isEmpty()) {
            try {
                propertyOwnerRepository.delete(propertyOwnerOpt.get());// delete owner
                log.info("Successful deletion.");
                log.info(LINE_DELIMITER);
                return new ResponseResultDto<>(true, ResponseStatus.SUCCESS, "The property owner has been deleted.");
            } catch (Exception e) {// for an unhandled exception
                log.error("Exception enabled.");
                log.error(LINE_DELIMITER);
                return new ResponseResultDto<>(false, ResponseStatus.ERROR, "An error occurred.");
            }

        }// if owner has properties then he cannot be deleted, throw error message
        log.error("Property owner has properties.");
        log.error(LINE_DELIMITER);
        return new ResponseResultDto<>(false, ResponseStatus.ERROR, "Property owner has properties.");
    }
}
