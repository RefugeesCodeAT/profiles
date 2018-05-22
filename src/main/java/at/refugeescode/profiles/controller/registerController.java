//package at.refugeescode.profiles.controller;
//import at.refugeescode.profiles.logic.CompanyService;
//import at.refugeescode.profiles.persistence.model.Company;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//@Controller
//public class registerController {
//
//    private CompanyService companyService;
//
//    private PasswordEncoder passwordEncoder;
//
//    public registerController(CompanyService companyService, PasswordEncoder passwordEncoder) {
//        this.companyService = companyService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/addCompany")
//    String page4(){
//        return "addCompany";
//    }
//
//
//    @ModelAttribute("newCompany")
//    Company getNewCompany(){
//        return new Company();
//    }
//
//    @PostMapping("addCompany")
//    String addCompany(@Valid Company company , BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            return page4();
//        }
//        company.setUsername(company.getUsername());
//        company.setEmail(company.getEmail());
//        company.setName(company.getName());
//        company.setPassword(passwordEncoder.encode(company.getPassword()));
//        company.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));
//        companyService.saveCompany(company);
//        return "redirect:/";
//    }
//}