package ua.kiev.prog.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;


public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> findByCategoryAndName(Long searchCategory, String searchName, Pageable pageable);

    Page<Product> findByCategory(Long searchCategory, Pageable pageable);

    Page<Product> findByName(String searchName, Pageable pageable);

    List<Product> getProductsBySearchTerm(String searchString);

    List<Product> getNewProducts();

    List<Product> getProductsByCategory(Long id);

    List<Product> getProducts();

    Product findByName(String name);

    Product findByNameAndNotId(String name, Long id);

    Product getById(Long id);

    List<Product> findByDiscount();

    Product getProductDetails(Long id);

    Product addProduct(String name, Category category,
                       String description, BigDecimal price,
                       Integer discount);

    boolean updateProduct(Long id, String name, Category category,
                          String description, BigDecimal price,
                          Integer discount);

    void deleteProduct(Long id) throws ProductNotFoundException;

    void save(Product product);


}
