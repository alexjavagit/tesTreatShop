package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {
    Product findByName(@Param("name") String name);

    Page<Product> findAllBy(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id=:searchCategory AND p.name LIKE %:searchName%")
    Page<Product> findByCategoryAndName(Long searchCategory, String searchName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id=:searchCategory")
    Page<Product> findByCategory(Long searchCategory, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchName%")
    Page<Product> findByName(String searchName, Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> getNewProducts();

    @Query("SELECT p FROM Product p WHERE p.category.id=:id")
    List<Product> findByCategory(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.discount>0")
    List<Product> findByDiscount();

    @Query("SELECT p from Product p WHERE p.name LIKE %:searchString%")
    List<Product> getProductsBySearchTerm(@Param("searchString") String searchString);

    @Query("SELECT p from Product p WHERE p.id=:id")
    Product getProductDetails(@Param("id") Long id);

    Product getById(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.name=:name and p.id <> :id")
    Product findByNameAndNotId(@Param("name") String name, @Param("id") Long id);


//    @Query("DELETE from Product p WHERE p.id=:id")
//    void deleteById(@Param("id")Long id);
}
