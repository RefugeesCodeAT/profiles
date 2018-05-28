package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
public class profileController {

    private ProfileService profileService;

    public profileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/addParticipant")
    String page() {
        return "addParticipant";
    }

    @GetMapping("/profile/{id}")
    String findProfileById(@PathVariable Long id, Model model) {
        Optional<Profile> profile = profileService.findOne(id);
        if (!profile.isPresent()) {
            return "redirect:/profiles";
        }
        model.addAttribute("profile", profile.get());
        return "profile";
    }


    @ModelAttribute("newParticipant")
    Profile getNewParticipant() {
        return new Profile();
    }

    @ModelAttribute("participants")
    List<Profile> getOneParticipant() {
        return profileService.findAll();
    }

    @PostMapping("addParticipant")
    String addProfile(@RequestParam("file") MultipartFile file, Profile profile, RedirectAttributes redirectAttributes) {
        try {
            byte[] bytes = file.getBytes();
            profile.setPicture(bytes);
            profile.setSkills(profile.getSkills());
            profileService.saveProfile(profile);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
            return "You failed to upload because " + " => " + e.getMessage();
        }
        return "redirect:/profiles";
    }
}
