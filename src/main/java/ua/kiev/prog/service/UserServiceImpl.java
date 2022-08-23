package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.UserRole;
import ua.kiev.prog.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    @Transactional
    public void updateUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

//    @Transactional
//    public void deleteUsers(List<Long> ids) {
//        ids.forEach(id -> {
//            Optional<CustomUser> user = userRepository.findById(id);
//            user.ifPresent(u -> {
//                if ( ! AppConfig.ADMIN.equals(u.getLogin()) && ! AppConfig.MODERATOR.equals(u.getLogin())) {
//                    userRepository.deleteById(u.getId());
//                }
//            });
//        });
//    }


//    @Transactional
//    public boolean addUser(String login, String passHash,
//                           String firstName, String lastName,
//                           UserRole role, String email,
//                           String phone,
//                           String address) {
//        if (userRepository.existsByLogin(login))
//            return false;
//
//        CustomUser customUser = new CustomUser(login, passHash, firstName, lastName, role, email, phone, address);
//        userRepository.save(customUser);
//
//        return true;
//    }

//    @Transactional
//    public void updateUser(String login, String email, String phone) {
//        CustomUser customUser = userRepository.findByLogin(login);
//        if (customUser == null)
//            return;
//
//        customUser.setEmail(email);
//        customUser.setPhone(phone);
//
//        userRepository.save(customUser);
//    }
}
