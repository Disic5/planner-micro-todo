package ru.den.plannertodo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.Stat;
import ru.den.plannertodo.repository.StatRepository;

@Service
@Transactional
public class StatService{
    @Autowired
    private StatRepository repository;

    public Stat findStatByUserId(Long userId){
        return repository.findByUserId(userId);
    }
}
