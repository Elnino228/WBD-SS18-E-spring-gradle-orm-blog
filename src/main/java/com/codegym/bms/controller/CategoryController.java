package com.codegym.bms.controller;


import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import com.codegym.bms.service.BlogService;
import com.codegym.bms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable Long id){
        Category category=categoryService.findById(id);

        Iterable<Blog> blogs=blogService.findAllByCategory(category);
        ModelAndView modelAndView=new ModelAndView("/category/view");
        modelAndView.addObject("blogs",blogs);
        modelAndView.addObject("category",category);
        return  modelAndView;
    }

    @GetMapping("/categories")
    public ModelAndView listCategory(){
        Iterable<Category> categories=categoryService.findAll();
        ModelAndView modelAndView=new ModelAndView("/category/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView=new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView save(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView=new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message","New category created success");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        Category category=categoryService.findById(id);
        if (category!=null){
            ModelAndView modelAndView=new ModelAndView("/category/edit");
            modelAndView.addObject("category",category);
            return modelAndView;
        } else {
            ModelAndView modelAndView=new ModelAndView("/error404");
            return modelAndView;
        }

    }

    @PostMapping("/edit-category")
    public ModelAndView update(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView=new ModelAndView("/category/edit");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message","Category updated success");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Category category=categoryService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/category/delete");
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @PostMapping("/delete-category")
    public String removeCategory(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:categories";
    }

}
