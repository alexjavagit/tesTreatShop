package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.service.CategoryService;

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

        return "categories";
    }

}
