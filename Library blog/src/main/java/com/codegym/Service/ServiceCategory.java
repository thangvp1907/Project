package com.codegym.Service;


import com.codegym.Entity.Category;


import java.util.List;

public interface ServiceCategory {

    boolean create(Category category);

    boolean update(Category category);

    boolean deleteById(int id);

    Category findById(int id);

    List<Category> findAll();
}
