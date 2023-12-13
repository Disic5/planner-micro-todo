package ru.den.planner.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;

    private String title;

    private Integer completedCount;

    private Integer uncompletedCount;

    private Long userId;

}
