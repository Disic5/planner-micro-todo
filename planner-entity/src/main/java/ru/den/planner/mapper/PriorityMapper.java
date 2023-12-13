package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.PriorityDto;
import ru.den.planner.entity.Priority;

import java.util.function.Function;

@Component
public class PriorityMapper implements Function<Priority, PriorityDto> {
    @Override
    public PriorityDto apply(Priority priority) {
        return PriorityDto.builder()
                .id(priority.getId())
                .title(priority.getTitle())
                .color(priority.getColor())
                .userId(priority.getUserId())
                .build();
    }
}
