package com.jobApplication;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class JobServiceImpl implements JobService {


    List<Job> jobs = new ArrayList<>();
    Long id = 1L;

    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }

    @Override
    public Job findJobById(Long id) {
        for (Job found : jobs) {
            if (found.getId().equals(id)) {
                return found;
            }
        }
        return null;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(new Job(id++, job.getTitle(), job.getDescription(), job.getMaxSalary(), job.getMinSalary(), job.getLocation()));

    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        for (Job found : jobs) {
            if (found.getId().equals(id)) {
                found.setDescription(updateJob.getDescription());
                found.setTitle(updateJob.getTitle());
                found.setMaxSalary(updateJob.getMaxSalary());
                found.setMinSalary(updateJob.getMinSalary());
                found.setLocation(updateJob.getLocation());
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        Iterator<Job> deleteIterator = jobs.iterator();
        while (deleteIterator.hasNext()) {
            Job deleteFound = deleteIterator.next();
            if (deleteFound.getId().equals(id)) {
                deleteIterator.remove();
                return true;
            }
        }
        return false;

    }
}
