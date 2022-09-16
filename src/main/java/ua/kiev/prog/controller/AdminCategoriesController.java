package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.exception.CategoryNotFoundException;
import ua.kiev.prog.service.CategoryService;

import java.text.ParseException;

@Controller
@RequestMapping("/admin")
public class AdminCategoriesController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    //@PreAuthorize("hasRole('ADMIN')")
    public String categoriesList(Model model,
                                 @RequestParam(value = "sortBy", required = false) String sortBy,
                                 @RequestParam(value = "order", required = false) String order,
                                 @RequestParam(value = "searchName", required = false) String searchName,
                                 @RequestParam(value = "page", required = false) String page,
                                 @RequestParam(value = "size", required = false) String size
    ) {
        int ipage = (page == null) ? 0 : Integer.parseInt(page) - 1;
        int isize = (size == null) ? 10 : Integer.parseInt(size);
        Sort.Direction direction = (order == null) ? Sort.Direction.ASC : ((order.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC);
        String sSortBy = (sortBy == null) ? "id" : ((sortBy.equals("price") || sortBy.equals("name")) ? sortBy : "id");
        Pageable pagingSort = PageRequest.of(ipage, isize, direction, sSortBy);


        Page<Category> categoryList;
        if (searchName != null && searchName.length() > 0) {

            categoryList = categoryService.findByName(searchName, pagingSort);
        } else {

            categoryList = categoryService.getAllCategories(pagingSort);
        }

        model.addAttribute("categories", categoryList.getContent());
        model.addAttribute("endpage", categoryList.getTotalPages());
        model.addAttribute("startpage", 1);
        model.addAttribute("currentPage", categoryList.getNumber() + 1);

        return "categories";
    }

    @GetMapping("/categories/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String deleteCategory(Model model, @PathVariable Long id) throws CategoryNotFoundException {
        Category category = categoryService.getById(id);
        if (category != null) {
            categoryService.deleteById(id);
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/new")
    public String newCategory(Model model) {
        model.addAttribute("action", "/admin/categories/new");
        return "categoryEdit";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(Model model, @PathVariable Long id) throws ParseException {
        Category category = categoryService.getById(id);
        if (category == null) {
            return "redirect:/admin/categories";
        }
        model.addAttribute("action", "/admin/categories/update");
        model.addAttribute("theCategory", category);

        return "categoryEdit";
    }

    @PostMapping("/categories/new")
    public String saveCategory(Model model,
                               @ModelAttribute("theCategory") Category theCategory
    ) {
        int error = 0;
        Category categoryExists = categoryService.findByName(theCategory.getName());
        if (categoryExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a category registered with the name provided.");
            error = 1;
        }
        categoryExists = categoryService.findByShortName(theCategory.getShortName());
        if (categoryExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a category registered with the short name provided.");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("theCategory", theCategory);
            return "categoryEdit";
        }

        categoryService.addCategory(theCategory);

        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/update")
    public String updateCategory(Model model,
                                 @ModelAttribute("theCategory") Category theCategory) {

        int error = 0;
        Category categoryExists = categoryService.findByNameAndNotId(theCategory.getName(), theCategory.getId());
        if (categoryExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a category registered with the name provided.");
            error = 1;
        }
        categoryExists = categoryService.findByShortNameAndNotId(theCategory.getShortName(), theCategory.getId());
        if (categoryExists != null) {
            model.addAttribute("errorMessage", "Oops!  There is already a category registered with the short name provided.");
            error = 1;
        }
        if (error == 1) {
            model.addAttribute("theCategory", theCategory);
            model.addAttribute("action", "/admin/categories/update");
            return "categoryEdit";
        }

        categoryService.updateCategory(theCategory);

        return "redirect:/admin/categories";
    }

}
