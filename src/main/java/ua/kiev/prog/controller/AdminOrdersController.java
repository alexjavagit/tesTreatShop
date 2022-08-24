package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.service.CategoryService;
import ua.kiev.prog.service.ProductService;
import ua.kiev.prog.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminOrdersController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public String adminIndex(Model model) {
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
        }
        return "admin_index";
    }

    @GetMapping("/orders")
    //@PreAuthorize("hasRole('ADMIN')")
    public String ordersList(Model model) {
        return "orders";
    }



}
