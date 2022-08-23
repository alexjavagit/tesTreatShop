package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.exception.ProductNotFoundException;
import ua.kiev.prog.service.CategoryService;
import ua.kiev.prog.service.ProductService;
import ua.kiev.prog.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

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


    @GetMapping("/products")
    //@PreAuthorize("hasRole('ADMIN')")
    public String productsList(Model model,
                               @RequestParam(value = "sortBy", required = false) String sortBy,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "searchCategory", required = false) Long searchCategory,
                               @RequestParam(value = "searchName", required = false) String searchName,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "size", required = false) String size
                               ) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page)-1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.ASC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : ((sortBy.equals("price") || sortBy.equals("name")) ? sortBy :  "id");
        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);

        Long icategory = 0L;
        if (searchCategory != null) {
            Category category = categoryService.getCategory(searchCategory.longValue());
            if (category != null) {
                icategory = category.getId();
            }
        }
        Page<Product> productList;
        if (!icategory.equals(0L) && (searchName != null && searchName.length()>0)) {

            productList = productService.findByCategoryAndName(icategory, searchName, pagingSort);
        } else if (!icategory.equals(0L)) {

            productList = productService.findByCategory(icategory, pagingSort);
        } else if (searchName != null && searchName.length()>0) {

            productList = productService.findByName(searchName, pagingSort);
        } else {

            productList = productService.getAllProducts(pagingSort);
        }

        model.addAttribute("products", productList.getContent());
        model.addAttribute("endpage", productList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", productList.getNumber()+1);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "products";
    }

    @GetMapping("/products/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(Model model, @PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/categories")
    //@PreAuthorize("hasRole('ADMIN')")
    public String categoriesList(Model model,
                               @RequestParam(value = "sortBy", required = false) String sortBy,
                               @RequestParam(value = "order", required = false) String order,
                               @RequestParam(value = "searchName", required = false) String searchName,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "size", required = false) String size
    ) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page)-1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.ASC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : ((sortBy.equals("price") || sortBy.equals("name")) ? sortBy :  "id");
        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);


        Page<Category> categoryList;
        if (searchName != null && searchName.length()>0) {

            categoryList = categoryService.findByName(searchName, pagingSort);
        } else {

            categoryList = categoryService.getAllCategories(pagingSort);
        }

        model.addAttribute("categories", categoryList.getContent());
        model.addAttribute("endpage", categoryList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", categoryList.getNumber()+1);

        return "products";
    }
}
