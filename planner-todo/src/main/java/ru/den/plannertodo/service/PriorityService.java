package ru.den.plannertodo.service;

import ru.den.planner.dto.PriorityDto;
import ru.den.planner.entity.Priority;

import java.util.List;

public interface PriorityService {

    PriorityDto findById(Long id);

    Priority addPriority(Priority priority);

    void updatePriority(Priority priority);

    void deletePriority(Long id);

    List<PriorityDto> findByTitle(String title, Long userId);

    List<PriorityDto> findAllPriority();

}
