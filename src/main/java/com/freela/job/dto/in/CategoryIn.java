package com.freela.job.dto.in;

import com.freela.job.entity.CategoryEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Random;
import java.util.UUID;

public class CategoryIn {

    @NotBlank(message = "Missing required field: name")
    @Size(max = 100, message = "name must be at max: {max} characters")
    public final String name;

    @Size(max = 255, message = "name must be at max: {max} characters")
    public final String description;

    public CategoryIn(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryEntity toEntity() {
        return toEntity(UUID.randomUUID().toString());
    }

    public CategoryEntity toEntity(String id) {
        return CategoryEntity.create(
                id,
                name,
                description
        );
    }

    public static CategoryIn mock() {
        return new CategoryIn(
                UUID.randomUUID().toString(),
                new Random().nextBoolean() ? UUID.randomUUID().toString() : null
        );
    }
}
