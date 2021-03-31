package com.freela.job.service;

import com.freela.job.dto.in.JobIn;
import com.freela.job.dto.out.JobOut;
import com.freela.job.entity.CategoryEntity;
import com.freela.job.entity.JobEntity;
import com.freela.job.repository.CategoryRepository;
import com.freela.job.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class JobServiceTest {

    @Autowired
    private JobService service;

    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private JobRepository jobRepository;

    @Test
    public void save_whenCategoryIsNotFound_throwsEntityNotFoundException() {
        String categoryId = UUID.randomUUID().toString();
        JobIn job = JobIn.mock();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        try {
            service.save(job, categoryId);
            fail();
        } catch (EntityNotFoundException e) {
            // ignored
        }

        verifyNoInteractions(jobRepository);
    }

    @Test
    public void save_callsRepository() {
        String categoryId = UUID.randomUUID().toString();
        CategoryEntity categoryEntity = CategoryEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobIn job = JobIn.mock();
        JobEntity jobEntity = job.toEntity(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(jobRepository.save(any())).thenReturn(jobEntity);

        JobOut result = service.save(job, categoryId);
        JobOut expected = JobOut.fromEntity(jobEntity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void update_whenCategoryIsNotFound_throwsEntityNotFoundException() {
        String categoryId = UUID.randomUUID().toString();
        String jobId = UUID.randomUUID().toString();
        JobIn job = JobIn.mock();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        try {
            service.update(job, categoryId, jobId);
            fail();
        } catch (EntityNotFoundException e) {
            // ignored
        }

        verifyNoInteractions(jobRepository);
    }

    @Test
    public void update_callsRepository() {
        String categoryId = UUID.randomUUID().toString();
        String jobId = UUID.randomUUID().toString();
        CategoryEntity categoryEntity = CategoryEntity.mock().toBuilder()
                .categoryId(categoryId)
                .build();
        JobIn job = JobIn.mock();
        JobEntity jobEntity = job.toEntity(categoryId, jobId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(jobRepository.save(jobEntity)).thenReturn(jobEntity);

        JobOut result = service.update(job, categoryId, jobId);
        JobOut expected = JobOut.fromEntity(jobEntity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findById_whenJobIsNotFound_throwsEntityNotFoundException() {
        String jobId = UUID.randomUUID().toString();

        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(jobId));
    }

    @Test
    public void findById_callsRepository() {
        String jobId = UUID.randomUUID().toString();
        JobEntity jobEntity = JobEntity.mock().toBuilder()
                .jobId(jobId)
                .build();

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(jobEntity));

        JobOut result = service.findById(jobId);
        JobOut expected = JobOut.fromEntity(jobEntity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findByCategory_whenNoJobsAreFound_returnsEmpty() {
        String categoryId = UUID.randomUUID().toString();

        when(jobRepository.findByCategoryId(categoryId)).thenReturn(Collections.emptyList());
        List<JobOut> result = service.findByCategory(categoryId);

        assertThat(result).isEmpty();
    }

    @Test
    public void findByCategory_returnsCorrectJobs() {
        String categoryId = UUID.randomUUID().toString();
        List<JobEntity> jobs = Stream.generate(JobEntity::mock)
                .limit(3)
                .collect(Collectors.toList());

        when(jobRepository.findByCategoryId(categoryId)).thenReturn(jobs);

        List<JobOut> result = service.findByCategory(categoryId);
        List<JobOut> expected = jobs
                .stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());

        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findByName_whenNoJobsAreFound_returnsEmpty() {
        String name = UUID.randomUUID().toString();

        when(jobRepository.findByNameIgnoreCaseContaining(name)).thenReturn(Collections.emptyList());
        List<JobOut> result = service.findByName(name);

        assertThat(result).isEmpty();
    }

    @Test
    public void findByName_returnsCorrectJobs() {
        String name = UUID.randomUUID().toString();
        List<JobEntity> jobs = Stream.generate(JobEntity::mock)
                .limit(3)
                .collect(Collectors.toList());

        when(jobRepository.findByNameIgnoreCaseContaining(name)).thenReturn(jobs);

        List<JobOut> result = service.findByName(name);
        List<JobOut> expected = jobs
                .stream()
                .map(JobOut::fromEntity)
                .collect(Collectors.toList());

        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }
}
