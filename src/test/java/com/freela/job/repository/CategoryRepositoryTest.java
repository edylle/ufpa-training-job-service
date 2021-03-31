package com.freela.job.repository;

import com.freela.job.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findByNameIgnoreCaseContaining_returnsCorrectCategories() {
        String name = UUID.randomUUID().toString().substring(10);

        CategoryEntity category1 = CategoryEntity.mock().toBuilder()
                .name(UUID.randomUUID().toString() + name + UUID.randomUUID().toString())
                .build();
        CategoryEntity category2 = CategoryEntity.mock().toBuilder()
                .name(UUID.randomUUID().toString() + name)
                .build();
        CategoryEntity category3 = CategoryEntity.mock().toBuilder()
                .name(name + UUID.randomUUID().toString())
                .build();
        CategoryEntity category4 = CategoryEntity.mock().toBuilder()
                .name(name)
                .build();
        CategoryEntity category5 = CategoryEntity.mock();

        repository.saveAll(List.of(category1, category2, category3, category4, category5));
        List<CategoryEntity> result = repository.findByNameIgnoreCaseContaining(name);

        assertThat(result).containsAnyOf(category1, category2, category3, category4);
    }

}
