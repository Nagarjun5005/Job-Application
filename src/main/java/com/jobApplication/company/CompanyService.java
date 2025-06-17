package com.jobApplication.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company createCompany(Company company);

    boolean updateCompany(Company company, Long id);

   Optional<Company> findCompany(Long id);

    boolean deleteJob(Long id);
}
