package com.codegym.bms.service.Impl;

import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import com.codegym.bms.repository.BlogRepository;
import com.codegym.bms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category){
        return blogRepository.findAllByCategory(category);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(String keyword, Pageable pageable) {
        return blogRepository.findAllByTitleContaining(keyword,pageable);
    }
}
