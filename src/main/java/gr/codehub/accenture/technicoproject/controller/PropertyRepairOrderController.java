package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.service.PropertyRepairOrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PropertyRepairOrderController {
    private PropertyRepairOrderService propertyRepairOrderService;

    @PostMapping (value = "/propertyRepairOrder/property/{propertyId}")
    public PropertyRepairOrder createPropertyRepairOrder(@PathVariable("propertyId") int propertyId,
                                                         @RequestBody PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        return propertyRepairOrderService.createPropertyRepairOrder(propertyRepairOrder,propertyId);
    }

    @GetMapping(value = "/propertyRepairOrder/useId/{propertyOwnerId}")
    public List<PropertyRepairOrder> searchByPropertyOwnerIdForPropertyRepairOrder(@PathVariable("propertyOwnerId") int propertyOwnerId) throws PropertyRepairOrderException {
        return propertyRepairOrderService.searchByPropertyOwnerIdForPropertyRepairOrder(propertyOwnerId);
    }

    @GetMapping(value = "/propertyRepairOrder/date/{firstDate}")
    public List<PropertyRepairOrder> searchByDate(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate) throws PropertyRepairOrderException {
        return propertyRepairOrderService.searchByDate(firstDate);
    }

    @GetMapping(value = "/propertyRepairOrder/rangeOfDates/{firstDate}/{secondDate}")
    public List<PropertyRepairOrder> searchByRangeOfDates(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate,
                                                          @PathVariable("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String secondDate) throws PropertyRepairOrderException {
        return propertyRepairOrderService.searchByRangeOfDates(firstDate, secondDate);
    }

    @PutMapping(value = "/propertyRepairOrder/{propertyRepairOrderId}")
    public PropertyRepairOrder updatePropertyRepairOrderFields(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                               @RequestBody PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        return propertyRepairOrderService.updatePropertyRepairOrderFields(propertyRepairOrderId, propertyRepairOrder);
    }

    @PutMapping(value = "/propertyRepairOrder/{propertyRepairOrderId}/property/{propertyId}")
    public PropertyRepairOrder updatePropertyRepairOrderFieldsAndProperty(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                                          @PathVariable("propertyId") int propertyId,
                                                                          @RequestBody PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        return propertyRepairOrderService.updatePropertyRepairOrderFieldsAndProperty(propertyRepairOrderId, propertyId, propertyRepairOrder);
    }

    @DeleteMapping(value = "/propertyRepairOrder/{propertyRepairOrderId}")
    public boolean deletePropertyRepairOrder(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId) throws PropertyRepairOrderException {
        return propertyRepairOrderService.deletePropertyRepairOrder(propertyRepairOrderId);
    }
}

