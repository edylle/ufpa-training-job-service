package com.freela.job.service;

import com.freela.job.dto.in.CategoryIn;
import com.freela.job.dto.out.CategoryOut;
import com.freela.job.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryOut save(CategoryIn category) {
        return CategoryOut.fromEntity(categoryRepository.save(category.toEntity()));
    }

    public CategoryOut update(CategoryIn category, String id) {
        return CategoryOut.fromEntity(categoryRepository.save(category.toEntity(id)));
    }

    public CategoryOut findById(String id) {
        return categoryRepository.findById(id)
                .map(CategoryOut::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + id));
    }

    public List<CategoryOut> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());
    }

    public List<CategoryOut> findByName(String name) {
        return categoryRepository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());
    }

}
