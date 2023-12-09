package ru.den.plannerusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.den.planner.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "select * " +
//            "from users.user_data " +
//            "where email = :email ", nativeQuery = true)
    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}
