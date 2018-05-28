package at.refugeescode.profiles.persistence.repository;

import at.refugeescode.profiles.persistence.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfilesRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findOneByName(String name);
}

