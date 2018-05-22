package at.refugeescode.profiles.logic;


import at.refugeescode.profiles.persistence.model.Company;
import at.refugeescode.profiles.persistence.model.Profile;
import at.refugeescode.profiles.persistence.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    private Company company;

    public CompanyService(CompanyRepository companyRepository, Company company) {
        this.companyRepository = companyRepository;
        this.company = company;
    }

    public void saveCompany(Company company){
        companyRepository.save(company);
    }
}
