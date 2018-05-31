package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.logic.ProfileService;
import at.refugeescode.profiles.persistence.model.EmailService;
import at.refugeescode.profiles.persistence.model.Interests;
import at.refugeescode.profiles.persistence.model.Mail;
import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.security.AdminPrincipal;
import at.refugeescode.profiles.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class profileController {

    Profile participant = new Profile();
    private ProfileService profileService;
    private Profile oneProfile;
    private Interests interests;
    private EmailService emailService;

    public profileController(ProfileService profileService, Profile oneProfile, Interests interests, EmailService emailService) {
        this.profileService = profileService;
        this.oneProfile = oneProfile;
        this.interests = interests;
        this.emailService = emailService;
    }

    @ModelAttribute("interestedParticipants")
    List<Profile> interestedParticipants(@AuthenticationPrincipal UserPrincipal userPrincipal, AdminPrincipal adminPrincipal) {
        if (userPrincipal == null) {
            return null;
        } else if (adminPrincipal == null) {
            return null;
        } else {
            return interests.getInterestedParticipants(userPrincipal.getCompany().getId());
        }
    }

    //@Secured("ROLE_USER")
    @PostMapping("like")
    String interested(@RequestParam Long id, @AuthenticationPrincipal UserPrincipal principal) throws MessagingException {
        Long userId = principal.getCompany().getId();
        Long participantId = id;
        Optional<Profile> par = profileService.findOne(participantId);
        //log.info("Spring Mail - Sending Email with Inline Attachment Example");
        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("mohammadalmosleh66@gmail.com");
        //mail.setTo(CompanyUsername.get().getEmail());
        mail.setSubject("Sending Email with Inline Attachment Example");
        mail.setContent(principal.getCompany().getName() + " is very interested in " + par.get().getName());
        emailService.sendSimpleMessage(mail);

        interests.interested(userId, participantId);
        return "redirect:/profile";
    }

    //@Secured("ROLE_USER")
    @PostMapping("dislike")
    String notInterested(@RequestParam Long id, @AuthenticationPrincipal UserPrincipal principal) throws MessagingException {
        Long userId = principal.getCompany().getId();
        Long participantId = id;
        Optional<Profile> par = profileService.findOne(participantId);
        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("mohammadalmosleh66@gmail.com");
        //mail.setTo(CompanyUsername.get().getEmail());
        mail.setSubject("Sending Email with Inline Attachment Example");
        mail.setContent(principal.getCompany().getName() + " is NOT interested in " + par.get().getName());
        emailService.sendSimpleMessage(mail);

        interests.notInterested(userId, participantId);
        return "redirect:/profile";
    }

//    @GetMapping("/profile/{id}")
//    String findProfileById(@PathVariable Long id, Model model) {
//        Optional<Profile> profile = profileService.findOne(id);
//        if (!profile.isPresent()) {
//            return "redirect:/profiles";
//        }
//        model.addAttribute("profile", profile.get());
//        return "profile";
//    }

    @GetMapping("/addParticipant")
    String page() {
        return "addParticipant";
    }

    @GetMapping("/profile")
    String showParticipants() {
        return "profile";
    }

    @GetMapping("/participants")
    String showParticipant() {
        return "participants";
    }

    @ModelAttribute("profiles")
    List<Profile> profiles() {
        return profileService.findAll();
    }

    @ModelAttribute("profile")
    Profile profile() {
        return oneProfile;
    }

    @ModelAttribute("newParticipant")
    Profile getNewParticipant() {
        return new Profile();
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
        return "redirect:/participants";
    }

    @GetMapping("/edit")
    String editPage() {
        return "editParticipant";
    }

    @ModelAttribute("p")
    Profile editParticipant() {
        return participant;
    }


    @PostMapping("update")
    String goedit(@RequestParam String id) {
        Optional<Profile> oldParticipant = profileService.findAll().stream()
                .filter(participant1 -> participant1.getId().toString().equalsIgnoreCase(id))
                .findFirst();
        participant = oldParticipant.get();
        return "redirect:/edit";
    }

    @PostMapping("/edit")
    public String updateParticipant(@RequestParam String name, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                participant.setPicture(bytes);
            }
            participant.setSkills(participant.getSkills());
            participant.setName(name);
            profileService.saveProfile(participant);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload");
            return "You failed to upload because " + " => " + e.getMessage();
        }
        return "redirect:/participants";
    }


    @PostMapping("/profile")
    String goProfile(@RequestParam String idParticipant) {
        List<Profile> collect = profileService.findAll().stream()
                .filter(participant -> participant.getId().toString().equalsIgnoreCase(idParticipant))
                .collect(Collectors.toList());
        oneProfile = collect.get(0);
        return "redirect:profile";
    }

    @PostMapping("/delete")
    String deleteToDo(String id) {
        Optional<Profile> profile = profileService.findOne(Long.valueOf(id));
        if (profile.isPresent()) {
            profileService.deleteProfile(profile.get());
        }
        return "redirect:/participants";
    }

}
