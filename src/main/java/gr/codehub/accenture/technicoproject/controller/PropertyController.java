package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.Property;
import gr.codehub.accenture.technicoproject.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Property controller with the endpoints for creation,
 * searching, updating and deleting of the properties.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {
    private PropertyService propertyService;

    /**
     * Creating property, checking the fields for null and
     * then saving it to the repository.
     * @param property property information.
     * @param propertyOwnerId property owner ID.
     * @return a response result with appropriate message.
     */
    @PostMapping("/propertyOwner/{propertyOwnerId}")
    public ResponseResultDto<Property> createProperty(@Valid @RequestBody Property property,
                                                      @PathVariable ("propertyOwnerId") int propertyOwnerId) {
        return propertyService.createProperty(property, propertyOwnerId);
    }

    /**
     * Searching property by property ID (if exists).
     * @param propertyId property ID.
     * @return a response result with appropriate message.
     */
    @GetMapping("/propertyId/{propertyId}")
    public ResponseResultDto<Property> searchPropertyByPropertyId(@PathVariable("propertyId") int propertyId) {
        return propertyService.searchPropertyByPropertyId(propertyId);
    }

    /**
     * Searching property by property identification number (if exists).
     * @param propertyIdNumber property identification number.
     * @return a response result with appropriate message.
     */
    @GetMapping("/propertyIdNumber/{propertyIdNumber}")
    public ResponseResultDto<Property> searchPropertyByPropertyIdNumber(@PathVariable("propertyIdNumber") long propertyIdNumber) {
        return propertyService.searchPropertyByPropertyIdNumber(propertyIdNumber);
    }

    /**
     * Searching properties by property owner's VAT number (if exist).
     * @param propertyOwnerVAT property owner's VAT number.
     * @return a response result with appropriate message.
     */
    @GetMapping("/vatNumber/{propertyOwnerVAT}")
    public ResponseResultDto<List<Property>> searchPropertyByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT) {
        return propertyService.searchPropertyByVAT(propertyOwnerVAT);
    }

    /**
     * Updating property fields (except for property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     * @param propertyId property ID.
     * @param property property information.
     * @return a response result with appropriate message.
     */
    @PutMapping("/{propertyId}")
    public ResponseResultDto<Property> updatePropertyFields(@PathVariable("propertyId") int propertyId,
                                                            @RequestBody Property property) {
        return propertyService.updatePropertyFields(propertyId, property);
    }

    /**
     * Updating property fields (including property owner),
     * by checking every possible field for user input,
     * and then saving it to the repository.
     * @param propertyId property ID.
     * @param propertyOwnerId property owner ID.
     * @param property property information.
     * @return a response result with appropriate message.
     */
    @PutMapping("/{propertyId}/propertyOwner/{propertyOwnerId}")
    public ResponseResultDto<Property> updatePropertyFieldsAndPropertyOwner(@PathVariable("propertyId") int propertyId,
                                                                            @PathVariable("propertyOwnerId") int propertyOwnerId,
                                                                            @RequestBody Property property) {
        return propertyService.updatePropertyFieldsAndPropertyOwner(propertyId, propertyOwnerId, property);
    }

    /**
     * Deleting property if there is not any repair.
     * @param propertyIdNumber property identification number.
     * @return a response result with appropriate message.
     */
    @DeleteMapping("/{propertyIdNumber}")
    public ResponseResultDto<Boolean> deletePropertyOwner(@PathVariable("propertyIdNumber") int propertyIdNumber) {
        return propertyService.deleteProperty(propertyIdNumber);
    }
}
