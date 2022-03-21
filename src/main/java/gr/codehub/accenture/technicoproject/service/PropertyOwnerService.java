package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;

public interface PropertyOwnerService {

    PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) throws PropertyOwnerException;

    PropertyOwner searchByVAT(String propertyOwnerVAT) throws PropertyOwnerException;

    PropertyOwner searchByEmail(String propertyOwnerEmail) throws PropertyOwnerException;

    PropertyOwner updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) throws PropertyOwnerException;

    boolean deletePropertyOwner(int propertyOwnerId) throws PropertyOwnerException;
}
