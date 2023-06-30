package com.codegym.Service;

import com.codegym.Entity.Blog;
import com.codegym.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServiceUser {
    boolean create(User user);

    boolean findById(int id);

    User findByAccount (String account);

}
