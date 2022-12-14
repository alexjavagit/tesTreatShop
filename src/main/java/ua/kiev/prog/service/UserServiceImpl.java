package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public CustomUser findByLoginAndNotId(String login, Long id) {
        return userRepository.findByLoginAndNotId(login, id);
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmailAndNotId(String email, Long id) {
        return userRepository.findByEmailAndNotId(email, id);
    }

    @Transactional(readOnly = true)
    public CustomUser getById(Long id) {
        return userRepository.findUserById(id);
    }

    @Transactional(readOnly = true)
    public Page<CustomUser> getAllUsers(Pageable pageable) {
        Page<CustomUser> list = userRepository.findAll(pageable);
        return list;
    }

    @Transactional(readOnly = true)
    public Page<CustomUser> findByEmailAndLogin(String searchEmail, String searchLogin, Pageable pageable) {

        Page<CustomUser> users = userRepository.findByEmailAndLogin(searchEmail, searchLogin, pageable);

        return users;
    }

    @Transactional(readOnly = true)
    public Page<CustomUser> findByEmail(String searchEmail, Pageable pageable) {
        Page<CustomUser> users = userRepository.findByEmail(searchEmail, pageable);

        return users;
    }

    @Transactional(readOnly = true)
    public Page<CustomUser> findByLogin(String searchLogin, Pageable pageable) {
        Page<CustomUser> users = userRepository.findByLogin(searchLogin, pageable);

        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomUser existsByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    @Override
    @Transactional
    public void updateUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
