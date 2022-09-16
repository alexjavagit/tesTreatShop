package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.Category;

import ua.kiev.prog.repository.CategoryRepository;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Category> findByName(String searchName, Pageable pageable) {
        Page<Category> categories = categoryRepository.findByName(searchName, pageable);
        return categories;
    }

    @Transactional(readOnly = true)
    public Page<Category> getAllCategories(Pageable pageable) {
        Page<Category> list = categoryRepository.findAll(pageable);
        return list;
    }

    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Category findByNameAndNotId(String name, Long id) {
        return categoryRepository.findByNameAndNotId(name, id);
    }

    @Transactional(readOnly = true)
    public Category findByShortName(String name) {
        return categoryRepository.findByShortName(name);
    }

    @Transactional(readOnly = true)
    public Category findByShortNameAndNotId(String name, Long id) {
        return categoryRepository.findByShortNameAndNotId(name, id);
    }

    @Transactional
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
