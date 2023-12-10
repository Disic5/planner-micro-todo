package ru.den.planner.mapper;

import org.springframework.stereotype.Component;
import ru.den.planner.dto.UserDto;
import ru.den.planner.entity.User;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return  UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles())
                .build();
    }
}