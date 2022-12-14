package ua.kiev.prog.service;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.entity.ProductImages;
import ua.kiev.prog.exception.ProductNotFoundException;
import ua.kiev.prog.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list;
    }


    @Transactional(readOnly = true)
    public Page<Product> findByCategoryAndName(Long searchCategory, String searchName, Pageable pageable) {

        Page<Product> products = productRepository.findByCategoryAndName(searchCategory, searchName, pageable);

        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> findByCategory(Long searchCategory, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(searchCategory, pageable);

        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String searchName, Pageable pageable) {
        Page<Product> products = productRepository.findByName(searchName, pageable);

        return products;
    }


    @Override
    public List<Product> getProductsBySearchTerm(String searchString) {
        List<Product> list = productRepository.getProductsBySearchTerm(searchString);
        return list;
    }

    @Transactional(readOnly = true)
    public List<Product> getNewProducts() {
        List<Product> products = productRepository.getNewProducts();

        for(Product product : products) {
            for (ProductImages productImage: product.getProductImages()) {
                productImage.setBase64Image(Base64.toBase64String(productImage.getImage()));
            }
        }
        return products;
    }

    public List<Product> getProductsByCategory(Long id) {
        List<Product> products  = productRepository.findByCategory(id);
        for(Product product : products) {
            for (ProductImages productImage: product.getProductImages()) {
                productImage.setBase64Image(Base64.toBase64String(productImage.getImage()));
            }
        }
        return products;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }


    @Transactional(readOnly = true)
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Product findByNameAndNotId(String name, Long id) {
        return productRepository.findByNameAndNotId(name, id);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findByDiscount() {
        return productRepository.findByDiscount();
    }

    ;

    @Transactional
    public Product addProduct(String name, Category category,
                              String description, BigDecimal price,
                              Integer discount) {
        Product product = new Product(name, category, description, price, discount);
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Transactional
    public boolean updateProduct(Long id, String name, Category category,
                                 String description, BigDecimal price,
                                 Integer discount) {
        Product product = productRepository.getById(id);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setDiscount(discount);
        productRepository.save(product);

        return true;
    }


    public Product getProductDetails(Long id) {
        return productRepository.getProductDetails(id);
    }

    @Transactional
    public void deleteProduct(Long id) throws ProductNotFoundException {
        verifyIfExists(id);
        productRepository.deleteById(id);
    }

    private Product verifyIfExists(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void save(Product product) {
        productRepository.save(product);
    }


}
