package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.model.Property;

import java.util.List;

public interface PropertyService {

    Property createProperty(Property property, int propertyOwnerId) throws PropertyException;

    Property searchPropertyByPropertyIdNumber(long propertyIdNumber) throws PropertyException;

    List<Property> searchPropertyByVAT(int propertyOwnerVAT) throws PropertyException;

    // property  update kostas
    Property updateProperty(int propertyId, Property property) throws PropertyException;

    boolean deleteProperty(int propertyId) throws PropertyException;
}
