package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.UserRole;
import ua.kiev.prog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String userList(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "userList";
//    }

    @GetMapping("/newuser")
    public String newUser(Model model) {
        model.addAttribute("user", new CustomUser());
        return "user";
    }

//    @PostMapping("/newuser")
//    public String saveUser(CustomUser customUser, Model model) {
//        PasswordEncoder encoder = null;
//        if (userService.addUser(customUser.getLogin(), encoder.encode(customUser.getPassword()), customUser.getFirstName(), customUser.getLastName(),
//                UserRole.USER, customUser.getEmail(), customUser.getPhone(), customUser.getAddress())) {
//            return "users";
//        } else {
//            model.addAttribute("user", customUser);
//            return "user";
//        }
//    }

}
