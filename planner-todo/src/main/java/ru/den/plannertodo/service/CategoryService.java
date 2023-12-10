package ru.den.plannertodo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.den.planner.entity.Category;
import ru.den.plannertodo.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public List<Category> findAllByUserId(Long userId){
        return repository.findAllByUserId(userId);
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

    public List<Category> findByTitle(String title, Long userId) {
        return repository.findByTitle(title, userId);
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

}
