package ua.kiev.prog.controller;

import lombok.var;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Map<String, Integer> productsMap = shoppingCartService.getProductsInCart();
        List<Product> cartProducts = new ArrayList<>();
        for (var entry : productsMap.entrySet()) {
            String[] parts = entry.getKey().split("_");
            Long productId = Long.parseLong(parts[0]);
            Product product = productService.getById(productId);
            if (product != null) {
                String size = parts[1];
                product.setCartSize(size);
                product.setCartQty(entry.getValue());
                cartProducts.add(product);
            }
        }
        model.addAttribute("products", cartProducts);
        //model.addAttribute("total", shoppingCartService.getTotal().toString());
        return "shoppingCart";
    }

    @PostMapping("/shoppingCart/addProduct")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCart productToCart) {
        Product product = productService.getById(Long.parseLong(productToCart.getId()));
        String message;
        if (product != null) {
            Integer cartQty = shoppingCartService.addProduct(product, productToCart.getSize(), 1);
            message = "{\"message\" : \"OK\", \"qty\" : \"" + cartQty + "\"}";
        } else {
            message = "{\"message\" : \"Some error. Please try again.\"}";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }


    @PostMapping("/shoppingCart/removeProduct")
    public ResponseEntity<?> removeProductFromCart(@RequestBody ProductToCart productToCart) {
        Product product = productService.getById(Long.parseLong(productToCart.getId()));
        String message;
        if (product != null) {
            Integer cartQty = shoppingCartService.removeProduct(product, productToCart.getSize());
            message = "{\"message\" : \"OK\", \"qty\" : \"" + cartQty + "\"}";
        } else {
            message = "{\"message\" : \"Some error. Please try again.\"}";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        shoppingCartService.checkout();

        return shoppingCart(model);
    }
}
