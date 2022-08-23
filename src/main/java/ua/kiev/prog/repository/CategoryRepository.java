package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.shortName=:name")
    Category findByShortName(@Param("name") String name);

    boolean existsByName(@Param("name") String name);

    Page<Category> findByName(String searchName, Pageable pageable);

    Page<Category> getAllCategories(Pageable pageable);

    Page<Category> findAll(Pageable pageable);
}
