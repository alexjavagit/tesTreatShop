package ua.kiev.prog.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.kiev.prog.entity.*;
import ua.kiev.prog.service.*;
import ua.kiev.prog.entity.POJO.ProductToCart;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemsService orderItemsService;



    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
        showShoppingCart(model);
        return "shoppingCart";
    }

    @PostMapping("/shoppingCart/addProduct")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCart productToCart) {
        Product product = productService.getById(Long.parseLong(productToCart.getId()));
        String message;
        if (product != null) {
            Integer cartQty = shoppingCartService.addProduct(product, productToCart.getSize(), 1);
            message = "{\"message\" : \"OK\", \"qty\" : \"" + cartQty + "\"";
        } else {
            message = "{\"message\" : \"Some error. Please try again.\"";
        }

        message = message + ", \"total\" : " + shoppingCartService.getTotal() +", ";
        message = message + "\"cartCount\" : " + shoppingCartService.getCartCount() +"}";
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
            message = "{\"message\" : \"OK\", \"qty\" : \"" + cartQty + "\"";
        } else {
            message = "{\"message\" : \"Some error. Please try again.\"";
        }
        message = message + ", \"total\" : " + shoppingCartService.getTotal() +", ";
        message = message + "\"cartCount\" : " + shoppingCartService.getCartCount() +"}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    @GetMapping("/shoppingCart/checkout")
    public String checkout(Model model) {
        showShoppingCart(model);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String login = user.getUsername();
            CustomUser dbCustomUser = userService.findByLogin(login);
            model.addAttribute("customUser", dbCustomUser);
        }
        return "checkout";
    }

    public void showShoppingCart(Model model) {
        Map<String, Integer> productsQty = shoppingCartService.getProductsInCart();
        Map<String, Product> cartProducts = new HashMap<>();
        Map<String, String> productsSizes = new HashMap<>();
        for (var entry : productsQty.entrySet()) {
            String[] parts = entry.getKey().split("_");
            Long productId = Long.parseLong(parts[0]);
            Product product = productService.getById(productId);
            if (product != null) {
                cartProducts.put(product.getId()+"_"+parts[1], product);
                productsSizes.put(product.getId()+"_"+parts[1], parts[1]);
            }
        }
        model.addAttribute("products", cartProducts);
        model.addAttribute("productsQty", productsQty);
        model.addAttribute("productsSizes", productsSizes);
    }

    @PostMapping("/shoppingCart/checkout")
    public String doCheckout(Model model,
                             @RequestParam(value = "shippingAddress", required = false) String shippingAddress,
                             @RequestParam(value = "firstName", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "phone", required = false) String phone) {
        CustomUser dbCustomUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String login = user.getUsername();
            dbCustomUser = userService.findByLogin(login);
        }

        Order order = new Order(email, firstName, lastName, phone, shippingAddress, OrderStatus.NEW, dbCustomUser, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        orderService.saveOrder(order);

        Map<String, Integer> productsQty = shoppingCartService.getProductsInCart();
        BigDecimal total = BigDecimal.ZERO;
        for (var entry : productsQty.entrySet()) {
            String[] parts = entry.getKey().split("_");
            Long productId = Long.parseLong(parts[0]);
            Product product = productService.getById(productId);
            if (product != null) {
                OrderItems orderItem = new OrderItems(order, product, new BigDecimal(entry.getValue()), product.getPrice(), parts[1]);
                orderItemsService.saveOrder(orderItem);
                total = total.add(product.getPrice().multiply(new BigDecimal(entry.getValue())));
            }
        }
        order.setTotal(total);
        //orderService.saveOrder(order);
        //clear shopping cart
        shoppingCartService.clearCart();

        model.addAttribute("orderId",  order.getId());
        model.addAttribute("cartCount", 0);
        return "orderDone";
    }
}
