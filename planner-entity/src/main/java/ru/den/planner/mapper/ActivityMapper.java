package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.ActivityDto;
import ru.den.planner.entity.Activity;

import java.util.function.Function;

@Component
public class ActivityMapper implements Function<Activity, ActivityDto> {
    @Override
    public ActivityDto apply(Activity activity) {
        return  ActivityDto.builder()
                .id(activity.getId())
                .activated(activity.getActivated())
                .userId(activity.getUserId())
                .build();
    }
}
