package at.refugeescode.profiles.controller;

import at.refugeescode.profiles.controller.testModel.EntityTest;
import at.refugeescode.profiles.controller.testModel.Repository;
import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.persistence.repository.ProfilesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class profileControllerTest {


    @Autowired
    Repository repository;

    @Autowired
    ProfilesRepository profilesRepository;
    
    @Test
    void findProfileById() {
        Optional<Profile> nour = profilesRepository.findOneByName("nour");
        assertNotNull(nour);
        assertEquals("nour", nour.get().getName());
    }

    @Test
    void addProfile() {
        repository.save(new EntityTest("bro"));
        List<EntityTest> profilesRepositoryAll = repository.findAll();
        assertNotNull(profilesRepositoryAll);
        String name = profilesRepositoryAll.get(0).getName();
        assertEquals("bro", name);
    }
}