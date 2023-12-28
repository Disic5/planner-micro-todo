package ru.den.plannertodo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.den.planner.dto.StatDto;
import ru.den.planner.entity.Stat;
import ru.den.planner.mapper.StatMapper;
import ru.den.plannertodo.repository.StatRepository;
import ru.den.plannertodo.service.StatService;

@Service
@Transactional
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository repository;

    private final StatMapper statMapper;


    public StatDto findStatByUserId(Long userId){
        return repository
                .findByUserId(userId)
                .map(statMapper)
                .orElseThrow(() -> new EntityNotFoundException("Stat with userId: " + userId + " not found"));
    }
}
