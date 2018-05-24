package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/profiles")
public class ParticipantController {
    private ProfileService profileService;
    private Profile profile;

    public ParticipantController(ProfileService profileService, Profile profile) {
        this.profileService = profileService;
        this.profile = profile;
    }

    @GetMapping
    String showParticipant(){
        return "par";
    }

    @ModelAttribute("allList")
    List<Profile> getAllParticipant(){
        List<Profile> all = profileService.findAll();
        System.out.println("-----------------------------------\nwe are here \n");
        all.forEach(oneRow -> System.out.println(oneRow.getPicPath()));

        return all;
    }
}
