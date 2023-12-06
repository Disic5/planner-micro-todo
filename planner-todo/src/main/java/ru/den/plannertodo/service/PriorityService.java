package ru.den.plannertodo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.Priority;
import ru.den.plannertodo.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PriorityService {
    private final PriorityRepository repository;

    public Optional<Priority> findById(Long id) {
        return repository.findById(id);
    }

    public Priority addPriority(Priority priority) {
        return repository.save(priority);
    }

    public void updatePriority(Priority priority) {
        repository.save(priority);
    }

    public void deletePriority(Long id) {
        repository.deleteById(id);
    }

    public List<Priority> findByTitle(String title, Long userId) {
        return repository.findByTitle(title, userId);
    }

    public List<Priority> findAllPriority() {
        return repository.findAll();
    }
}

