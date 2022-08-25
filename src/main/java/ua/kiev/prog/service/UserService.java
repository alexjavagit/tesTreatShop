package ua.kiev.prog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Product;

public interface UserService {

    CustomUser findByLogin(String login);
    CustomUser findByLoginAndNotId(String login, Long id);

    CustomUser findByEmail(String email);
    CustomUser findByEmailAndNotId(String email, Long id);

    CustomUser getById(Long id);

    CustomUser existsByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    void deleteById(Long id);

    Page<CustomUser> getAllUsers(Pageable pageable);

    Page<CustomUser> findByEmailAndLogin(String searchEmail, String searchLogin, Pageable pageable);

    Page<CustomUser> findByEmail(String searchEmail, Pageable pageable);

    Page<CustomUser> findByLogin(String searchLogin, Pageable pageable);
}
