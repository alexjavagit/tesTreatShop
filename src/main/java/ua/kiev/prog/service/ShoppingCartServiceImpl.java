package ua.kiev.prog.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 *
 * @author Dusan
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<String, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Integer addProduct(Product product, String size, Integer qty) {

        if (products.containsKey(product.getId()+"_"+size)) {
            products.replace(product.getId()+"_"+size, products.get(product.getId()+"_"+size) + 1);
            return products.get(product.getId()+"_"+size);
        } else {
            products.put(product.getId()+"_"+size, 1);
            return 1;
        }
    }


    @Override
    public Integer removeProduct(Product product, String size) {
        if (products.containsKey(product.getId()+"_"+size)) {
            if (products.get(product.getId()+"_"+size) > 1) {
                products.replace(product.getId() + "_" + size, products.get(product.getId() + "_" + size) - 1);
                return products.get(product.getId() + "_" + size);
            } else if (products.get(product.getId()+"_"+size) == 1) {
                products.remove(product.getId()+"_"+size);
                return 0;
            } else
                return 0;
        } else
            return 0;
    }


    @Override
    public Map<String, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }


    @Override
    public Integer getTotal() {
        Integer total = 0;
        for (var entry : products.entrySet()) {
            String[] parts = entry.getKey().split("_");
            Long productId = Long.parseLong(parts[0]);
            Product eproduct = productRepository.getById(productId);
            if (eproduct != null) {
                if (eproduct.getDiscount() > 0) {
                    BigDecimal rPrice = eproduct.getPrice().subtract(eproduct.getPrice().multiply(BigDecimal.valueOf(eproduct.getDiscount()).divide(BigDecimal.valueOf(100))));
                    total = total + rPrice.intValue() * entry.getValue();
                } else {
                    total = total + eproduct.getPrice().intValue() * entry.getValue();
                }
            }
        }
        return total.intValue();
    }

    @Override
    public Integer getCartCount() {
        Integer count = 0;
        for (var entry : products.entrySet()) {
            count = count + entry.getValue();
        }
        return count;
    }

    @Override
    public void clearCart() {
        products.entrySet().clear();
        products.clear();
    }
}