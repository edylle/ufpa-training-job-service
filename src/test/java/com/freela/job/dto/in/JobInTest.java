package com.freela.job.dto.in;

import com.freela.job.entity.JobEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class JobInTest {

    @Test
    public void toEntity_withCategoryId_transformsCorrectly() {
        String categoryId = UUID.randomUUID().toString();

        JobIn dto = JobIn.mock();
        JobEntity result = dto.toEntity(categoryId);

        assertThat(result.getJobId()).isNotNull();
        assertThat(result.getCategoryId()).isEqualTo(categoryId);
        assertThat(result.getName()).isEqualTo(dto.name);
        assertThat(result.getDescription()).isEqualTo(dto.description);
        assertThat(result.getBudgetMin()).isEqualTo(dto.budgetMin);
        assertThat(result.getBudgetMax()).isEqualTo(dto.budgetMax);
    }

    @Test
    public void toEntity_withCategoryId_andJobId_transformsCorrectly() {
        String categoryId = UUID.randomUUID().toString();
        String jobId = UUID.randomUUID().toString();

        JobIn dto = JobIn.mock();
        JobEntity result = dto.toEntity(categoryId, jobId);

        assertThat(result.getJobId()).isEqualTo(jobId);
        assertThat(result.getCategoryId()).isEqualTo(categoryId);
        assertThat(result.getName()).isEqualTo(dto.name);
        assertThat(result.getDescription()).isEqualTo(dto.description);
        assertThat(result.getBudgetMin()).isEqualTo(dto.budgetMin);
        assertThat(result.getBudgetMax()).isEqualTo(dto.budgetMax);
    }

}
