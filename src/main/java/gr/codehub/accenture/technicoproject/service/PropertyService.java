package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.Property;

import java.util.List;

/**
 * Declaration of property service methods
 */
public interface PropertyService {

    /**
     * Creating property, checking the fields for null and
     * then saving it to the repository.
     * @param property property information.
     * @param propertyOwnerId property owner ID.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Property> createProperty(Property property, int propertyOwnerId);

    /**
     * Searching property by property ID (if exists).
     * @param propertyId property ID.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Property> searchPropertyByPropertyId(int propertyId);

    /**
     * Searching property by property identification number (if exists).
     * @param propertyIdNumber property identification number.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Property> searchPropertyByPropertyIdNumber(long propertyIdNumber);

    /**
     * Searching properties by property owner's VAT number (if exist).
     * @param propertyOwnerVAT property owner's VAT number.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<List<Property>> searchPropertyByVAT(int propertyOwnerVAT);

    /**
     * Updating property fields (except for property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     * @param propertyId property ID.
     * @param property property information.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Property> updatePropertyFields(int propertyId, Property property);

    /**
     * Updating property fields (including property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     * @param propertyId property ID.
     * @param propertyOwnerId property owner ID.
     * @param property property information.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property);

    /**
     * Deleting property if there is not any repair.
     * @param propertyIdNumber property identification number.
     * @return a response result with appropriate message.
     */
    ResponseResultDto<Boolean> deleteProperty(int propertyIdNumber);
}
