package ua.kiev.prog.service;

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

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<String, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     */
    @Override
    public void checkout() {
        Product product;
//        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
//            // Refresh quantity for every product before checking
//            product = productRepository.findOne(entry.getKey().getId());
//            if (product.getQuantity() < entry.getValue())
//                throw new NotEnoughProductsInStockException(product);
//            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
//        }
//        productRepository.save(products.keySet());
//        productRepository.flush();
//        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
        return BigDecimal.ZERO;
    }
}