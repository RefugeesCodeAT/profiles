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


//    @GetMapping("/profile")
//    String profilePage(){
//        return "profile";
//    }

    @GetMapping("/profile/{id}")
    String page(@PathVariable Long id, Model model) {
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
    String addParticipant(@RequestParam("file") MultipartFile file, Profile profile, RedirectAttributes redirectAttributes) {
        String uploadRootPath = "src\\main\\resources\\static\\images";

        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        try {
            byte[] bytes = new byte[0];
            bytes = file.getBytes();
            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            profile.setPicture(bytes);
            profile.setPicPath(file.getOriginalFilename());
            profileService.saveProfile(profile);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
            return "You failed to upload because " + " => " + e.getMessage();
        }

        return "redirect:/profiles";
    }


}
