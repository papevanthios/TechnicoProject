package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairOrderService {

    PropertyRepairOrder createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId) throws PropertyRepairOrderException;

    List<PropertyRepairOrder> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId) throws PropertyRepairOrderException;

    List<PropertyRepairOrder> searchByRangeOfDates(String firstDate, String secondDate) throws PropertyRepairOrderException;

    PropertyRepairOrder updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException;

    PropertyRepairOrder updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException;

    boolean deletePropertyRepairOrder(int propertyRepairOrderId) throws PropertyRepairOrderException;
}
