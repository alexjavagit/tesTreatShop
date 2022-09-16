package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.CustomUser;


public interface UserRepository extends JpaRepository<CustomUser, Long> {
    @Query("SELECT u FROM CustomUser u WHERE u.login=:login")
    CustomUser findByLogin(@Param("login") String login);

    @Query("SELECT u FROM CustomUser u WHERE u.login=:login and u.id <> :id")
    CustomUser findByLoginAndNotId(@Param("login") String login, @Param("id") Long id);

    CustomUser findByEmail(@Param("email") String email);

    @Query("SELECT u FROM CustomUser u WHERE u.email=:email and u.id <> :id")
    CustomUser findByEmailAndNotId(@Param("email") String email, @Param("id") Long id);

    CustomUser findUserById(@Param("id") Long id);

    Page<CustomUser> findAllBy(Pageable pageable);

    @Query("SELECT u FROM CustomUser u WHERE u.email LIKE %:searchEmail% AND u.login LIKE %:searchLogin%")
    Page<CustomUser> findByEmailAndLogin(String searchEmail, String searchLogin, Pageable pageable);

    @Query("SELECT u FROM CustomUser u WHERE u.email LIKE %:searchEmail%")
    Page<CustomUser> findByEmail(String searchEmail, Pageable pageable);

    @Query("SELECT u FROM CustomUser u WHERE u.login LIKE %:searchLogin%")
    Page<CustomUser> findByLogin(String searchLogin, Pageable pageable);
}