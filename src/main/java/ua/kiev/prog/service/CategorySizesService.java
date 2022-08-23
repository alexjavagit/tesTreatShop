package ua.kiev.prog.service;

import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;


public interface CategorySizesService {

    CategorySizes addSize(Category category, String size);
}
