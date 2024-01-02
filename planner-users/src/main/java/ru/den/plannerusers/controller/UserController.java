package ru.den.plannerusers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.planner.dto.UserDto;
import ru.den.planner.entity.User;
import ru.den.plannerusers.serch.UserSearchValues;
import ru.den.plannerusers.service.UserService;
import ru.den.plannerutils.rest.webclient.UserWebClientBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserWebClientBuilder userWebClientBuilder;

    @PostMapping("/id")
    public ResponseEntity<UserDto> findUserById(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(service.findUserById(id));
        }catch (Exception e){
            return new ResponseEntity("Пользователь не найден", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findUserById() {
        return ResponseEntity.ok(service.findAllUser());
    }

    @PostMapping("/email")
    public ResponseEntity<UserDto> findUserByEmail(@RequestBody String email) {
        try {
            return ResponseEntity.ok(service.findUserByEmail(email));
        }catch (Exception e){
            return new ResponseEntity("Пользователь с таким email  не существует", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            service.addUser(user);
            //заполняем начальные данны пользователя (в параллельном потоке)
            if (user != null){
                userWebClientBuilder.initUserData(user.getId()).subscribe(result -> System.out.println("user populated: " + result));
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.updateUser(user));
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
        } catch (Exception e) {
            return new ResponseEntity<>("Email не существует", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<UserDto>> search(@RequestBody UserSearchValues userSearchValues) {
        if (userSearchValues == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Page<UserDto> result = service.findUserByParams(userSearchValues);

        return ResponseEntity.ok(result);
    }

}
