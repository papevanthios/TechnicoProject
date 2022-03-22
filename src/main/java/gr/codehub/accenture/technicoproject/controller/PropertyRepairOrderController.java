package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyRepairOrder;
import gr.codehub.accenture.technicoproject.service.PropertyRepairOrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/propertyRepairOrder")
public class PropertyRepairOrderController {
    private PropertyRepairOrderService propertyRepairOrderService;

    @PostMapping (value = "/property/{propertyId}")
    public ResponseResultDto<PropertyRepairOrder> createPropertyRepairOrder(@PathVariable("propertyId") int propertyId,
                                                                            @Valid @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.createPropertyRepairOrder(propertyRepairOrder,propertyId);
    }

    @GetMapping(value = "/useId/{propertyOwnerId}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByPropertyOwnerIdForPropertyRepairOrder(@PathVariable("propertyOwnerId") int propertyOwnerId){
        return propertyRepairOrderService.searchByPropertyOwnerIdForPropertyRepairOrder(propertyOwnerId);
    }

    @GetMapping(value = "/date/{firstDate}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByDate(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate){
        return propertyRepairOrderService.searchByDate(firstDate);
    }

    @GetMapping(value = "/rangeOfDates/{firstDate}/{secondDate}")
    public ResponseResultDto<List<PropertyRepairOrder>> searchByRangeOfDates(@PathVariable("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate,
                                                          @PathVariable("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String secondDate){
        return propertyRepairOrderService.searchByRangeOfDates(firstDate, secondDate);
    }

    @GetMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<PropertyRepairOrder> searchByPropertyRepairOrderId(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId) {
        return propertyRepairOrderService.searchByPropertyRepairOrderId(propertyRepairOrderId);
    }

    @PutMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFields(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                               @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.updatePropertyRepairOrderFields(propertyRepairOrderId, propertyRepairOrder);
    }

    @PutMapping(value = "/{propertyRepairOrderId}/property/{propertyId}")
    public ResponseResultDto<PropertyRepairOrder> updatePropertyRepairOrderFieldsAndProperty(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId,
                                                                          @PathVariable("propertyId") int propertyId,
                                                                          @RequestBody PropertyRepairOrder propertyRepairOrder){
        return propertyRepairOrderService.updatePropertyRepairOrderFieldsAndProperty(propertyRepairOrderId, propertyId, propertyRepairOrder);
    }

    @DeleteMapping(value = "/{propertyRepairOrderId}")
    public ResponseResultDto<Boolean> deletePropertyRepairOrder(@PathVariable("propertyRepairOrderId") int propertyRepairOrderId){
        return propertyRepairOrderService.deletePropertyRepairOrder(propertyRepairOrderId);
    }
}

