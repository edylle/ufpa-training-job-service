package com.freela.job.dto.in;

import com.freela.job.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryInTest {

    @Test
    public void toEntity_transformsCorrectly() {
        CategoryIn dto = CategoryIn.mock();
        CategoryEntity result = dto.toEntity();

        assertThat(result.getCategoryId()).isNotNull();
        assertThat(result.getName()).isEqualTo(dto.name);
        assertThat(result.getDescription()).isEqualTo(dto.description);
    }

    @Test
    public void toEntity_withId_transformsCorrectly() {
        String categoryId = UUID.randomUUID().toString();

        CategoryIn dto = CategoryIn.mock();
        CategoryEntity result = dto.toEntity(categoryId);

        assertThat(result.getCategoryId()).isEqualTo(categoryId);
        assertThat(result.getName()).isEqualTo(dto.name);
        assertThat(result.getDescription()).isEqualTo(dto.description);
    }

}
