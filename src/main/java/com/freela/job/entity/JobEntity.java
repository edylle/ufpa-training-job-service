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

@Entity(name = "jobs")
@Data
@Builder(toBuilder = true)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JobEntity {

    @Id
    String jobId;
    String categoryId;
    String name;
    String description;
    int budgetMin;
    int budgetMax;

    public static JobEntity create(String jobId,
                                   String categoryId,
                                   String name,
                                   String description,
                                   int budgetMin,
                                   int budgetMax) {
        return JobEntity.builder()
                .jobId(jobId)
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .budgetMin(budgetMin)
                .budgetMax(budgetMax)
                .build();
    }

    public static JobEntity mock() {
        int min = new Random().nextInt(1_000);
        int max = min + new Random().nextInt(1_000);

        return JobEntity.builder()
                .jobId(UUID.randomUUID().toString())
                .categoryId(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .description(new Random().nextBoolean() ? UUID.randomUUID().toString() : null)
                .budgetMin(min)
                .budgetMax(max)
                .build();
    }
}
