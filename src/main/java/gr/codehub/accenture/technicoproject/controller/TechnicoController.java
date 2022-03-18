package gr.codehub.accenture.technicoproject.controller;

import gr.codehub.accenture.technicoproject.model.PropertyOwner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
public class TechnicoController {
//    private Object Owner;
    // attributes for services

    // endpoint home maria
//
    @GetMapping(value="/home")
    public String home(){
        return "home page";
    }


    // property owner create maria

//    @PostMapping(value="/owner")
//    public PropertyOwner create(@RequestBody PropertyOwner owner)
//    {
//        return PropertyOwner.createPropertyOwner(Owner);
//    }

    // property owner search1 maria

//    @SearchMapping(value = "/Date")
//    public Date search(@RequestBody Date date)
//    {
//        int Date = 0;
//        return Date.(Date);
    // property owner search2 maria

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
