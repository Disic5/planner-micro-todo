package ru.den.plannertodo.controller;


import ru.den.planner.entity.User;
import ru.den.plannertodo.feign.UserFeignClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.dto.CategoryDto;
import ru.den.planner.entity.Category;
import ru.den.plannertodo.search.CategorySearchValue;
import ru.den.plannertodo.service.CategoryService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

//    private final UserRestBuilder userRestBuilder;

//    private final UserWebClientBuilder userWebClientBuilder;

    private final CategoryService service;

    private final UserFeignClient userFeignClient;

    @PostMapping("/id")
    public ResponseEntity<CategoryDto> findById(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new EntityNotFoundException("Category with this id " + id + " not found");
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> findAllCategory() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/all")
    public ResponseEntity<List<CategoryDto>> findAllCategoryByUserId(@RequestBody Long userId) {
        try {
            return ResponseEntity.ok(service.findAllByUserId(userId));
        } catch (Exception e) {
            throw new EntityNotFoundException("Category with this userId " + userId + " not found");
        }
    }

    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
//        if (userWebClientBuilder.userExists(category.getUserId())) {
//            return ResponseEntity.ok(service.addCategory(category));
//        }
        /**
         // подписываемся на резьтат (ассинхронный вызов)
         userWebClientBuilder.userExistAsync(category.getUserId()).subscribe(user -> System.out.println("user= " + user));
         */

        ResponseEntity<User> result = userFeignClient.findUserById(category.getUserId());

        if (result == null){
            return new ResponseEntity("MC Users не доступен", HttpStatus.NO_CONTENT);
        }
        //вызов мс через feign interface
        if (result.getBody() != null){
            return ResponseEntity.ok(service.addCategory(category));
        }

        return new ResponseEntity("user_id= "+ category.getUserId() + " not found ", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        service.updateCategory(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CategoryDto>> search(@RequestBody CategorySearchValue categorySearchValue) {
        if (categorySearchValue.getUserId() != null && categorySearchValue.getUserId() == 0) {
            return new ResponseEntity("missed param: userId", HttpStatus.NOT_ACCEPTABLE);
        }
        List<CategoryDto> list = service.findByTitle(categorySearchValue.getTitle(), categorySearchValue.getUserId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable Long id) {
        if (Objects.nonNull(service.findById(id))) {
            service.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
