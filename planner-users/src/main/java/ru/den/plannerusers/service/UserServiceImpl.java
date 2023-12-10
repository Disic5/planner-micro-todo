package ru.den.plannerusers.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.User;
import ru.den.plannerusers.repository.UserRepository;
import ru.den.plannerusers.serch.UserSearchValues;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ID_COLUMN = "id";

    @Autowired
    private final UserRepository repository;

    @Transactional
    public void addUser(User user) {
        repository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Пользователь с таким email уже сужествует");
                });
        repository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            throw new EntityNotFoundException("Пользователь не найден с id " + id);
        }
    }

    public List<User> findAllUser() {
        return repository.findAll();
    }


    public Optional<User> findUserByEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser;
        } else {
            throw new EntityNotFoundException("Пользователь не найден с email " + email);
        }
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
    public Page<User> findUserByParams(UserSearchValues userSearchValues) {
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

        return repository.findByParams(email, name, pageRequest);
    }
}
