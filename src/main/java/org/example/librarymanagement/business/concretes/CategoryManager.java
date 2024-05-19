package org.example.librarymanagement.business.concretes;

import org.example.librarymanagement.business.abstracts.CategoryService;
import org.example.librarymanagement.dao.CategoryRepo;
import org.example.librarymanagement.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category get(long id) {
        return null;
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
