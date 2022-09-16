package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;

import java.util.List;

public interface CategorySizesRepository extends JpaRepository<CategorySizes, Long> {
    List<CategorySizes> findByCategory(@Param("category_id") Long category_id);

    boolean existsBySizeAndCategory(@Param("size") String size, @Param("category") Category category);

    @Query("SELECT c FROM CategorySizes c WHERE c.category=:category")
    List<CategorySizes> getByCategory(Category category);
}
