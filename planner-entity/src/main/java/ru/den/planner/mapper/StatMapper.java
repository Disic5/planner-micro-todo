package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.StatDto;
import ru.den.planner.entity.Stat;

import java.util.function.Function;

@Component
public class StatMapper implements Function<Stat, StatDto> {
    @Override
    public StatDto apply(Stat stat) {
        return StatDto.builder()
                .id(stat.getId())
                .completedTotal(stat.getCompletedTotal())
                .uncompletedTotal(stat.getUncompletedTotal())
                .userId(stat.getUserId())
                .build();
    }
}
