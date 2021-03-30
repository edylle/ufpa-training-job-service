package com.freela.job.dto.in;

import com.freela.job.entity.JobEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class JobIn {

    @NotBlank(message = "Missing required field: name")
    @Size(max = 100, message = "name must be at max: {max} characters")
    public final String name;

    @NotBlank(message = "Missing required field: description")
    @Size(max = 1000, message = "name must be at max: {max} characters")
    public final String description;

    @Min(value = 0, message = "Minimum value for budgetMin: 0")
    @Max(value = Integer.MAX_VALUE, message = "Maximum value for budgetMin: " + Integer.MAX_VALUE)
    public final Integer budgetMin;

    @Max(value = Integer.MAX_VALUE, message = "Maximum value for budgetMin: " + Integer.MAX_VALUE)
    public final Integer budgetMax;

    public JobIn(String name, String description, Integer budgetMin, Integer budgetMax) {
        this.name = name;
        this.description = description;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
    }

    public JobEntity toEntity(String categoryId) {
        return toEntity(categoryId, UUID.randomUUID().toString());
    }

    public JobEntity toEntity(String categoryId, String jobId) {
        return JobEntity.create(
                categoryId,
                jobId,
                name,
                description,
                budgetMin,
                budgetMax
        );
    }
}
