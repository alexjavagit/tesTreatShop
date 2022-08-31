package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.Category;
import ua.kiev.prog.entity.CategorySizes;
import ua.kiev.prog.repository.CategorySizesRepository;

import java.util.List;

@Service
public class CategorySizesServiceImpl implements CategorySizesService {
    private final CategorySizesRepository categorySizesRepository;

    @Autowired
    public CategorySizesServiceImpl(CategorySizesRepository categorySizesRepository) {
        this.categorySizesRepository = categorySizesRepository;
    }

    @Transactional
    public CategorySizes addSize(Category category, String size) {
        if (categorySizesRepository.existsBySizeAndCategory(size, category))
            return null;

        CategorySizes categorySizes = new CategorySizes(category, size);
        categorySizesRepository.save(categorySizes);

        return categorySizes;
    }

    @Transactional
    public List<CategorySizes> getSizesForCategory(Category category) {
        List<CategorySizes> categorySizes = categorySizesRepository.getByCategory(category);
        return categorySizes;
    }
}
