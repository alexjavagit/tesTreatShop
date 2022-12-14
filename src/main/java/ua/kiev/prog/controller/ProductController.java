package ua.kiev.prog.controller;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.entity.ProductImages;
import ua.kiev.prog.service.CategoryService;
import ua.kiev.prog.service.ProductImagesService;
import ua.kiev.prog.service.ProductService;
import ua.kiev.prog.service.ShoppingCartService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductImagesService productImagesService;
    private final ShoppingCartService shoppingCartService;

    public ProductController(ProductService productService, CategoryService categoryService, ProductImagesService productImagesService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productImagesService = productImagesService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{id}")
    public String productDetails(Model model, @PathVariable String id) {
        Long pid = Long.parseLong(id);
        if (pid > 0) {
            Product product = productService.getProductDetails(pid);
            model.addAttribute("product", product);
            Category category = categoryService.getCategory(product.getCategory().getId());
            model.addAttribute("category", category);

            List<ProductImages> productImages = productImagesService.getProductImages(pid);
            for (ProductImages productImage: product.getProductImages()) {
                    productImage.setBase64Image(Base64.toBase64String(productImage.getImage()));
            }

            model.addAttribute("productImages", productImages);

            List<CategorySizes> sizes = product.getCategorySizes();
            model.addAttribute("sizes", sizes);

            return "product";
        } else {
            return "redirect: /catalog";
        }
    }

}
