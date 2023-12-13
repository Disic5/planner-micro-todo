package ru.den.planner.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriorityDto {

    private Long id;

    private String title;

    private String color;

    private Long userId;

}
