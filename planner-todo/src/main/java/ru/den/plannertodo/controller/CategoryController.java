package ru.den.plannertodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.entity.Category;
import ru.den.plannertodo.search.CategorySearchValue;
import ru.den.plannertodo.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/id")
    public ResponseEntity<Category> findById(@RequestBody Long id) {
        Optional<Category> category = service.findById(id);
        if (category.isPresent()){
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }else {
            throw new NoSuchElementException("Category with this id " + id + " not found");
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<Category>> findAll(@RequestBody Long userId){
        return ResponseEntity.ok(service.findAll(userId));
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.addCategory(category));
    }

    @PutMapping(value = "/update", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        //  проверкм на обязательные параметры
//        if (category != null && category.getId() != 0) {
//            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
//        }
//        //если передали пустое значение title
//        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
//            return new ResponseEntity("Title must be not null", HttpStatus.NOT_ACCEPTABLE);
//        }
        service.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValue categorySearchValue) {
        if (categorySearchValue.getUserId() != null && categorySearchValue.getUserId() == 0) {
            return new ResponseEntity("missed param: userId", HttpStatus.NOT_ACCEPTABLE);
        }
        List<Category> list = service.findByTitle(categorySearchValue.getTitle(), categorySearchValue.getUserId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePriority(@PathVariable Long id) {
        if(service.findById(id).isPresent()){
            service.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
