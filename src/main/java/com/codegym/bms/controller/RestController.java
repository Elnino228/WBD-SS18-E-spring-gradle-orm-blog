package com.codegym.bms.controller;
import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import com.codegym.bms.service.BlogService;
import com.codegym.bms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    public BlogService blogService;

    @Autowired
    public CategoryService categoryService;

    @GetMapping(value = "/api/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Blog>> listCategory(Pageable pageable){
        Page<Blog> blogs=blogService.findAll(pageable);
        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/categories",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> listCategories(){
        Iterable<Category> categories=categoryService.findAll();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/api/view-category/{id}")
    public ResponseEntity<Iterable<Blog>> viewCategory(@PathVariable Long id){
        Category category=categoryService.findById(id);
        Iterable<Blog> blogs=blogService.findAllByCategory(category);
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

    @GetMapping("/api/view-blog/{id}")
    public ResponseEntity<Blog> viewBlog(@PathVariable Long id){
        Blog blog=blogService.findById(id);
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

}
