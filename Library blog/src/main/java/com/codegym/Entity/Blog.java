package com.codegym.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Validated
public class Blog implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_blog", columnDefinition = "varchar(50)")
    @NotBlank(message = "Name không được để trống")
    private String nameBlog;

    @Column(name = "tac_gia", columnDefinition = "varchar(50)")
    @NotBlank(message = "author không được để trống")
    private String author;

    @Column(name = "noi_dung", columnDefinition = "varchar(125)")
    @NotBlank(message = "content không được để trống")
    @Size(max = 125, message = "Noi dung qua dai,Vui long giam bot")
    private String content;

    @Column(name = "ngay_comment", columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "blogUser", cascade = CascadeType.ALL)
    private List<User> userList;


    public Blog() {

    }

    public Blog(int id, String nameBlog, String author, String content, Date date, Category category, List<User> userList) {
        this.id = id;
        this.nameBlog = nameBlog;
        this.author = author;
        this.content = content;
        this.date = date;
        this.category = category;
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBlog() {
        return nameBlog;
    }

    public void setNameBlog(String nameBlog) {
        this.nameBlog = nameBlog;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Blog.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Blog blog = (Blog) target;
        String content = blog.getContent();
        if (content == null) {
            errors.rejectValue("content", "null"
                    , null, "content không được để trống");
        }
    }
}
