package com.codegym.bms.controller;

import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import com.codegym.bms.service.BlogService;
import com.codegym.bms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    public BlogService blogService;

    @Autowired
    public CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/")
    public String index(){
        return "/blog/index";
    }
    @GetMapping("/admin/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/admin/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog) {
        blog.setTime(new Date());
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog create successfully");
        return modelAndView;
    }

    @GetMapping("/admin/blogs")
    public ModelAndView listBlog(@RequestParam("s") Optional<String> keyword, Pageable pageable) {
        Page<Blog> blogs;
        if (keyword.isPresent()){
            blogs=blogService.findAllByTitleContaining(keyword.get(),pageable);
        } else {
            blogs=blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/user/blogs")
    public ModelAndView userBlogs(@RequestParam("s") Optional<String> keyword,Pageable pageable){
        Page<Blog> blogs;
        if (keyword.isPresent()){
            blogs=blogService.findAllByTitleContaining(keyword.get(),pageable);
        } else {
            blogs=blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/user-list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/admin/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error404");
            return modelAndView;
        }

    }

    @PostMapping("/admin/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated success");
        return modelAndView;
    }

    @GetMapping("/admin/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog blog=blogService.findById(id);
        if (blog!=null){
            ModelAndView modelAndView=new ModelAndView("/blog/delete");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("error404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-blog")
    public String deleteBLog(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:blogs";
    }

    @GetMapping("/admin/view-blog/{id}")
    public ModelAndView viewBlog(@PathVariable Long id){
        Blog blog=blogService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/blog/view");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }

    @GetMapping("/user/view-blog/{id}")
    public ModelAndView userViewBlog(@PathVariable Long id){
        Blog blog=blogService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/blog/user-view");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
}