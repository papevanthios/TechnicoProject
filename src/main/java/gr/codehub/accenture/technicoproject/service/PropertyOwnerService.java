package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;

public interface PropertyOwnerService {

    ResponseResultDto<PropertyOwner> createPropertyOwner(PropertyOwner propertyOwner);

    ResponseResultDto<PropertyOwner> searchByVAT(String propertyOwnerVAT);

    ResponseResultDto<PropertyOwner> searchByEmail(String propertyOwnerEmail);

    ResponseResultDto<PropertyOwner> searchByPropertyOwnerId(int propertyOwnerId);

    ResponseResultDto<PropertyOwner> updatePropertyOwner(int propertyOwnerId, PropertyOwner propertyOwner);

    ResponseResultDto<Boolean> deletePropertyOwner(int propertyOwnerId);
}
