package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.Category;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor {
    Category findByName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.name=:name AND c.id<>:id")
    Category findByNameAndNotId(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT c FROM Category c WHERE c.shortName=:name")
    Category findByShortName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.shortName=:name AND c.id<>:id")
    Category findByShortNameAndNotId(@Param("name") String name, @Param("id") Long id);

    boolean existsByName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.name LIKE %:searchName%")
    Page<Category> findByName(String searchName, Pageable pageable);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findCategoryById(@Param("id") Long id);
}
