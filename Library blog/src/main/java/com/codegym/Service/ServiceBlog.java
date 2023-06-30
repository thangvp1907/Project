package com.codegym.Service;

import com.codegym.Entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceBlog {
    boolean create(Blog blog);

    boolean update(Blog blog);

    boolean deleteById(int id);

    Blog findById(int id);

    Page<Blog> findAllPage(Pageable pageable);

    List<Blog> findAll();

    List<Blog> findBlogByNameBlog(String name);

}
