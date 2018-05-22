package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
