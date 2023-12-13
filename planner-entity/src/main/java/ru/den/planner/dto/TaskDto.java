package ru.den.planner.dto;

import lombok.*;
import ru.den.planner.entity.Category;
import ru.den.planner.entity.Priority;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long id;

    private String title;

    private Boolean completed;

    private Date taskData;

    private Long userId;

    private Category category;

    private Priority priority;

}
