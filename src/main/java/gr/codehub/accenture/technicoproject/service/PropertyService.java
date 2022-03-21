package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.model.Property;

import java.util.List;

public interface PropertyService {

    ResponseResultDto<Property> createProperty(Property property, int propertyOwnerId);

    Property searchPropertyByPropertyIdNumber(long propertyIdNumber) throws PropertyException;

    List<Property> searchPropertyByVAT(int propertyOwnerVAT) throws PropertyException;

    Property updatePropertyFields(int propertyId, Property property) throws PropertyException;

    Property updatePropertyFieldsAndPropertyOwner(int propertyId, int propertyOwnerId, Property property) throws PropertyException;

    boolean deleteProperty(int propertyId) throws PropertyException;
}
