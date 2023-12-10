package ru.den.plannerusers.service;

import org.springframework.data.domain.Page;
import ru.den.planner.dto.UserDto;
import ru.den.planner.entity.User;
import ru.den.plannerusers.serch.UserSearchValues;

import java.util.List;

public interface UserService {

    void addUser(User user);

    UserDto findUserById(Long id);

    List<UserDto> findAllUser();

    UserDto findUserByEmail(String email);

    void deleteUserById(Long id);

    void deleteUserByEmail(String email);

    User updateUser(User user);

    Page<UserDto> findUserByParams(UserSearchValues userSearchValues);

}
