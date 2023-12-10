package ru.den.plannerusers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.den.planner.entity.User;
import ru.den.plannerusers.serch.UserSearchValues;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> findUserById(Long id);

    List<User> findAllUser();

    Optional<User> findUserByEmail(String email);

    void deleteUserById(Long id);

    void deleteUserByEmail(String email);

    User updateUser(User user);

    Page<User> findUserByParams(UserSearchValues userSearchValues);

}
