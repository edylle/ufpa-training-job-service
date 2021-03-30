package com.freela.job.service;

import com.freela.job.entity.JobEntity;
import com.freela.job.repository.CategoryRepository;
import com.freela.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class JobService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private JobRepository jobRepository;

    public JobEntity save(JobEntity job) {
        categoryRepository.findById(job.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found for ID: " + job.getCategoryId()));

        return jobRepository.save(job);
    }

}
