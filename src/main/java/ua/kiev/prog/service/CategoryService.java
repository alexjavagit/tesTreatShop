package ua.kiev.prog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Page<Category> findByName(String searchName, Pageable pageable);

    Page<Category> getAllCategories(Pageable pageable);

    Category findByName(String name);

    Category findByNameAndNotId(String name, Long id);

    Category getById(Long id);

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteById(Long id);

    Category findByShortName(String name);

    Category findByShortNameAndNotId(String name, Long id);

    Category getCategory(Long id);

}
