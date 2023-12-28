package ru.den.plannertodo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.den.planner.dto.PriorityDto;
import ru.den.planner.entity.Priority;
import ru.den.planner.mapper.PriorityMapper;
import ru.den.plannertodo.repository.PriorityRepository;
import ru.den.plannertodo.service.PriorityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository repository;

    private final PriorityMapper priorityMapper;

    public PriorityDto findById(Long id) {
        return repository.findById(id)
                .map(priorityMapper)
                .orElseThrow(() -> new EntityNotFoundException("Priority with this id " + id + " not found"));
    }

    public Priority addPriority(Priority priority) {
        return repository.save(priority);
    }

    public void updatePriority(Priority priority) {
        repository.save(priority);
    }

    public void deletePriority(Long id) {
        Optional<Priority> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Priority with this id " + id + " not found");
        }
    }

    public List<PriorityDto> findByTitle(String title, Long userId) {
        return repository.findByTitle(title, userId)
                .stream()
                .map(priorityMapper)
                .collect(Collectors.toList());
    }

    public List<PriorityDto> findAllPriority() {
        return repository.findAll()
                .stream()
                .map(priorityMapper)
                .collect(Collectors.toList());
    }
}

