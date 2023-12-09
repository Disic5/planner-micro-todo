package ru.den.plannerusers.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.entity.User;
import ru.den.plannerusers.service.UserService;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService service;

    @PostMapping("/id")
    public ResponseEntity<Optional<User>> findUserById(@RequestBody Long id) {
        return ResponseEntity.ok(service.findUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findUserById() {
        return ResponseEntity.ok(service.findAllUser());
    }

    @PostMapping("/email")
    public ResponseEntity<Optional<User>> findUserByEmail(@RequestBody String email) {
        return ResponseEntity.ok( service.findUserByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            service.addUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/email")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> deleteUserById(@RequestBody String email) {
        try {
            service.deleteUserByEmail(email);
        } catch (Exception e){
           return new ResponseEntity<>("Email не существует", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }


}
