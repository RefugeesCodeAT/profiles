package at.refugeescode.profiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class contact {
    @GetMapping
    String contact(){
        return "contact";
    }
}
