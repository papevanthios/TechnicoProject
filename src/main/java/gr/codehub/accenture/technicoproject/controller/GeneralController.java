package gr.codehub.accenture.technicoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GeneralController {

    @GetMapping(value = "")
    public String home() {
        return "Welcome to the Team ....";
    }
}
