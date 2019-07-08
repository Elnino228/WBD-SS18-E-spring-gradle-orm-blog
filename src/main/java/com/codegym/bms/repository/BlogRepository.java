package com.codegym.bms.repository;

import com.codegym.bms.model.Blog;
import com.codegym.bms.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByTitleContaining(String keyword, Pageable pageable);
}
