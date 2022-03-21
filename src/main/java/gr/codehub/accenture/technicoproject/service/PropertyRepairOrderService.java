package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;

import java.util.List;

public interface PropertyRepairOrderService {

    ResponseResultDto<Boolean> createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId);

    ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId);

    List<PropertyRepairOrder> searchByDate(String firstDate) throws PropertyRepairOrderException;

    List<PropertyRepairOrder> searchByRangeOfDates(String firstDate, String secondDate) throws PropertyRepairOrderException;

    PropertyRepairOrder searchByPropertyRepairOrderId(int propertyRepairOrderId);

    PropertyRepairOrder updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException;

    PropertyRepairOrder updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException;

    boolean deletePropertyRepairOrder(int propertyRepairOrderId) throws PropertyRepairOrderException;
}
