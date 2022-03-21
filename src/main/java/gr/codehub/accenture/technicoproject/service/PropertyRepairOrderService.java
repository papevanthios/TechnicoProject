package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;

import java.util.List;

public interface PropertyRepairOrderService {

    ResponseResultDto<PropertyRepairOrder> createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId);

    ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId);

    ResponseResultDto<List<PropertyRepairOrder>> searchByDate(String firstDate);

    ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(String firstDate, String secondDate);

    ResponseResultDto<PropertyRepairOrder> searchByPropertyRepairOrderId(int propertyRepairOrderId);

    ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder);

    ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder);

    ResponseResultDto<Boolean> deletePropertyRepairOrder(int propertyRepairOrderId);
}
