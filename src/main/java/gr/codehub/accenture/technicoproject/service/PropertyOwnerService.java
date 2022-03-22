package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;

/**
 * This is the interface for the Services related to the owner.
 * Here we define the CRUD operations related to the controller of the owner's page.
 */
public interface PropertyOwnerService {

    /**
     * Creates an owner by the user input through the controller.
     *
     * @param propertyOwner  the owner, not null
     * @return the owner data, status of request and a message
     */
    ResponseResultDto<PropertyOwner> createPropertyOwner(PropertyOwner propertyOwner);

    /**
     * Obtains the owner by his VAT number.
     *
     * @param propertyOwnerVAT  the VAT number of the owner, 9-digits number
     * @return  the owner data, status of request and a message
     */
    ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT);

    /**
     * Obtains the owner by his email.
     *
     * @param propertyOwnerEmail  the owner's email, []@[].[]
     * @return  owner's data, status of request and a message
     */
    ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail);

    /**
     * Obtains the owner by his id primary key in the database.
     *
     * @param propertyOwnerId  the owner's id primary key, from 1 to ...
     * @return  owner's data, status of request and a message
     */
    ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId);

    /**
     * Updates the owner's details. Can be one or more at a time.
     *
     * @param propertyOwnerId  the owner's id primary key, from 1 to ...
     * @param propertyOwner  owner's updated data from the controller
     * @return  updated owner's details, status of request and a message
     */
    ResponseResultDto<PropertyOwner> updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner);

    /**
     * Deletes the owner from the database.
     *
     * @param propertyOwnerId  owner's id primary key, from 1 to ...
     * @return  boolean, true in case of deletion otherwise returns false
     */
    ResponseResultDto<Boolean> deletePropertyOwner(int propertyOwnerId);
}
