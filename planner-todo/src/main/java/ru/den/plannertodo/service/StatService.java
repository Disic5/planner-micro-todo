package ru.den.plannertodo.service;

import ru.den.planner.dto.StatDto;

public interface StatService {

    StatDto findStatByUserId(Long userId);

}
