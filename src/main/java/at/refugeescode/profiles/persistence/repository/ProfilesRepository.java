package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository <Profiles, Long>{
}
