package com.codegym.bms.service;

import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByTitleContaining(String keyword,Pageable pageable);

    Iterable<Blog> findAllByCategory(Category category);

    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);
}
