package at.refugeescode.profiles.initialise;

import at.refugeescode.profiles.persistence.model.Admin;
import at.refugeescode.profiles.persistence.model.Company;
import at.refugeescode.profiles.persistence.repository.AdminRepository;
import at.refugeescode.profiles.persistence.repository.CompanyRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class UserInitialisation {

    @Bean
    ApplicationRunner initialiseUsers(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        return args -> {
            String username = "admin";
            Optional<Admin> oAdmin = adminRepository.findOneByUsername(username);
            if(oAdmin.isPresent()){
                return;
            }
            saveAdmin(passwordEncoder, adminRepository, username);
        };
    }

    private void saveAdmin(PasswordEncoder passwordEncoder, AdminRepository adminRepository, String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(username));
        admin.setAuthorities(Stream.of("ADMIN").collect(Collectors.toSet()));
        adminRepository.save(admin);
    }
}
