package com.freela.job.rest;

import com.freela.job.dto.in.CategoryIn;
import com.freela.job.dto.out.CategoryOut;
import com.freela.job.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryRest {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/save")
    public CategoryOut save(@Valid @RequestBody CategoryIn category) {
        return CategoryOut.fromEntity(categoryRepository.save(category.toEntity()));
    }

    @GetMapping("/find-single/{id}")
    public CategoryOut findById(@PathVariable String id) {
        return categoryRepository.findById(id)
                .map(CategoryOut::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + id));
    }

    @GetMapping("/find-all")
    public List<CategoryOut> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-name/{name}")
    public List<CategoryOut> findByName(@PathVariable String name) {
        return categoryRepository.findByNameIgnoreCaseContaining(name).stream()
                .map(CategoryOut::fromEntity)
                .collect(Collectors.toList());
    }

}
