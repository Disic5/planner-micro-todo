package ru.den.plannertodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    // сортировка
    @Query("SELECT p FROM Priority p where " +
            "(:title is null or :title='' " + //если передали параметр title пустым, то выбирутся все записи(сработанные)
            "or lower(p.title) like lower(concat('%', :title,'%')))" + // если параметр title не пустой, то выполнится эта строчка
            "and p.userId=:userId " + // фильтрация для конкретного пользователя
            "order by p.title asc ")
    List<Priority> findByTitle(@Param("title") String title, @Param("userId") Long userId);
}
