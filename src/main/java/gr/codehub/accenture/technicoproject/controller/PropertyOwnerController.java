package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PropertyOwnerController {

    // property owner create maria
    @PostMapping(value="/propertyOwner")
    public PropertyOwner create(@RequestBody PropertyOwner propertyOwner) {
        return propertyOwner;
    }

    // property owner search1 maria
    @GetMapping(value = "/propertyOwner/{propertyOwnerVAT}")//for now return null (services need to be done first)
    public  PropertyOwner searchByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT){
        return null;
    }

    // property owner search2 maria
    @GetMapping(value = "/propertyOwner/{propertyOwnerEmail}")//for now return null (services need to be done first)
    public  PropertyOwner searchByEmail(@PathVariable("propertyOwnerEmail") int propertyOwnerEmail){
        return null;
    }

    // property owner update aris

    // property owner delete aris




}
