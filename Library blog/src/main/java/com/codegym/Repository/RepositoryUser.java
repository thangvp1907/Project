package com.codegym.Repository;


import com.codegym.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, Integer> {
    User findByAccount(String account);

}
