package ru.den.plannertodo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.Category;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c where " +
            "(:title is null or :title='' " + //если передали параметр title пустым, то выбирутся все записи(сработанные)
            "or lower(c.title) like lower(concat('%', :title,'%')))" + // если параметр title не пустой, то выполнится эта строчка
            "and c.userId=:userId " + // фильтрация для конкретного пользователя
            "order by c.title asc ")
        // сортировка
    List<Category> findByTitle(@Param("title") String title, @Param("userId") Long userId);

    @Query("select c from Category c where c.userId = :userId")
    List<Category> findAllByUserId(@Param("userId") Long userId);

}
