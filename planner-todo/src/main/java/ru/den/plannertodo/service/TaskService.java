package ru.den.plannertodo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.Task;
import ru.den.plannertodo.repository.TaskRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public List<Task> findByUserId(Long userId) {
        return repository.findByUserIdOrderByTitleAsc(userId);
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task) {
        return repository.save(task);
    }

    public void deleteTaskById(Long id) {
        repository.deleteById(id);
    }

    public Optional<Task> findTaskById(Long id) {
        return repository.findById(id);
    }

    public Page<Task> findByParams(String text, Boolean completed, Long priorityId,
                                   Long categoryId, Long userId,
                                   Date dateFrom, Date dateTo, Pageable paging) {
        return repository.findByParams(text, completed, priorityId, categoryId, userId, dateFrom, dateTo, paging);
    }

    public List<Task> findAllTask(){
        return repository.findAll();
    }
}
