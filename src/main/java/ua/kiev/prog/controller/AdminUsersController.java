package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.exception.ProductNotFoundException;
import ua.kiev.prog.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUsersController {
    @Autowired
    private UserService userService;


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
        //userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
