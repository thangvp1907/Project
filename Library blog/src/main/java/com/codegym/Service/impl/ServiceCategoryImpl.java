package com.codegym.Service.impl;

import com.codegym.Entity.Category;
import com.codegym.Repository.RepositoryCategory;
import com.codegym.Service.ServiceCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryImpl implements ServiceCategory {
    private final RepositoryCategory repositoryCategory;

    public ServiceCategoryImpl(RepositoryCategory repositoryCategory) {
        this.repositoryCategory = repositoryCategory;
    }

    @Override
    public boolean create(Category category) {
        repositoryCategory.save(category);
        return true;
    }

    @Override
    public boolean update(Category category) {
        if (repositoryCategory.existsById(category.getIdCategory())) {
            repositoryCategory.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        if (repositoryCategory.existsById(id)) {
            repositoryCategory.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Category findById(int id) {
        return repositoryCategory.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return repositoryCategory.findAll();
    }
}
