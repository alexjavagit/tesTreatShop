package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.UserRole;
import ua.kiev.prog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/register")
    public String newUser() {
        return "register";
    }

    @PostMapping("/new")
    public String saveUser(Model model,
                           @ModelAttribute("customUser") CustomUser customUser) {
        System.out.println(customUser);
        int error = 0;
        CustomUser userExists = userService.findByEmail(customUser.getEmail());
        if (userExists != null) {
            model.addAttribute("errorMessage", "There is already a user registered with the email provided.");
            error = 1;
        }
        userExists = userService.findByLogin(customUser.getLogin());
        if (userExists != null) {
            model.addAttribute("errorMessage", "There is already a user registered with the login provided.");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("customUser", customUser);
            return "register";
        }
        customUser.setRole(UserRole.USER);
        customUser.setPassword(encoder.encode(customUser.getPassword()));
        userService.addUser(customUser);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model){
        //org.springframework.security.core.userdetails.User user = getCurrentUser();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        String login = user.getUsername();

        CustomUser dbCustomUser = userService.findByLogin(login);
        System.out.println(dbCustomUser);
        model.addAttribute("customUser", dbCustomUser);

        return "userProfile";
    }

    @PostMapping("/update")
    public String updateUser(Model model,
                           @ModelAttribute("customUser") CustomUser customUser) {
        System.out.println(customUser);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        String login = user.getUsername();
        CustomUser dbCustomUser = userService.findByLogin(login);

        int error = 0;
        CustomUser userExists = userService.findByEmailAndNotId(customUser.getEmail(), dbCustomUser.getId());
        if (userExists != null) {
            model.addAttribute("errorMessage", "There is already a user registered with the email provided.");
            error = 1;
        }
        userExists = userService.findByLoginAndNotId(customUser.getLogin(), dbCustomUser.getId());
        if (userExists != null) {
            model.addAttribute("errorMessage", "There is already a user registered with the login provided.");
            model.addAttribute("errorMessageColor", "red");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("customUser", customUser);
            return "userProfile";
        }
        dbCustomUser.setFirstName(customUser.getFirstName());
        dbCustomUser.setLastName(customUser.getLastName());
        dbCustomUser.setEmail(customUser.getEmail());
        dbCustomUser.setPhone(customUser.getPhone());
        dbCustomUser.setAddress(customUser.getAddress());
        dbCustomUser.setLogin(customUser.getLogin());
        //customUser.setRole(UserRole.USER);
        dbCustomUser.setPassword(encoder.encode(customUser.getPassword()));
        userService.updateUser(dbCustomUser);

        model.addAttribute("errorMessage", "Profile was successfully changed!");
        model.addAttribute("errorMessageColor", "navy");



        model.addAttribute("customUser", dbCustomUser);

        return "userProfile";
    }

}
