package com.freela.job.dto.out;

import com.freela.job.entity.JobEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JobOutTest {

    @Test
    public void fromEntity_transformsCorrectly() {
        JobEntity entity = JobEntity.mock();
        JobOut result = JobOut.fromEntity(entity);

        assertThat(result.JobId).isEqualTo(entity.getJobId());
        assertThat(result.categoryId).isEqualTo(entity.getCategoryId());
        assertThat(result.name).isEqualTo(entity.getName());
        assertThat(result.description).isEqualTo(entity.getDescription());
        assertThat(result.budgetMin).isEqualTo(entity.getBudgetMin());
        assertThat(result.budgetMax).isEqualTo(entity.getBudgetMax());
    }

}
