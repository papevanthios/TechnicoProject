package gr.codehub.accenture.technicoproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The general controller contains only the home endpoint of the project.
 */
@RestController
@AllArgsConstructor
public class GeneralController {
    @GetMapping(value = "")
    public String home() {
        return "Welcome to the Team 2";
    }
}
