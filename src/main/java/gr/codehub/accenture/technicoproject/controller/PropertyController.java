package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.exception.PropertyException;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PropertyController {
    private PropertyService propertyService;

    @PostMapping(value = "/property/propertyOwner/{propertyOwnerId}")
    public Property createProperty(@RequestBody Property property,
                                   @PathVariable ("propertyOwnerId") int propertyOwnerId) throws PropertyException {
        return propertyService.createProperty(property, propertyOwnerId);
    }

    @GetMapping(value = "/property/propertyIdNumber/{propertyIdNumber}")
    public Property searchPropertyByPropertyIdNumber(@PathVariable("propertyIdNumber") long propertyIdNumber) throws PropertyException {
        return propertyService.searchPropertyByPropertyIdNumber(propertyIdNumber);
    }

    @GetMapping(value = "/property/vatNumber/{propertyOwnerVAT}")
    public List<Property> searchPropertyByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT) throws PropertyException {
        return propertyService.searchPropertyByVAT(propertyOwnerVAT);
    }

    @PutMapping(value = "/property/{propertyId}")
    public Property updatePropertyFields(@PathVariable("propertyId") int propertyId,
                                         @RequestBody Property property) throws PropertyException {
        return propertyService.updatePropertyFields(propertyId, property);
    }

    @PutMapping(value = "/property/{propertyId}/propertyOwner/{propertyOwnerId}")
    public Property updatePropertyFieldsAndPropertyOwner(@PathVariable("propertyId") int propertyId,
                                                         @PathVariable("propertyOwnerId") int propertyOwnerId,
                                                         @RequestBody Property property) throws PropertyException {
        return propertyService.updatePropertyFieldsAndPropertyOwner(propertyId, propertyOwnerId, property);
    }

    @DeleteMapping(value = "/property/{propertyIdNumber}")
    public boolean deletePropertyOwner(@PathVariable("propertyIdNumber") int propertyIdNumber) throws PropertyException {
        return propertyService.deleteProperty(propertyIdNumber);
    }
}
