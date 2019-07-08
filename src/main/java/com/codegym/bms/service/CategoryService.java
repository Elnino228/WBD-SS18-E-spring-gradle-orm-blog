package com.codegym.bms.service;

import com.codegym.bms.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
