package com.freela.job.service;

import com.freela.job.dto.in.JobIn;
import com.freela.job.dto.out.JobOut;
import com.freela.job.entity.CategoryEntity;
import com.freela.job.repository.CategoryRepository;
import com.freela.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private JobRepository jobRepository;

    public JobOut save(JobIn job, String categoryId) {
        findCategory(categoryId);
        return JobOut.fromEntity(jobRepository.save(job.toEntity(categoryId)));
    }

    public JobOut update(JobIn job, String categoryId, String jobId) {
        findCategory(categoryId);
        return JobOut.fromEntity(jobRepository.save(job.toEntity(categoryId, jobId)));
    }

    public JobOut findById(String id) {
        return jobRepository.findById(id)
                .map(JobOut::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Job not found for id: " + id));
    }

    public List<JobOut> findByCategory(String categoryId) {
        return jobRepository.findByCategoryId(categoryId)
                .stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());
    }

    public List<JobOut> findByName(String name) {
        return jobRepository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());
    }

    private CategoryEntity findCategory(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for ID: " + categoryId));
    }
}
