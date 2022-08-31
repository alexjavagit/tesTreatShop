package ua.kiev.prog.service;

import ua.kiev.prog.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product, Integer qty);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout();

    BigDecimal getTotal();
}
