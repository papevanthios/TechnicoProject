package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.exception.PropertyOwnerException;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PropertyOwnerController {
    private PropertyOwnerService propertyOwnerService;

    // property owner create maria
    @PostMapping(value = "/propertyOwner")
    public PropertyOwner createPropertyOwner(@RequestBody PropertyOwner propertyOwner) throws PropertyOwnerException {
        return propertyOwnerService.createPropertyOwner(propertyOwner);
    }

    // property owner search1 maria
    @GetMapping(value = "/propertyOwner/vatNumber/{propertyOwnerVAT}")
    public PropertyOwner searchPropertyOwnerByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT) throws PropertyOwnerException {
        return propertyOwnerService.searchByVAT(propertyOwnerVAT);
    }

    // property owner search1 maria
    @GetMapping(value = "/propertyOwner/email/{propertyOwnerEmail}")
    public PropertyOwner searchPropertyOwnerByEmail(@PathVariable("propertyOwnerEmail") String propertyOwnerEmail) throws PropertyOwnerException {
        return propertyOwnerService.searchByEmail(propertyOwnerEmail);
    }

    // UPDATE - aris
    @PutMapping(value = "/propertyOwner/{propertyOwnerId}")
    public PropertyOwner updatePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId, @RequestBody PropertyOwner propertyOwner) throws PropertyOwnerException {
        return propertyOwnerService.updatePropertyOwner(propertyOwnerId, propertyOwner);
    }

    // property owner delete aris
    @DeleteMapping(value = "/propertyOwner/{propertyOwnerId}")
    public boolean deletePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId) throws PropertyOwnerException {
        return propertyOwnerService.deletePropertyOwner(propertyOwnerId);
    }
}
