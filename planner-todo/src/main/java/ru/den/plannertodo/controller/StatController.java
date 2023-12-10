package ru.den.plannertodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.den.planner.entity.Stat;
import ru.den.plannertodo.service.StatService;

@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private StatService service;

    @PostMapping
    public ResponseEntity<Stat> findByUserId(@RequestBody Long userId){
        return ResponseEntity.ok(service.findStatByUserId(userId));
    }
}
