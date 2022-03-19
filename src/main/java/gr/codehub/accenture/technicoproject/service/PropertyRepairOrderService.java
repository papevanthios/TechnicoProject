package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairOrderService {

    // property repair create manthos
    PropertyRepairOrder createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyOwnerId) throws PropertyRepairOrderException;

    // property repair search1 manthos
    List<PropertyRepairOrder> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId) throws PropertyRepairOrderException;

    // property repair search2 manthos
    List<PropertyRepairOrder> searchByRangeOfDates(LocalDateTime firstDate, LocalDateTime secondDate);

    // property repair update manthos
    PropertyRepairOrder updatePropertyRepairOrder(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException;

    // property repair delete manthos
    boolean deletePropertyRepairOrder(int propertyRepairOrderId) throws PropertyRepairOrderException;
}
