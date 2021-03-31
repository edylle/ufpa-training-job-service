package com.freela.job.repository;

import com.freela.job.entity.JobEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository repository;

    @Test
    public void findByNameIgnoreCaseContaining_returnsCorrectCategories() {
        String name = UUID.randomUUID().toString().substring(10);

        JobEntity job1 = JobEntity.mock().toBuilder()
                .name(UUID.randomUUID().toString() + name + UUID.randomUUID().toString())
                .build();
        JobEntity job2 = JobEntity.mock().toBuilder()
                .name(UUID.randomUUID().toString() + name)
                .build();
        JobEntity job3 = JobEntity.mock().toBuilder()
                .name(name + UUID.randomUUID().toString())
                .build();
        JobEntity job4 = JobEntity.mock().toBuilder()
                .name(name)
                .build();
        JobEntity job5 = JobEntity.mock();

        repository.saveAll(List.of(job1, job2, job3, job4, job5));
        List<JobEntity> result = repository.findByNameIgnoreCaseContaining(name);

        assertThat(result).containsAnyOf(job1, job2, job3, job4);
    }

    @Test
    public void findByCategoryId_returnsCorrectCategories() {
        String categoryId = UUID.randomUUID().toString();

        JobEntity job1 = JobEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobEntity job2 = JobEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobEntity job3 = JobEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobEntity job4 = JobEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobEntity job5 = JobEntity.mock();

        repository.saveAll(List.of(job1, job2, job3, job4, job5));
        List<JobEntity> result = repository.findByCategoryId(categoryId);

        assertThat(result).containsAnyOf(job1, job2, job3, job4);
    }
}
