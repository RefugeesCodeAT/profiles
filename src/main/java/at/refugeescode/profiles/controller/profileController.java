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

    @ModelAttribute("allList")
    List<Profile> getAllParticipant(){
        List<Profile> all = profileService.findAll();
        System.out.println("-----------------------------------\nwe are here \n");
        all.forEach(oneRow -> System.out.println(oneRow.getPicPath()));

        return all;
    }


    @PostMapping("addParticipant")
    String addParticipant(@RequestParam("file") MultipartFile file, Profile profile, RedirectAttributes redirectAttributes){
        if (!file.isEmpty()) {
            try {
                String UPLOADED_FOLDER = "C:\\Users\\Mohammad\\Desktop\\programmes\\profiles\\src\\main\\resources\\static\\images";
                byte[] bytes = file.getBytes();
                File serverFile = new File(UPLOADED_FOLDER+File.separator+ file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                profile.setPicture(bytes);
                profile.setPicPath(file.getOriginalFilename());
                this.profile=profile;

                profileService.saveProfile(profile);
                redirectAttributes.addFlashAttribute("flash.message","Successfully uploaded");

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message","Failed to upload");
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
        }
        return "redirect:/profiles";
    }




}
