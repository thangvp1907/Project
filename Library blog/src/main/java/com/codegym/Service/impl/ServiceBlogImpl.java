package com.codegym.Service.impl;

import com.codegym.Entity.Blog;
import com.codegym.Repository.RepositoryBlog;
import com.codegym.Service.ServiceBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBlogImpl implements ServiceBlog {
    public final RepositoryBlog repositoryBlog;

    public ServiceBlogImpl(RepositoryBlog repositoryBlog) {
        this.repositoryBlog = repositoryBlog;
    }

    @Override
    public boolean create(Blog blog) {
        repositoryBlog.save(blog);
        return true;
    }

    @Override
    public boolean update(Blog blog) {
        if (repositoryBlog.existsById(blog.getId())) {
            repositoryBlog.save(blog);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        repositoryBlog.deleteById(id);
        return true;
    }

    @Override
    public Blog findById(int id) {
        return repositoryBlog.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> findAllPage(Pageable pageable) {
        return repositoryBlog.findAll(pageable);
    }

    @Override
    public List<Blog> findAll() {
        return repositoryBlog.findAll();
    }

    @Override
    public List<Blog> findBlogByNameBlog(String name) {
        return repositoryBlog.findBlogByNameBlog(name);
    }
}
