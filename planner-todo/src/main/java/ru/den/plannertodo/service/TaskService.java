package ru.den.plannertodo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.den.planner.dto.TaskDto;
import ru.den.planner.entity.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> findByUserId(Long userId);

    Task addTask(Task task);

    Task updateTask(Task task);

    void deleteTaskById(Long id);

    Optional<TaskDto> findTaskById(Long id);

    Page<Task> findByParams(String text, Boolean completed, Long priorityId,
                            Long categoryId, Long userId,
                            Date dateFrom, Date dateTo, Pageable paging);

    List<TaskDto> findAllTask();
}
