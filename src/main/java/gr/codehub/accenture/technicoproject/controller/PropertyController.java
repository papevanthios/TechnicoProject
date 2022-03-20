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

    // property  create aris
    @PostMapping(value = "/property/propertyOwner/{propertyOwnerId}")
    public Property createProperty(@RequestBody Property property, @PathVariable ("propertyOwnerId") int propertyOwnerId) throws PropertyException {
        return propertyService.createProperty(property, propertyOwnerId);
    }

    // property  search1 aris
    @GetMapping(value = "/property/propertyIdNumber/{propertyIdNumber}")
    public Property searchPropertyByPropertyIdNumber(@PathVariable("propertyIdNumber") long propertyIdNumber) throws PropertyException {
        return propertyService.searchPropertyByPropertyIdNumber(propertyIdNumber);
    }

    // property  search2 kostas
    @GetMapping(value = "/property/vatNumber/{propertyOwnerVAT}")
    public List<Property> searchPropertyByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT) throws PropertyException {
        return propertyService.searchPropertyByVAT(propertyOwnerVAT);
    }

    // property  update kostas

    // property  delete kostas
    @DeleteMapping(value = "/property/{propertyIdNumber}")
    public boolean deletePropertyOwner(@PathVariable("propertyIdNumber") int propertyIdNumber) throws PropertyException {
        return propertyService.deleteProperty(propertyIdNumber);
    }
}
