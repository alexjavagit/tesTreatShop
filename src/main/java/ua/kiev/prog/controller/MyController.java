package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
@ResponseBody
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
        model.addAttribute("products", list.subList(0, 4));

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


    @GetMapping("/login")
    public String loginPage() {
        System.out.println("LoginPage");
        return "login";
    }


    @GetMapping("/unauthorized")
    public String unauthorized(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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