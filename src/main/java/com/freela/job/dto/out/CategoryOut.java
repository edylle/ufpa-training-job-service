package com.freela.job.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freela.job.entity.CategoryEntity;
import lombok.EqualsAndHashCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

@EqualsAndHashCode
@JsonInclude(NON_ABSENT)
public class CategoryOut {

    public final String id;
    public final String name;
    public final String description;

    public CategoryOut(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static CategoryOut fromEntity(CategoryEntity entity) {
        return new CategoryOut(entity.getCategoryId(), entity.getName(), entity.getDescription());
    }
}
