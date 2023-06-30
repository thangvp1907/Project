package com.codegym.Repository;

import com.codegym.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategory extends JpaRepository<Category,Integer> {

}
