package ua.kiev.prog.service;

import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;

import java.util.List;


public interface CategorySizesService {

    CategorySizes addSize(Category category, String size);

    List<CategorySizes> getSizesForCategory(Category category);
}
