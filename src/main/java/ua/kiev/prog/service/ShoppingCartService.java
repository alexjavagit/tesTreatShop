package ua.kiev.prog.service;

import ua.kiev.prog.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product, String size);

    void removeProduct(Product product, String size);

    Map<String, Integer> getProductsInCart();

    void checkout();

    BigDecimal getTotal();
}
