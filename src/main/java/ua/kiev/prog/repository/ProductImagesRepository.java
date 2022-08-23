package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.Product;
import ua.kiev.prog.entity.ProductImages;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    ProductImages findByProduct(Product product);

    @Query("SELECT i FROM ProductImages i WHERE i.product.id=:id")
    List<ProductImages> findProductImages(Long id);
}
