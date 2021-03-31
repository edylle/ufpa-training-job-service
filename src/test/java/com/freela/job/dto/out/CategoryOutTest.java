package com.freela.job.dto.out;

import com.freela.job.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryOutTest {

    @Test
    public void fromEntity_transformsCorrectly() {
        CategoryEntity entity = CategoryEntity.mock();
        CategoryOut result = CategoryOut.fromEntity(entity);

        assertThat(result.id).isEqualTo(entity.getCategoryId());
        assertThat(result.name).isEqualTo(entity.getName());
        assertThat(result.description).isEqualTo(entity.getDescription());
    }

}
