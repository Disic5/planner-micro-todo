package ru.den.plannertodo.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.den.planner.dto.CategoryDto;
import ru.den.planner.entity.Category;
import ru.den.planner.mapper.CategoryMapper;
import ru.den.plannertodo.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository repository;

    public CategoryDto findById(Long id) {
        return repository
                .findById(id)
                .map(categoryMapper)
                .orElseThrow(() -> new EntityNotFoundException("Категория с id [%s] не найдена".formatted(id)));
    }

    public List<CategoryDto> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .filter(Objects::nonNull)
                .map(categoryMapper)
                .collect(Collectors.toList());
    }

    public Category addCategory(Category category) {
        return repository.save(category);
    }

    public void updateCategory(Category category) {
        repository.save(category);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public List<CategoryDto> findByTitle(String title, Long userId) {
        return repository.findByTitle(title, userId)
                .stream()
                .filter(Objects::nonNull)
                .map(categoryMapper)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> findAll() {
        return repository.findAll()
                .stream()
                .map(categoryMapper)
                .collect(Collectors.toList());
    }

}
