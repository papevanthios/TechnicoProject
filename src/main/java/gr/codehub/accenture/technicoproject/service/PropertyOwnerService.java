package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;

public interface PropertyOwnerService {

    ResponseResultDto<Boolean> createPropertyOwner(PropertyOwner propertyOwner);//done

    ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT);// done

    ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail);// done

    ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId); // done

    PropertyOwner updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner) throws PropertyOwnerException;

    boolean deletePropertyOwner(int propertyOwnerId) throws PropertyOwnerException;
}
