package ru.den.plannerusers.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.User;
import ru.den.plannerusers.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

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
}
