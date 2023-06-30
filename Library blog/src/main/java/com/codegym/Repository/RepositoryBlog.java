package com.codegym.Repository;

import com.codegym.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryBlog extends JpaRepository<Blog, Integer> {
    List<Blog> findBlogByNameBlog(String name);
}
