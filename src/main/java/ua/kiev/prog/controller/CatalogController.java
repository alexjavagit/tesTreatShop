package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.service.CategoryService;
import ua.kiev.prog.service.ProductImagesService;
import ua.kiev.prog.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductImagesService productImagesService;

    @Autowired
    public CatalogController(ProductService productService, CategoryService categoryService, ProductImagesService productImagesService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productImagesService = productImagesService;
    }


    @GetMapping("/")
    public String list(Model model, Pageable pageable){
        Page<Product> list = productService.getAllProducts(pageable);

        model.addAttribute("products", list);
        return "catalog";
    }

    @GetMapping("/search/")
    public String searchResults(Model model, @RequestParam("search_for") String searchFor){
        List<Product> list = productService.getProductsBySearchTerm(searchFor);

        model.addAttribute("products", list);
        return "catalog";
    }

    @GetMapping("/{categoryName}")
    public String listByCategory(Model model, @PathVariable String categoryName){
        Category category = categoryService.findByShortName(categoryName);
        if (category == null) {
            return "redirect:/";
        }

        List<Product> list = productService.getProductsByCategory((Long) category.getId());

        model.addAttribute("category", categoryName);
        model.addAttribute("products", list);
        return "catalog";
    }

    @GetMapping("/sale")
    public String listProductsOnSale(Model model){
        List<Product> products = productService.findByDiscount();

        model.addAttribute("products", products);
        model.addAttribute("category", "sale");
        return "catalog";
    }

}
