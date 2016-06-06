package com.mobilapi.service;


import com.mobilapi.domain.category.Category;
import com.mobilapi.domain.product.Product;
import com.mobilapi.repository.CategoryRepository;
import com.mobilapi.service.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    public List<Product> getAllProductByCategory(Long id) {

        Category category = categoryRepository.findOne(id);

        if (category == null) {
            return null;
        } else {
            return category.getProducts();
        }
    }

    public Category createCategory(CategoryDto categoryDto) {
        return categoryRepository.save(categoryDto.createCategory());
    }

    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

    public void updateCategory(Category category) {

        if (categoryRepository.exists(category.getId())) {
            categoryRepository.save(category);
        }
    }
}
