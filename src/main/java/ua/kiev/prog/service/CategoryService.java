package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Page<Category> findByName(String searchName, Pageable pageable);

    Page<Category> getAllCategories(Pageable pageable);

    Category findByName(String name);


    Category findByShortName(String name);

    Category getCategory(Long id);

    @Autowired
    Category addCategory(String name, String description);

}
