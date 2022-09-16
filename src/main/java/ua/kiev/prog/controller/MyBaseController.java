package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.kiev.prog.service.ShoppingCartService;


@ControllerAdvice
public class MyBaseController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    // adds a global value to every model
    @ModelAttribute("cartCount")
    public Integer test() {
        return shoppingCartService.getCartCount();
    }
}