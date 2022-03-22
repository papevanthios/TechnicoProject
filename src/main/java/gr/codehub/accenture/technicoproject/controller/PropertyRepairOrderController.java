package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.service.PropertyRepairOrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Property repair order controller with the endpoints for creation,
 * searching, updating and deleting of the properties.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/propertyRepairOrder")
public class PropertyRepairOrderController {
    private PropertyRepairOrderService propertyRepairOrderService;

    /**
     * Get an object of class PropertyRepairOrder from controller, an integer for the property id and creating
     * the object of PropertyRepairOrder into the service's repository.
     * @param propertyRepairOrder an object of class PropertyRepairOrder
     * @param propertyId an integer for property id
     * @return a response result object of propertyRepairOrder object of class ResponseResultDto
     */
    @PostMapping (value = "/property/{propertyId}")
    public ResponseResultDto<PropertyRepairOrder> createPropertyRepairOrder(@PathVariable("propertyId") int propertyId,
                                                                            @Valid @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.createPropertyRepairOrder(propertyRepairOrder,propertyId);
    }

    /**
     * Get an integer of property owner id and search it from the service's repository.
     * @param propertyOwnerId an integer of property owner id
     * @return a response result of a list of objects of class property repair order
     */
    @GetMapping(value = "/useId/{propertyOwnerId}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(@PathVariable("propertyOwnerId") int propertyOwnerId){
        return propertyRepairOrderService.searchByPropertyOwnerIdForPropertyRepairOrder(propertyOwnerId);
    }

    /**
     * Get a string of date and search it from service's repository.
     * @param firstDate a string of date
     * @return a response result of a list of objects of class property repair order
     */
    @GetMapping(value = "/date/{firstDate}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByDate(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate){
        return propertyRepairOrderService.searchByDate(firstDate);
    }

    /**
     * Get two strings of dates and makes a search for property repair orders into service's repository.
     * @param firstDate a string of date
     * @param secondDate a string of date
     * @return a response result of a list of objects of class property repair order
     */
    @GetMapping(value = "/rangeOfDates/{firstDate}/{secondDate}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate,
                                                          @PathVariable("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String secondDate){
        return propertyRepairOrderService.searchByRangeOfDates(firstDate, secondDate);
    }

    /**
     * Get an integer of property repair order id and search it from service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @return a response result of an object of PropertyRepairOrder
     */
    @GetMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<PropertyRepairOrder> searchByPropertyRepairOrderId(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId) {
        return propertyRepairOrderService.searchByPropertyRepairOrderId(propertyRepairOrderId);
    }

    /**
     * Get an integer of property repair order id, get an object of PropertyRepairOrder and update the property repair order into the service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @param propertyRepairOrder an object ofPropertyRepairOrder
     * @return a response result of an object of PropertyRepairOrder
     */
    @PutMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFields(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                               @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.updatePropertyRepairOrderFields(propertyRepairOrderId, propertyRepairOrder);
    }

    /**
     * Get an integer of property repair order id, get an object of PropertyRepairOrder, get an integer of property id
     * and update the property repair order into the service's repository.
     * @param propertyRepairOrderId an integer of property repair order id
     * @param propertyId an integer for property id
     * @param propertyRepairOrder an object ofPropertyRepairOrder
     * @return a response result of an object of PropertyRepairOrder
     */
    @PutMapping(value = "/{propertyRepairOrderId}/property/{propertyId}")
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFieldsAndProperty(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                                          @PathVariable("propertyId") int propertyId,
                                                                          @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.updatePropertyRepairOrderFieldsAndProperty(propertyRepairOrderId, propertyId, propertyRepairOrder);
    }

    /**
     * Get an integer of property repair order id and delete the property repair order from the service's repository.
     * @param propertyRepairOrderId  an integer of property repair order id
     * @return a response result of an object of Boolean
     */
    @DeleteMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<Boolean> deletePropertyRepairOrder(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId){
        return propertyRepairOrderService.deletePropertyRepairOrder(propertyRepairOrderId);
    }
}

