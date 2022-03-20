package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.exception.PropertyRepairOrderException;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.service.PropertyRepairOrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class PropertyRepairOrderController {
    private PropertyRepairOrderService propertyRepairOrderService;

    // PropertyRepairOrder Create by Manthos
    @PostMapping (value = "/propertyRepairOrder/propertyOwner/{propertyOwnerId}")
    public PropertyRepairOrder createPropertyRepairOrder(@PathVariable("propertyOwnerId") int propertyOwnerId, @RequestBody  PropertyRepairOrder  propertyRepairOrder) throws PropertyRepairOrderException {
        return propertyRepairOrderService.createPropertyRepairOrder(propertyRepairOrder, propertyOwnerId);
    }

    // PropertyRepairOrder Search1 by Manthos
    @GetMapping(value = "/propertyRepairOrder/useId/{propertyOwnerId}")
    public List<PropertyRepairOrder> searchByPropertyOwnerIdForPropertyRepairOrder(@PathVariable("propertyOwnerId") int propertyOwnerId) throws PropertyRepairOrderException {
        return propertyRepairOrderService.searchByPropertyOwnerIdForPropertyRepairOrder(propertyOwnerId);
    }

    // PropertyRepairOrder Search2 by Manthos
    @GetMapping(value = "/propertyRepairOrder/rangeOfDates/{firstDate}/{secondDate}")
    public List<PropertyRepairOrder> searchByRangeOfDates(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime firstDate, @PathVariable("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime secondDate) throws PropertyRepairOrderException {
        return propertyRepairOrderService.searchByRangeOfDates(firstDate, secondDate);
    }

    // PropertyRepairOrder Update by Manthos
    @PutMapping(value = "/propertyRepairOrder/{propertyRepairOrderId}")
    public PropertyRepairOrder updatePropertyRepairOrder(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId, @RequestBody PropertyRepairOrder propertyRepairOrder) throws PropertyRepairOrderException {
        return propertyRepairOrderService.updatePropertyRepairOrder(propertyRepairOrderId, propertyRepairOrder);
    }


    // PropertyRepairOrder Delete by Manthos
    @DeleteMapping(value = "/propertyRepairOrder/{propertyRepairOrderId}")
    public boolean deletePropertyRepairOrder(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId) throws PropertyRepairOrderException {
        return propertyRepairOrderService.deletePropertyRepairOrder(propertyRepairOrderId);
    }
}

