package ru.den.plannertodo.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.den.planner.dto.StatDto;
import ru.den.planner.entity.Stat;
import ru.den.plannertodo.service.impl.StatServiceImpl;

@RestController
@RequestMapping("/stat")
@RequiredArgsConstructor
public class StatController {

    private final StatServiceImpl service;

    @PostMapping
    public ResponseEntity<StatDto> findByUserId(@RequestBody Long userId){
        try {
            return ResponseEntity.ok(service.findStatByUserId(userId));
        }catch (Exception e){
            throw new EntityNotFoundException("userId не существует " + userId);
        }

    }
}
