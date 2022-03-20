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

    @PostMapping(value = "/propertyOwner")
    public PropertyOwner createPropertyOwner(@RequestBody PropertyOwner propertyOwner) throws PropertyOwnerException {
        return propertyOwnerService.createPropertyOwner(propertyOwner);
    }

    @GetMapping(value = "/propertyOwner/vatNumber/{propertyOwnerVAT}")
    public PropertyOwner searchPropertyOwnerByVAT(@PathVariable("propertyOwnerVAT") String propertyOwnerVAT) throws PropertyOwnerException {
        return propertyOwnerService.searchByVAT(propertyOwnerVAT);
    }

    @GetMapping(value = "/propertyOwner/email/{propertyOwnerEmail}")
    public PropertyOwner searchPropertyOwnerByEmail(@PathVariable("propertyOwnerEmail") String propertyOwnerEmail) throws PropertyOwnerException {
        return propertyOwnerService.searchByEmail(propertyOwnerEmail);
    }

    @PutMapping(value = "/propertyOwner/{propertyOwnerId}")
    public PropertyOwner updatePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId,
                                             @RequestBody PropertyOwner propertyOwner) throws PropertyOwnerException {
        return propertyOwnerService.updatePropertyOwner(propertyOwnerId, propertyOwner);
    }

    @DeleteMapping(value = "/propertyOwner/{propertyOwnerId}")
    public boolean deletePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId) throws PropertyOwnerException {
        return propertyOwnerService.deletePropertyOwner(propertyOwnerId);
    }
}
