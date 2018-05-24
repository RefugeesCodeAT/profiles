package at.refugeescode.profiles.logic;


import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.persistence.repository.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfilesRepository profileRepository;

    private Profile profile;

    public ProfileService(ProfilesRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfile(Profile profile){
        profileRepository.save(profile);
    }

    public List<Profile> findAll(){
        return   profileRepository.findAll();
    }

}
