package com.jobApplication;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping()
    public ResponseEntity<List<Job>> findAllJobs() {
        List<Job> jobList = jobService.findAllJobs();
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("job created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job found = jobService.findJobById(id);
        if (found != null) {
            return new ResponseEntity<>(found, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean updateJob = jobService.updateJob(id, job);
        if (updateJob) {
            return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleteFound = jobService.deleteJob(id);
        if (deleteFound) {
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("id not found ", HttpStatus.NOT_FOUND);


    }
}
