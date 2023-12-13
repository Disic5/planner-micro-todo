package ru.den.plannertodo.service;

import ru.den.planner.dto.CategoryDto;
import ru.den.planner.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto findById(Long id);

    List<CategoryDto> findAllByUserId(Long userId);

    Category addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    List<CategoryDto> findByTitle(String title, Long userId);

    List<CategoryDto> findAll();

}
