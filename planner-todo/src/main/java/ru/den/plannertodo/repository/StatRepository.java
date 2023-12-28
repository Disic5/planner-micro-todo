package ru.den.plannertodo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.Stat;

import java.util.Optional;

@Repository
public interface StatRepository extends CrudRepository<Stat,Long> {
    Optional<Stat> findByUserId (Long userId);
}
