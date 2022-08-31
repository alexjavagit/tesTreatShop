package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.service.ProductService;
import ua.kiev.prog.service.ShoppingCartService;
import ua.kiev.prog.service.UserService;

import java.util.Collection;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProductService productService;
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> list = productService.getNewProducts();
        model.addAttribute("products", list.subList(0,4));

        String login = "";
        String role = "";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            if (userDetail != null) {
                login = userDetail.getUsername();

                CustomUser dbUser = userService.findByLogin(login);
                model.addAttribute("login", login);
                role = dbUser.getRole().toString();
                model.addAttribute("role", role);
            } else {
                login = "";
            }
        } else {
            login = "";
        }
        if (role == "ADMIN") {
            return "redirect:/admin/orders";
        } else {
            return "index";
        }
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model){
        //org.springframework.security.core.userdetails.User user = getCurrentUser();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbCustomUser = userService.findByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("admin", isAdmin(user));
        model.addAttribute("firstName", dbCustomUser.getFirstName());
        model.addAttribute("lastName", dbCustomUser.getLastName());
        model.addAttribute("email", dbCustomUser.getEmail());
        model.addAttribute("phone", dbCustomUser.getPhone());
        model.addAttribute("address", dbCustomUser.getAddress());

        return "userProfile";
    }


//    @PostMapping(value = "/update")
//    public String update(@RequestParam(required = false) String email,
//                         @RequestParam(required = false) String phone) {
//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String login = user.getUsername();
//
//        userService.updateUser(login, email, phone);
//
//        return "redirect:/";
//    }

//    @PostMapping(value = "/newuser")
//    public String update(@RequestParam String login,
//                         @RequestParam String password,
//                         @RequestParam String firstName,
//                         @RequestParam String lastName,
//                         @RequestParam(required = false) String email,
//                         @RequestParam(required = false) String phone,
//                         @RequestParam(required = false) String address,
//                         Model model) {
//        String passHash = passwordEncoder.encode(password);
//
//        if ( ! userService.addUser(login, passHash, firstName, lastName, UserRole.USER, email, phone, address)) {
//            model.addAttribute("exists", true);
//            model.addAttribute("login", login);
//            return "register";
//        }
//
//        return "redirect:/";
//    }

//    @PostMapping(value = "/delete")
//    public String delete(@RequestParam(name = "toDelete[]", required = false) List<Long> ids,
//                         Model model) {
//        userService.deleteUsers(ids);
//        model.addAttribute("users", userService.getAllUsers());
//
//        return "admin";
//    }

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("LoginPage");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String admin(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "admin";
//    }

//    @GetMapping("/moderator")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public String moderator(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "moderator";
//    }

    @GetMapping("/unauthorized")
    public String unauthorized(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

//    private org.springframework.security.core.userdetails.User getCurrentUser() {
//        return (org.springframework.security.core.userdetails.User)SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//    }

    private boolean isAdmin(org.springframework.security.core.userdetails.User user) {

        Collection<GrantedAuthority> roles = user.getAuthorities();

        for (GrantedAuthority auth : roles) {
            if ("ROLE_ADMIN".equals(auth.getAuthority()))
                return true;
        }

        return false;
    }


}