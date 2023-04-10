package com.example.categorybackend.service;

import com.example.categorybackend.dto.CategoryDto;
import com.example.categorybackend.entity.Category;
import com.example.categorybackend.repository.original.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository originalCategoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category(null, categoryDto.getName());
        category = originalCategoryRepository.save(category);
        //kafkaCategoryRepository.save(new Category(category.getId(), category.getName()));
        return new CategoryDto(category.getId(), category.getName());
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = this.originalCategoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories) {
            categoryDtos.add(this.getDto(category));
        }
        return categoryDtos;
    }

    private CategoryDto getDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
