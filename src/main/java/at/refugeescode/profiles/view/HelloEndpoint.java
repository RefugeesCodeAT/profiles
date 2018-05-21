package at.refugeescode.profiles.view;

import at.refugeescode.profiles.persistence.model.Profiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloEndpoint {
    @GetMapping
    String hello(Profiles profiles){
        profiles.setGithubUrl("github.com/mohammmod");
        return "helloworld";
    }
}
