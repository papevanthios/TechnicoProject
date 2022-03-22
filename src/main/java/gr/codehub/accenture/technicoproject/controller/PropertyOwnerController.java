package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/propertyOwner")
public class PropertyOwnerController {
    private PropertyOwnerService propertyOwnerService;

    @PostMapping(value = "")
    public ResponseResultDto<PropertyOwner> createPropertyOwner(@Valid @RequestBody PropertyOwner propertyOwner) {
        return propertyOwnerService.createPropertyOwner(propertyOwner);
    }

    @GetMapping(value = "/vatNumber/{propertyOwnerVAT}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerByVAT(@PathVariable("propertyOwnerVAT") String propertyOwnerVAT) {
        return propertyOwnerService.searchByVAT(propertyOwnerVAT);
    }

    @GetMapping(value = "/email/{propertyOwnerEmail}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerByEmail(@PathVariable("propertyOwnerEmail") String propertyOwnerEmail) {
        return propertyOwnerService.searchByEmail(propertyOwnerEmail);
    }

    @GetMapping(value = "/{propertyOwnerId}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerById(@PathVariable("propertyOwnerId") int propertyOwnerId) {
        return propertyOwnerService.searchByPropertyOwnerId(propertyOwnerId);
    }

    @PutMapping(value = "/{propertyOwnerId}")// to do
    public ResponseResultDto<PropertyOwner> updatePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId,
                                             @RequestBody PropertyOwner propertyOwner) {
        return propertyOwnerService.updatePropertyOwner(propertyOwnerId, propertyOwner);
    }

    @DeleteMapping(value = "/{propertyOwnerId}")
    public ResponseResultDto<Boolean> deletePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId) {
        return propertyOwnerService.deletePropertyOwner(propertyOwnerId);
    }
}
