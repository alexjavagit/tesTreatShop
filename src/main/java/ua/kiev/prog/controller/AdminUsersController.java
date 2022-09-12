package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.UserRole;
import ua.kiev.prog.exception.ProductNotFoundException;
import ua.kiev.prog.service.UserService;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/admin")
public class AdminUsersController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/users")
    //@PreAuthorize("hasRole('ADMIN')")
    public String usersList(Model model,
                               @RequestParam(value = "sortBy", required = false) String sortBy,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "searchEmail", required = false) String searchEmail,
                               @RequestParam(value = "searchLogin", required = false) String searchLogin,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "size", required = false) String size
    ) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page)-1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.ASC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : ((sortBy.equals("price") || sortBy.equals("name")) ? sortBy :  "id");
        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);


        Page<CustomUser> usersList;
        if ((searchEmail != null && searchEmail.length()>0) && (searchLogin != null && searchLogin.length()>0)) {

            usersList = userService.findByEmailAndLogin(searchEmail, searchLogin, pagingSort);
        } else if (searchEmail != null && searchEmail.length()>0) {

            usersList = userService.findByEmail(searchEmail, pagingSort);
        } else if (searchLogin != null && searchLogin.length()>0) {

            usersList = userService.findByLogin(searchLogin, pagingSort);
        } else {

            usersList = userService.getAllUsers(pagingSort);
        }

        model.addAttribute("users", usersList.getContent());
        model.addAttribute("endpage",usersList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", usersList.getNumber()+1);

        return "users";
    }

    @GetMapping("/users/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(Model model, @PathVariable Long id) throws ProductNotFoundException {
        CustomUser customUser = userService.getById(id);
        if (customUser != null) {
            userService.deleteById(id);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("action", "/admin/users/new");
        return "userEdit";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable Long id) throws ParseException {
        CustomUser customUser = userService.getById(id);
        if (customUser == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("action", "/admin/users/update");
        model.addAttribute("theUser", customUser);


        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM yyyy");
        String dateAdded = customUser.getDateAdded().format(format);
        model.addAttribute("dateAdded", dateAdded);

        return "userEdit";
    }

    @PostMapping("/users/new")
    public String saveUser(Model model,
                           @ModelAttribute("theUser") CustomUser theUser
                           ) {
        System.out.println(theUser);
        int error = 0;
        CustomUser userExists = userService.findByEmail(theUser.getEmail());
        if (userExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a user registered with the email provided.");
            error = 1;
        }
        userExists = userService.findByLogin(theUser.getLogin());
        if (userExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a user registered with the login provided.");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("theUser", theUser);
            return "userEdit";
        }
        theUser.setRole(UserRole.USER);
        theUser.setPassword(encoder.encode(theUser.getPassword()));
        userService.addUser(theUser);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/update")
    public String updateUser(Model model,
                             @ModelAttribute("theUser") CustomUser theUser) {

        int error = 0;
        CustomUser userExists = userService.findByEmailAndNotId(theUser.getEmail(), theUser.getId());
        if (userExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a user registered with the email provided.");
            error = 1;
        }
        userExists = userService.findByLoginAndNotId(theUser.getLogin(), theUser.getId());
        if (userExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a user registered with the login provided.");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("theUser", theUser);
            model.addAttribute("action", "/admin/users/update");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM yyyy");
            String dateAdded = userExists.getDateAdded().format(format);
            model.addAttribute("dateAdded", dateAdded);
            return "userEdit";
        }

        //CustomUser customUser = new CustomUser(login, encoder.encode(passwd), firstName, lastName, UserRole.USER, email, phone, address);
        userService.updateUser(theUser);

        return "redirect:/admin/users";
    }
}
