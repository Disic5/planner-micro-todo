package ru.den.planner.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatDto {

    private Long id;

    private Long completedTotal;

    private Long uncompletedTotal;

    private Long userId;

}
