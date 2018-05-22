package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profile, Long> {
}
