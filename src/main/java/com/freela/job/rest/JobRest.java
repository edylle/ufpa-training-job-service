package com.freela.job.rest;

import com.freela.job.dto.in.JobIn;
import com.freela.job.dto.out.JobOut;
import com.freela.job.repository.JobRepository;
import com.freela.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobs")
public class JobRest {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/save/{categoryId}")
    public JobOut save(@PathVariable String categoryId, @Valid @RequestBody JobIn job) {
        return JobOut.fromEntity(jobService.save(job.toEntity(categoryId)));
    }

    @PutMapping("/update/{categoryId}/{jobIdd}")
    public JobOut update(@PathVariable String categoryId, @PathVariable String jobId, @Valid @RequestBody JobIn job) {
        return JobOut.fromEntity(jobRepository.save(job.toEntity(categoryId, jobId)));
    }

    @GetMapping("/find-single/{id}")
    public JobOut findById(@PathVariable String id) {
        return jobRepository.findById(id)
                .map(JobOut::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Job not found for id: " + id));
    }

    @GetMapping("/find-all")
    public List<JobOut> findAll() {
        return jobRepository.findAll().stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-name/{name}")
    public List<JobOut> findByName(@PathVariable String name) {
        return jobRepository.findByNameIgnoreCaseContaining(name).stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());
    }

}
