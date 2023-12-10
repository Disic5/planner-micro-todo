package ru.den.plannerusers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.User;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

    @Query("select u from User u where " +
            "(:email is null or :email='' or lower(u.email) like lower(concat('%', :email, '%'))) or " +
            "(:name is null or :name='' or lower(u.name) like lower(concat('%', :name, '%')))")
    Page<User> findByParams(@Param("email") String email,
                           @Param("name") String name,
                           Pageable pageable);
}
