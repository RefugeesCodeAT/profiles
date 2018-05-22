package at.refugeescode.profiles.controller;

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


    @GetMapping("/addParticipant")
    String page(){
        return "addParticipant";
    }


    @ModelAttribute("newParticipant")
    Profile getNewParticipant(){
        return new Profile();
    }

    @ModelAttribute("allList")
    List<Profile> getAllParticipant(){
        return profilesRepository.findAll();
    }


    @PostMapping("addParticipant")
    String addParticipant(Profile participant, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if (!file.isEmpty()) {
            try {
                String UPLOADED_FOLDER = "C:\\Users\\Mohammad\\Projects\\profiles-project-demo\\src\\main\\resources\\static\\images";
                byte[] bytes = file.getBytes();
                File serverFile = new File(UPLOADED_FOLDER+File.separator+ file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                participant.setImage(file.getOriginalFilename());
                this.profile=profile;

                profilesRepository.save(this.participant);
                redirectAttributes.addFlashAttribute("flash.message","Successfully uploaded");

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flash.message","Failed to upload");
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
        }
        return "redirect:/";
    }




}
