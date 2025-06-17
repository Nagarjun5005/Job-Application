package com.jobApplication.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();

    Job findJobById(Long id);

    void createJob(Job job);

    boolean updateJob(Long id, Job job);

    boolean deleteJob(Long id);
}
