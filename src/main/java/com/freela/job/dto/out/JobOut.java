package com.freela.job.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freela.job.entity.JobEntity;
import lombok.EqualsAndHashCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@EqualsAndHashCode
@JsonInclude(NON_ABSENT)
public class JobOut {

    public final String JobId;
    public final String categoryId;
    public final String name;
    public final String description;
    public final Integer budgetMin;
    public final Integer budgetMax;

    public JobOut(String jobId,
                  String categoryId,
                  String name,
                  String description,
                  Integer budgetMin,
                  Integer budgetMax) {
        JobId = jobId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
    }

    public static JobOut fromEntity(JobEntity entity) {
        return new JobOut(entity.getJobId(),
                          entity.getCategoryId(),
                          entity.getName(),
                          entity.getDescription(),
                          entity.getBudgetMin(),
                          entity.getBudgetMax());
    }
}
