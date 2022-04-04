package com.example.invoice.service;

import com.example.invoice.repository.CategoryRepository;
import com.example.invoice.domains.Category;
import com.example.invoice.service.base.BaseAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseAbstractService<CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
