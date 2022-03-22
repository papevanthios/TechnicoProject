package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {
    private PropertyService propertyService;

    @PostMapping("/propertyOwner/{propertyOwnerId}")
    public ResponseResultDto<Property> createProperty(@Valid @RequestBody Property property,
                                                     @PathVariable ("propertyOwnerId") int propertyOwnerId) {
        return propertyService.createProperty(property, propertyOwnerId);
    }

    @GetMapping("/propertyIdNumber/{propertyIdNumber}")
    public ResponseResultDto<Property> searchPropertyByPropertyIdNumber(@PathVariable("propertyIdNumber") long propertyIdNumber) {
        return propertyService.searchPropertyByPropertyIdNumber(propertyIdNumber);
    }

    @GetMapping("/vatNumber/{propertyOwnerVAT}")
    public ResponseResultDto<List<Property>> searchPropertyByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT) {
        return propertyService.searchPropertyByVAT(propertyOwnerVAT);
    }

    @PutMapping("/{propertyId}")
    public ResponseResultDto<Property> updatePropertyFields(@PathVariable("propertyId") int propertyId,
                                         @RequestBody Property property) {
        return propertyService.updatePropertyFields(propertyId, property);
    }

    @PutMapping("/{propertyId}/propertyOwner/{propertyOwnerId}")
    public ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(@PathVariable("propertyId") int propertyId,
                                                         @PathVariable("propertyOwnerId") int propertyOwnerId,
                                                         @RequestBody Property property) {
        return propertyService.updatePropertyFieldsAndPropertyOwner(propertyId, propertyOwnerId, property);
    }

    @DeleteMapping("/{propertyIdNumber}")
    public ResponseResultDto<Boolean> deletePropertyOwner(@PathVariable("propertyIdNumber") int propertyIdNumber) {
        return propertyService.deleteProperty(propertyIdNumber);
    }
}
