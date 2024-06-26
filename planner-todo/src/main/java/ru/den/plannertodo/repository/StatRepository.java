package ru.den.plannertodo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.Stat;

@Repository
public interface StatRepository extends CrudRepository<Stat,Long> {
    Stat findByUserId (Long userId);
}
