package at.refugeescode.profiles.persistence.model;

import at.refugeescode.profiles.persistence.repository.CompanyRepository;
import at.refugeescode.profiles.persistence.repository.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class Interests {

    private CompanyRepository companyRepository;

    private ProfilesRepository profilesRepository;

    public Interests(CompanyRepository companyRepository, ProfilesRepository profilesRepository) {
        this.companyRepository = companyRepository;
        this.profilesRepository = profilesRepository;
    }

    public void interested(Long companyId, Long participantId) {
        Company company = companyRepository.findById(companyId).get();
        Profile participant = profilesRepository.findById(participantId).get();
        company.getInterested().add(participant);
        companyRepository.save(company);
    }

    public void notInterested(Long companyId, Long participantId) {
        Company company = companyRepository.findById(companyId).get();
        Profile participant = profilesRepository.findById(participantId).get();
        company.getInterested().remove(participant);
        companyRepository.save(company);
    }

    public List<Profile> getInterestedParticipants(Long userId) {
        return companyRepository.findById(userId).get().getInterested().stream()
                .collect(Collectors.toList());
    }
}
