//package ru.den.plannertodo.service.impl;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.den.planner.dto.CategoryDto;
//import ru.den.planner.entity.Category;
//import ru.den.planner.mapper.CategoryMapper;
//import ru.den.plannertodo.repository.CategoryRepository;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceImplTest {
//
//    @Mock
//    private CategoryRepository repository;
//
//    @InjectMocks
//    CategoryServiceImpl service;
//
//    @Mock
//    private CategoryMapper mapper;
//
//    @Test
//    void findById() {
//        Long id = 1L;
//        Category category = new Category();
//        category.setId(id);
//        category.setTitle("title");
//        mapper.apply(category);
//        when(repository.findById(id)).thenReturn(Optional.of(category));
//
//        CategoryDto dto = service.findById(id);
//
//        assertEquals(dto.getId(), category.getId());
//        verify(repository, times(1)).findById(id);
//    }
//
//    @Test
//    void findAllByUserId() {
//    }
//
//    @Test
//    void addCategory() {
//    }
//
//    @Test
//    void updateCategory() {
//    }
//
//    @Test
//    void deleteCategory() {
//    }
//
//    @Test
//    void findByTitle() {
//    }
//
//    @Test
//    void findAll() {
//    }
//}