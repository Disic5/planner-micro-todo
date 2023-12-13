package ru.den.plannertodo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.Stat;
import ru.den.plannertodo.repository.StatRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StatService{

    private final StatRepository repository;

    public Stat findStatByUserId(Long userId){
        return repository.findByUserId(userId);
    }
}
