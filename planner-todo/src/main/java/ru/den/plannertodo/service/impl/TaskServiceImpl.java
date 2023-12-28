package ru.den.plannertodo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.den.planner.dto.TaskDto;
import ru.den.planner.entity.Priority;
import ru.den.planner.entity.Task;
import ru.den.planner.mapper.TaskMapper;
import ru.den.plannertodo.repository.TaskRepository;
import ru.den.plannertodo.service.TaskService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    private final TaskMapper taskMapper;


    public List<TaskDto> findByUserId(Long userId) {
        return repository
                .findByUserIdOrderByTitleAsc(userId)
                .stream().map(taskMapper)
                .collect(Collectors.toList());
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task) {
        return repository.save(task);
    }

    public void deleteTaskById(Long id) {
        Optional<Task> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Task with this id " + id + " not found");
        }
    }

    public Optional<TaskDto> findTaskById(Long id) {
        return repository
                .findById(id)
                .map(taskMapper);
    }

    public Page<Task> findByParams(String text, Boolean completed, Long priorityId,
                                   Long categoryId, Long userId,
                                   Date dateFrom, Date dateTo, Pageable paging) {
        return repository.findByParams(text, completed, priorityId, categoryId, userId, dateFrom, dateTo, paging);
    }

    public List<TaskDto> findAllTask(){
        return repository
                .findAll()
                .stream()
                .map(taskMapper)
                .collect(Collectors.toList());
    }
}
