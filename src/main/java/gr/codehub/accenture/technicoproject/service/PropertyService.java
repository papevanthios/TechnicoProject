package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.model.Property;

import java.util.List;

public interface PropertyService {

    // property  create aris
    Property createProperty(Property property, int propertyOwnerId) throws PropertyException;

    // property  search1 aris
    Property searchPropertyByPropertyIdNumber(long propertyIdNumber) throws PropertyException;

    // property  search2 kostas
    List<Property> searchPropertyByVAT(int propertyOwnerVAT) throws PropertyException;

    // property  update kostas
    Property updateProperty(int propertyId, Property property) throws PropertyException;

    // property  delete kostas
    boolean deleteProperty(int propertyId) throws PropertyException;
}
