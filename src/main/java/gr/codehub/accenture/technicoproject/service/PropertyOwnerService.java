package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;

public interface PropertyOwnerService {
    // property owner create maria
    PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) throws PropertyOwnerException;

    // property owner search by vat maria
    PropertyOwner searchByVAT(int propertyOwnerVAT) throws PropertyOwnerException;

    // property owner search email maria
    PropertyOwner searchByEmail(String propertyOwnerEmail) throws PropertyOwnerException;

    // property owner update aris
    PropertyOwner updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) throws PropertyOwnerException;

    // property owner delete aris
    boolean deletePropertyOwner(int propertyOwnerId) throws PropertyOwnerException;
}
