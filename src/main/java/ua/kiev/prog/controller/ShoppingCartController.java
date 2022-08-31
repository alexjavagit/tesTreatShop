package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.service.ProductService;
import ua.kiev.prog.service.ShoppingCartService;
import ua.kiev.prog.entity.POJO.ProductToCart;

@Controller
@RequestMapping("/")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        System.out.println("shoppingCart");
        model.addAttribute("products", shoppingCartService.getProductsInCart());
        model.addAttribute("total", shoppingCartService.getTotal().toString());
        return "shoppingCart";
    }

    @PostMapping("/shoppingCart/addProduct")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCart productToCart) {
        Product product = productService.getById(Long.parseLong(productToCart.getId()));
        String message;
        if (product != null) {
            product.setSelectedSize(productToCart.getSize());
            shoppingCartService.addProduct(product, 1);
            message = "{\"message\" : \"OK\"}";
        } else {
            message = "{\"message\" : \"Some error. Please try again.\"}";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }


//    @GetMapping("/removeProduct/{productId}")
//    public String removeProductFromCart(@PathVariable("productId") Long productId, Model model) {
//        Product product = productService.getById(productId);
//        if (product != null) {
//            //shoppingCartService.removeProduct(product);
//        }
//        return shoppingCart(model);
//    }
//
//    @GetMapping("/checkout")
//    public String checkout(Model model) {
//        shoppingCartService.checkout();
//
//        return shoppingCart(model);
//    }
}
