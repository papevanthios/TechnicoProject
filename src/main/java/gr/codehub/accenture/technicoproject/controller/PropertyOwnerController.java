package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import gr.codehub.accenture.technicoproject.service.PropertyOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PropertyOwnerController {
    private PropertyOwnerService propertyOwnerService;

    // property owner create maria

    @PostMapping(value="/propertyOwner")
    public PropertyOwner createPropertyOwner(@RequestBody PropertyOwner propertyOwner) {
        return propertyOwnerService.createPropertyOwner(propertyOwner);
    }

    // property owner search1 maria

    @GetMapping(value = "/propertyOwner/{propertyOwnerVAT}")//for now return null (services need to be done first)
    public  PropertyOwner searchByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT){
        return propertyOwnerService.searchByVAT(propertyOwnerVAT);
    }

//    We must create a new search by email, the url cannot recognise the difference with the search by vat

//     property owner search2 maria
//
//    @GetMapping(value = "/propertyOwner/{propertyOwnerEmail}")//for now return null (services need to be done first)
//    public  PropertyOwner searchByEmail(@PathVariable("propertyOwnerEmail") int propertyOwnerEmail){
//        PropertyOwner Email = null;
//        return null;
//    }

    // property owner update aris

    // property owner delete aris




}
