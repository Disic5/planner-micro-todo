package ru.den.plannertodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.den.plannertodo.service.TestDataService;

/**
 *  Будет вызываться при добавление нового пользователя
 * */

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class TestDataController {

    private final TestDataService service;

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody Long userId){
        service.init(userId);
        return ResponseEntity.ok(true);
    }
}
