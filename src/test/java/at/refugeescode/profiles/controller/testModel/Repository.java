package at.refugeescode.profiles.controller.testModel;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<EntityTest,Long> {
    Optional<EntityTest> findOneByName(String name);
}
