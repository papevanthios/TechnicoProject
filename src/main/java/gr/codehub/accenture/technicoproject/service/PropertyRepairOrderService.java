package gr.codehub.accenture.technicoproject.service;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;

import java.util.List;

/**
 * This interface declares all basic functionality for property repair order services.
 *
 * Services:
 *      Create property repair order.
 *      Search property repair orders with property owner id.
 *      Search property repair orders by date.
 *      Search property repair orders by range of dates.
 *      Search property repair order by property repair order id.
 *      Update property repair order fields.
 *      Update property repair order fields and property.
 *      Delete property repair order.
 */
public interface PropertyRepairOrderService {

    /**
     * Get an object of class PropertyRepairOrder from controller, an integer for the property id and creating
     * the object of PropertyRepairOrder into the service's repository.
     * @param propertyRepairOrder an object of class PropertyRepairOrder
     * @param propertyId an integer for property id
     * @return a response result object of propertyRepairOrder object of class ResponseResultDto
     */
    ResponseResultDto<PropertyRepairOrder> createPropertyRepairOrder(PropertyRepairOrder propertyRepairOrder, int propertyId);

    /**
     * Get an integer of property owner id and search it from the service's repository.
     * @param propertyOwnerId an integer of property owner id
     * @return a response result of a list of objects of class property repair order
     */
    ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(int propertyOwnerId);

    /**
     * Get a string of date and search it from service's repository.
     * @param firstDate a string of date
     * @return a response result of a list of objects of class property repair order
     */
    ResponseResultDto<List<PropertyRepairOrder>> searchByDate(String firstDate);

    /**
     * Get two strings of dates and makes a search for property repair orders into service's repository.
     * @param firstDate a string of date
     * @param secondDate a string of date
     * @return a response result of a list of objects of class property repair order
     */
    ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(String firstDate, String secondDate);

    /**
     * Get an integer of property repair order id and search it from service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @return a response result of an object of PropertyRepairOrder
     */
    ResponseResultDto<PropertyRepairOrder> searchByPropertyRepairOrderId(int propertyRepairOrderId);

    /**
     * Get an integer of property repair order id, get an object of PropertyRepairOrder and update the property repair order into the service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @param propertyRepairOrder an object ofPropertyRepairOrder
     * @return a response result of an object of PropertyRepairOrder
     */
    ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFields(int propertyRepairOrderId, PropertyRepairOrder propertyRepairOrder);

    /**
     * Get an integer of property repair order id, get an object of PropertyRepairOrder, get an integer of property id
     * and update the property repair order into the service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @param propertyId an integer for property id
     * @param propertyRepairOrder an object ofPropertyRepairOrder
     * @return a response result of an object of PropertyRepairOrder
     */
    ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFieldsAndProperty(int propertyRepairOrderId, int propertyId, PropertyRepairOrder propertyRepairOrder);

    /**
     * Get an integer of property repair order id and delete the property repair order from the service's repository.
     * @param propertyRepairOrderId  an integer of property repair order id
     * @return a response result of an object of Boolean
     */
    ResponseResultDto<Boolean> deletePropertyRepairOrder(int propertyRepairOrderId);
}
