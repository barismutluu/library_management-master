package org.example.librarymanagement.business.abstracts;

import org.example.librarymanagement.entities.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Category save(Category category);
    Category get(long id);
    Page<Category> cursor(int page, int pageSize);
    Category update(Category category);
    boolean delete(long id);
}
