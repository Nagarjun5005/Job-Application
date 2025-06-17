package com.jobApplication.company;


import com.jobApplication.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        Company saveCompany = companyRepository.save(company);
        return saveCompany;
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Company updateCompany = existingCompany.get();
            updateCompany.setDescription(company.getDescription());
            updateCompany.setName(company.getName());
            companyRepository.save(updateCompany);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Company> findCompany(Long id) {
        Optional<Company> found = companyRepository.findById(id);
        return found;
    }

    @Override
    public boolean deleteJob(Long id) {
        Optional<Company> delete = companyRepository.findById(id);
        if (delete.isPresent()) {
           Company company = delete.get();
            companyRepository.delete(company);
            return true;
        }
        return false;
    }

    }

