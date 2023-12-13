package ru.den.planner.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityDto {

    private Long id;

    private Boolean activated;

    private Long userId;
}
