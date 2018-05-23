package at.refugeescode.profiles.initialise;

import at.refugeescode.profiles.persistence.model.Admin;
import at.refugeescode.profiles.persistence.repository.AdminRepository;
import at.refugeescode.profiles.persistence.repository.CompanyRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class UserInitialisation {

    @Bean
    ApplicationRunner initialiseUsers(PasswordEncoder passwordEncoder, CompanyRepository companyRepository, AdminRepository adminRepository) {
        return args -> {
//            Admin admin = new Admin();
//            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("admin"));
//            admin.setAuthorities(Stream.of("ADMIN").collect(Collectors.toSet()));
//            adminRepository.save(admin);
        };
    }
}
