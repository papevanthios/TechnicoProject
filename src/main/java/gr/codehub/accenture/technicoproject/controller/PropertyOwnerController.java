package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for the owner's web page.
 * We are using REST-API where the FRONT-END is replaced by Postman or Swagger.
 * <p>
 * Basic CRUD operations are requested from this controller regarding the owner model class.
 * The HTTP methods used are:
 * <li>POST
 * <li>GET
 * <li>PUT
 * <li>DELETE
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/propertyOwner")
public class PropertyOwnerController {
    private PropertyOwnerService propertyOwnerService;

    /**
     * Creating an owner with a POST mapping.
     *
     * @param propertyOwner owner's data from JSON object
     * @return  owner's data, status of request and a message
     */
    @PostMapping(value = "")
    public ResponseResultDto<PropertyOwner> createPropertyOwner(@RequestBody PropertyOwner propertyOwner) {
        return propertyOwnerService.createPropertyOwner(propertyOwner);
    }

    /**
     * Obtains an owner by his VAT number with a GET mapping.
     *
     * @param propertyOwnerVAT  owner's VAT number, 9-digits number
     * @return  owner's data, status of request and a message
     */
    @GetMapping(value = "/vatNumber/{propertyOwnerVAT}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerByVAT(@PathVariable("propertyOwnerVAT") String propertyOwnerVAT) {
        return propertyOwnerService.searchByVAT(propertyOwnerVAT);
    }

    /**
     * Obtains an owner by his email with a GET mapping.
     *
     * @param propertyOwnerEmail  owner's email, []@[].[]
     * @return  owner's data, status of request and a message
     */
    @GetMapping(value = "/email/{propertyOwnerEmail}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerByEmail(@PathVariable("propertyOwnerEmail") String propertyOwnerEmail) {
        return propertyOwnerService.searchByEmail(propertyOwnerEmail);
    }

    /**
     * Obtains an owner by his id primary key with a GET mapping.
     *
     * @param propertyOwnerId  owner's id primary key, from 1 to ...
     * @return  owner's data, status of request and a message
     */
    @GetMapping(value = "/{propertyOwnerId}")
    public ResponseResultDto<PropertyOwner> searchPropertyOwnerById(@PathVariable("propertyOwnerId") int propertyOwnerId) {
        return propertyOwnerService.searchByPropertyOwnerId(propertyOwnerId);
    }

    /**
     * Updates owner's details by his id primary key with a PUT mapping.
     *
     * @param propertyOwnerId owner's id primary key, from 1 to ...
     * @param propertyOwner  owner's details to be updated, can be one or more
     * @return  owner's data, status of request and a message
     */
    @PutMapping(value = "/{propertyOwnerId}")// to do
    public ResponseResultDto<PropertyOwner> updatePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId,
                                             @RequestBody PropertyOwner propertyOwner) {
        return propertyOwnerService.updatePropertyOwner(propertyOwnerId, propertyOwner);
    }

    /**
     * Deletes the owner by his id primary key with a DELETE mapping.
     *
     * @param propertyOwnerId  owner's id primary key, from 1 to ...
     * @return  boolean, true if deletion is successfull otherwise returns false
     */
    @DeleteMapping(value = "/{propertyOwnerId}")
    public ResponseResultDto<Boolean> deletePropertyOwner(@PathVariable("propertyOwnerId") int propertyOwnerId) {
        return propertyOwnerService.deletePropertyOwner(propertyOwnerId);
    }
}
