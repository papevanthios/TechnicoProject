package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
public class TechnicoController{
//    private static String id;
//    private PropertyOwner Owner;
//    private String Email;

//    private Object Owner;
    // attributes for services

    // endpoint home maria
//
    @GetMapping(value="/")
    public String home(){
        return "home page";
    }


    // property owner create maria

    @PostMapping(value="/propertyOwner")
    public PropertyOwner create(@RequestBody PropertyOwner propertyOwner)
    {
        return propertyOwner;
    }

    // property owner search1 maria

    @GetMapping(value = "/propertyOwner/{propertyOwnerVAT}")//for now return null (services need to be done first)
    public  PropertyOwner searchByVAT(@PathVariable("propertyOwnerVAT") int propertyOwnerVAT){
        return null;}



    // property owner search2 maria


    @GetMapping(value = "/propertyOwner/{propertyOwnerEmail}")//for now return null (services need to be done first)
    public  PropertyOwner searchByEmail(@PathVariable("propertyOwnerEmail") int propertyOwnerEmail){
        return null;}

    // property owner update aris

    // property owner delete aris

    // property  create aris

    // property  search1 aris

    // property  search2 kostas

    // property  update kostas

    // property  delete kostas

    // property repair create kostas

    // property repair search1 manthos

    // property repair search2

    // property repair update

    // property repair delete

}
