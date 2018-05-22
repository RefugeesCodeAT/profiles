package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findOneByUsername(String username);
}
