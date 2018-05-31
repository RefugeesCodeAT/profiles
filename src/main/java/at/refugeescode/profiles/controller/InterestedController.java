package at.refugeescode.profiles.controller;


import at.refugeescode.profiles.persistence.model.Interests;
import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.security.AdminPrincipal;
import at.refugeescode.profiles.security.UserPrincipal;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
public class InterestedController {

    private Interests interests;

    public InterestedController(Interests interests) {
        this.interests = interests;
    }



//    @ModelAttribute("loggedCompanyName")
//    String principal(@AuthenticationPrincipal UserPrincipal principal) {
//        String text =" ";
//        if(principal != null){
//            text += "Welcome " + principal.getCompany().getName();
//        }
//        return text;
//    }
//
//    @ModelAttribute("loggedAdminName")
//    String principal2(@AuthenticationPrincipal AdminPrincipal principal) {
//        String text =" ";
//        if(principal != null){
//            text += "Welcome " + principal.getAdmin().getName();
//        }
//        return text;
//    }

//    @ModelAttribute("loggedUserName")
//    String prin(Principal principal) {
//        String text =" ";
//        if(principal != null){
//            text += "Welcome " + principal.getName();
//        }
//        return text;
//    }



}