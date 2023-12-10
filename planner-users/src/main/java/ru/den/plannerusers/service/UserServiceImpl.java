package ru.den.plannerusers.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.den.planner.dto.UserDto;
import ru.den.planner.entity.User;
import ru.den.planner.mapper.UserMapper;
import ru.den.plannerusers.repository.UserRepository;
import ru.den.plannerusers.serch.UserSearchValues;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ID_COLUMN = "id";

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper userMapper;

    @Transactional
    public void addUser(User user) {
        repository.findByEmail(user.getEmail())
                  .ifPresent(u -> {
                    throw new IllegalArgumentException("Пользователь с таким email уже сужествует");
                });
        repository.save(user);
    }

    public UserDto findUserById(Long id) {
        return repository.findById(id)
                .map(userMapper)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с id " + id));
    }

    public List<UserDto> findAllUser() {
        return repository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }


    public UserDto findUserByEmail(String email) {
        return repository.findByEmail(email)
                .map(userMapper)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с email " + email));

    }

    @Transactional
    public void deleteUserById(Long id) {
        Optional<User> userId = repository.findById(id);
        if (userId.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Пользователь не найден с id " + id);
        }
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            repository.deleteByEmail(email);
        } else {
            throw new EntityNotFoundException("Пользователь не найден с email " + email);
        }
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public Page<UserDto> findUserByParams(UserSearchValues userSearchValues) {
        String email = userSearchValues.getEmail() != null ? userSearchValues.getEmail() : null;
        String name = userSearchValues.getName() != null ? userSearchValues.getName() : null;

        String sortColumn = userSearchValues.getSortColumn() != null ? userSearchValues.getSortColumn() : null;
        String sortDirection = userSearchValues.getSortDirection() != null ? userSearchValues.getSortDirection() : null;
        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : null;
        Integer pageNumber = userSearchValues.getPageNumber() != null ? userSearchValues.getPageNumber() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        return repository.findByParams(email, name, pageRequest).map(userMapper);
    }
}
