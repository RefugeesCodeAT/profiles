package at.refugeescode.profiles.controller;
import at.refugeescode.profiles.logic.CompanyService;
import at.refugeescode.profiles.persistence.model.Company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class registerController {

    private CompanyService companyService;

    private PasswordEncoder passwordEncoder;

    private Company company;

    public registerController(CompanyService companyService, PasswordEncoder passwordEncoder, Company company) {
        this.companyService = companyService;
        this.passwordEncoder = passwordEncoder;
        this.company = company;
    }


    @PostMapping("addCompany")
    String addCompany(@RequestParam String username,@RequestParam String companyName
    ,@RequestParam String password,@RequestParam String email){
        company.setUsername(username);
        company.setEmail(email);
        company.setName(companyName);
        company.setPassword(passwordEncoder.encode(password));
        company.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));
        companyService.saveCompany(company);
        return "redirect:/";
    }
}