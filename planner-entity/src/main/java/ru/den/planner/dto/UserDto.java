package ru.den.planner.dto;

import lombok.*;
import ru.den.planner.entity.Role;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private Set<Role> roles;
}
