package com.jobApplication.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //get companies
    @GetMapping
    public ResponseEntity<List<Company>>getAllCompanies(){
      List<Company>companyList=  companyService.getAllCompanies();
      if(companyList!=null){
          return new ResponseEntity<>(companyList, HttpStatus.FOUND);
      }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }



    //save company
    @PostMapping
    public ResponseEntity<String>createCompany(@RequestBody Company company){
       Company newCompany= companyService.createCompany(company);
       return new ResponseEntity<>("company created successfully",HttpStatus.CREATED);
    }


    //update company
    @PutMapping("/{id}")
    public ResponseEntity<String>updateCompany(@PathVariable Long id,@RequestBody Company company){
        boolean found = companyService.updateCompany(company, id);
        if(found){
            return new ResponseEntity<>("updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("id not found",HttpStatus.NOT_FOUND);

    }
    //findById company
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>>findCompany(@PathVariable Long id){
        Optional<Company> company = companyService.findCompany(id);
        return new ResponseEntity<>(company,HttpStatus.FOUND);
    }

    //delete company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleteFound = companyService.deleteJob(id);
        if (deleteFound) {
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("id not found ", HttpStatus.NOT_FOUND);


    }
}
