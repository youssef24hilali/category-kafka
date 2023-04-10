package com.example.categorybackend.controller;

import com.example.categorybackend.dto.CategoryDto;
import com.example.categorybackend.entity.Category;
import com.example.categorybackend.producer.CategoryProducer;
import com.example.categorybackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryProducer categoryProducer;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        Category category = new Category(categoryDto.getId(), categoryDto.getName());
        categoryProducer.send(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
}
