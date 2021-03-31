package com.freela.job.rest;

import com.freela.job.dto.in.JobIn;
import com.freela.job.dto.out.JobOut;
import com.freela.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobRest {

    @Autowired
    private JobService jobService;

    @PostMapping("/save/{categoryId}")
    public JobOut save(@PathVariable String categoryId, @Valid @RequestBody JobIn job) {
        return jobService.save(job, categoryId);
    }

    @PutMapping("/update/{categoryId}/{jobIdd}")
    public JobOut update(@PathVariable String categoryId, @PathVariable String jobId, @Valid @RequestBody JobIn job) {
        return jobService.update(job, categoryId, jobId);
    }

    @GetMapping("/find-single/{id}")
    public JobOut findById(@PathVariable String id) {
        return jobService.findById(id);
    }

    @GetMapping("/find-by-category/{categoryId}")
    public List<JobOut> findAll(@PathVariable String categoryId) {
        return jobService.findByCategory(categoryId);
    }

    @GetMapping("/find-by-name/{name}")
    public List<JobOut> findByName(@PathVariable String name) {
        return jobService.findByName(name);
    }

}
