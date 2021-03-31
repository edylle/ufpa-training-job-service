package com.freela.job.rest;

import com.freela.job.dto.in.CategoryIn;
import com.freela.job.dto.out.CategoryOut;
import com.freela.job.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public CategoryOut save(@Valid @RequestBody CategoryIn category) {
        return categoryService.save(category);
    }

    @PutMapping("/update/{id}")
    public CategoryOut update(@PathVariable String id, @Valid @RequestBody CategoryIn category) {
        return categoryService.update(category, id);
    }

    @GetMapping("/find-single/{id}")
    public CategoryOut findById(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @GetMapping("/find-all")
    public List<CategoryOut> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/find-by-name/{name}")
    public List<CategoryOut> findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

}
