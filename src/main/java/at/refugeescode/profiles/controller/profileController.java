package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@Controller
public class profileController {

    private ProfileService profileService;
    private Profile profile;

    public profileController(ProfileService profileService, Profile profile) {
        this.profileService = profileService;
        this.profile = profile;
    }

    //    @Value("${pathFile}")
//    private String pathFile;

    @GetMapping("/addParticipant")
    String page(){
        return "addParticipant";
    }

    //@GetMapping("/")
    //String page1(){
      //  return "showParticipants";
    //}


    @ModelAttribute("newParticipant")
    Profile getNewParticipant(){
        return new Profile();
    }




    @PostMapping("addParticipant")
    String addParticipant(@RequestParam("file") MultipartFile file, Profile profile, RedirectAttributes redirectAttributes){
        String uploadRootPath = "src\\main\\resources\\static\\img";

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
                redirectAttributes.addFlashAttribute("flash.message","Successfully uploaded");

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message","Failed to upload");
                return "You failed to upload " + " => " + e.getMessage();
            }

        return "redirect:/profiles";
    }




}
