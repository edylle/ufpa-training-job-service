package com.freela.job.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;
import java.util.UUID;

@Entity(name = "categories")
@Data
@Builder(toBuilder = true)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity {

    @Id
    String categoryId;
    String name;
    String description;

    public static CategoryEntity create(String id, String name, String description) {
        return CategoryEntity.builder()
                .categoryId(id)
                .name(name)
                .description(description)
                .build();
    }

    public static CategoryEntity mock() {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(new Random().nextBoolean() ? UUID.randomUUID().toString() : null)
                .build();
    }
}
