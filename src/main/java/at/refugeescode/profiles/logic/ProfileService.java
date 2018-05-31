package at.refugeescode.profiles.logic;


import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.persistence.repository.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private ProfilesRepository profileRepository;

    public ProfileService(ProfilesRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfile(Profile profile){
        profileRepository.save(profile);
    }

    public void deleteProfile(Profile profile){
        profileRepository.delete(profile);
    }

    public List<Profile> findAll(){
        return   profileRepository.findAll();
    }

    public Optional<Profile> findOne(Long id){
        return profileRepository.findById(id);
    }

    public Optional<Profile> findOneByUsername(String name){
        return profileRepository.findOneByName(name);
    }

}
