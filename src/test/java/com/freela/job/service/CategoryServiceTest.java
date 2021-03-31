package com.freela.job.service;

import com.freela.job.dto.in.CategoryIn;
import com.freela.job.dto.out.CategoryOut;
import com.freela.job.entity.CategoryEntity;
import com.freela.job.repository.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void save_callsRepository() {
        CategoryIn category = CategoryIn.mock();
        CategoryEntity entity = category.toEntity();

        when(categoryRepository.save(any())).thenReturn(entity);

        CategoryOut result = service.save(category);
        CategoryOut expected = CategoryOut.fromEntity(entity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void update_callsRepository() {
        String id = UUID.randomUUID().toString();
        CategoryIn category = CategoryIn.mock();
        CategoryEntity entity = category.toEntity(id);

        when(categoryRepository.save(entity)).thenReturn(entity);

        CategoryOut result = service.update(category, id);
        CategoryOut expected = CategoryOut.fromEntity(entity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findById_whenCategoryIsNotFound_throwsEntityNotFoundException() {
        String id = UUID.randomUUID().toString();

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }

    @Test
    public void findById_callsRepository() {
        String id = UUID.randomUUID().toString();
        CategoryEntity entity = CategoryEntity.mock().toBuilder()
                .categoryId(id)
                .build();

        when(categoryRepository.findById(id)).thenReturn(Optional.of(entity));

        CategoryOut result = service.findById(id);
        CategoryOut expected = CategoryOut.fromEntity(entity);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findAll_whenNoCategoriesAreFound_returnsEmpty() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
        List<CategoryOut> result = service.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    public void findAll_returnsCorrectCategories() {
        List<CategoryEntity> categories = Stream.generate(CategoryEntity::mock)
                .limit(3)
                .collect(Collectors.toList());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryOut> result = service.findAll();
        List<CategoryOut> expected = categories
                .stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());

        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findByName_whenNoCategoriesAreFound_returnsEmpty() {
        String name = UUID.randomUUID().toString();

        when(categoryRepository.findByNameIgnoreCaseContaining(name)).thenReturn(Collections.emptyList());
        List<CategoryOut> result = service.findByName(name);

        assertThat(result).isEmpty();
    }

    @Test
    public void findByName_returnsCorrectCategories() {
        String name = UUID.randomUUID().toString();
        List<CategoryEntity> categories = Stream.generate(CategoryEntity::mock)
                .limit(3)
                .collect(Collectors.toList());

        when(categoryRepository.findByNameIgnoreCaseContaining(name)).thenReturn(categories);

        List<CategoryOut> result = service.findByName(name);
        List<CategoryOut> expected = categories
                .stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());

        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }
}
