package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.TaskDto;
import ru.den.planner.entity.Task;

import java.util.function.Function;

@Component
public class TaskMapper implements Function<Task, TaskDto> {
    @Override
    public TaskDto apply(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .category(task.getCategory())
                .userId(task.getUserId())
                .completed(task.getCompleted())
                .priority(task.getPriority())
                .taskData(task.getTaskData())
                .title(task.getTitle())
                .build();
    }
}
