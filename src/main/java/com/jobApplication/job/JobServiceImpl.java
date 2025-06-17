package com.jobApplication.job;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {


    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);

    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);

    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> existingJob = jobRepository.findById(id);

        if (existingJob.isPresent()) {
            Job found = existingJob.get();
            found.setDescription(updateJob.getDescription());
            found.setTitle(updateJob.getTitle());
            found.setMaxSalary(updateJob.getMaxSalary());
            found.setMinSalary(updateJob.getMinSalary());
            found.setLocation(updateJob.getLocation());
            jobRepository.save(found);
            return true;
        }


        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        Optional<Job> delete = jobRepository.findById(id);
        if (delete.isPresent()) {
            Job job = delete.get();
            jobRepository.delete(job);
            return true;
        }

        return false;
    }
}
