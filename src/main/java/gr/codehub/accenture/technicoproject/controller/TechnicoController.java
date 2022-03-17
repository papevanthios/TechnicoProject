package gr.codehub.accenture.technicoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TechnicoController {
    // attributes for services

    // endpoint home maria

    @GetMapping(value="/home")
    public String home(){
        return "home page";
    }


    // property owner create maria

    // property owner search1 maria

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
