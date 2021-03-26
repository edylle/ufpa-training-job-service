package com.freela.job.repository;

import com.freela.job.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    List<CategoryEntity> findByNameIgnoreCaseContaining(String name);

}
