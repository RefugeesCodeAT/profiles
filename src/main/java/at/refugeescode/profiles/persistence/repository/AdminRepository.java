package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
