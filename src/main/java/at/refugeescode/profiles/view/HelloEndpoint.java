package at.refugeescode.profiles.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloEndpoint {
    @GetMapping
    String hello(){
        return "helloworld";
    }
}
