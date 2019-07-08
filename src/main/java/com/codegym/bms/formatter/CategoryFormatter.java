package com.codegym.bms.formatter;

import com.codegym.bms.model.Category;
import com.codegym.bms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<Category> {


    private CategoryService categoryService;

    @Autowired
    public CategoryFormatter(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Category category,Locale locale){
        return "["+category.getId()+", "+category.getName()+"]";
    }
}
