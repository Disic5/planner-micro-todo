package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.CategoryDto;
import ru.den.planner.entity.Category;

import java.util.function.Function;

@Component
public class CategoryMapper implements Function<Category, CategoryDto> {

    @Override
    public CategoryDto apply(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .completedCount(category.getCompletedCount())
                .uncompletedCount(category.getUncompletedCount())
                .userId(category.getUserId())
                .build();
    }
}
