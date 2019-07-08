package com.codegym.bms.repository;

import com.codegym.bms.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
}
