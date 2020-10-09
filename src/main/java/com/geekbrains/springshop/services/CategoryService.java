package com.geekbrains.springshop.services;

import com.geekbrains.springshop.entities.Category;
import com.geekbrains.springshop.repositories.CategoryRepository;
import com.geekbrains.springshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    public void addNewCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        Category category =categoryRepository.findById(id).orElse(null);
        if (category!=null){
            if (productRepository.findAllByCategory_id(id).size()==0){
                categoryRepository.delete(category);
            }
        }
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findOneById(id);
    }
}
