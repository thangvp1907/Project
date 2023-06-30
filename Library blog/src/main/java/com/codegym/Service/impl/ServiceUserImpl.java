package com.codegym.Service.impl;

import com.codegym.Entity.User;
import com.codegym.Repository.RepositoryUser;
import com.codegym.Service.ServiceUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUserImpl implements ServiceUser {

    private final RepositoryUser repositoryUser;

    public ServiceUserImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public boolean create(User user) {
        repositoryUser.save(user);
        return true;
    }


    @Override
    public boolean findById(int id) {
        repositoryUser.findById(id);
        return true;
    }

    @Override
    public User findByAccount(String account) {
        return repositoryUser.findByAccount(account);
    }


}
