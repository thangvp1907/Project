package com.codegym.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCategory;

    @Column(name = "ten_category", columnDefinition = "varchar(50)")
    private String nameCategory;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Blog> blogList;


    public Category() {

    }

    public Category(int idCategory, String nameCategory, List<Blog> blogList) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.blogList = blogList;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
}
