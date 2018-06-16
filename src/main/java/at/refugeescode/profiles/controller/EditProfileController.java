package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/edit")
public class EditProfileController {
    ProfileService profileService;
    private Long id;

    public EditProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    String editPage(@PathVariable Long id, Model model) {
        this.id=id;
        Optional<Profile> profile = profileService.findOne(id);
        if (!profile.isPresent()) {
            return "redirect:/participants";
        }
        model.addAttribute("profile", profile.get());
       // model.addAttribute("newPrfoile",new Profile());

        return "editParticipant";
    }
    @ModelAttribute("newProfile")
    Profile getObject(){
        return new Profile();
    }

    @PostMapping
    public String edit(@RequestParam String name,Profile profile,
                       @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes){
        Optional<Profile> one = profileService.findOne(id);
        Profile oneprofile = one.get();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                profile.setPicture(bytes);
            }
            oneprofile.setName(name);
//            profile.setSkills(skilles);
//            profile.setDescription(description);
//            profile.setIntroduction(introduction);
//            profile.setGithubUrl(githubUrl);
//            profile.setBio(bio);
//            profile.setSpecialization(specialization);
//            profile.setSkills(null);
//            profile.setName(name);
            profileService.saveProfile(oneprofile);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully edit");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
            return "You failed to upload because " + " => " + e.getMessage();
        }
        return "redirect:/"+ "profile/" + id;
    }

}
