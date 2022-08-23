package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query("SELECT u FROM CustomUser u WHERE u.login=:login")
    CustomUser findByLogin(@Param("login") String login);

    boolean existsByLogin(@Param("login") String login);

}