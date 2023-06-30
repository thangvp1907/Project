package com.codegym.Entity;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@Validated
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_account", columnDefinition = "varchar(50)")
    @NotBlank(message = "ten tai khoan khong duoc de trong!!!")
    private String account;

    @Column(name = "mat_khau", columnDefinition = "varchar(50)")
    @NotBlank(message = "mat khau khong duoc de trong!!!")
    private String password;
    @Column(name = "ho_ten", columnDefinition = "varchar(50)")
    @NotBlank(message = "khong udoc de trong!!!")
    private String name;
    @Column(name = "email", columnDefinition = "varchar(50)")
    @NotEmpty(message = "Email khong duoc de trong!!!")
    private String email;

    @NotNull(message = "khong duoc de trong!!!")
    @Column(name = "tuoi", columnDefinition = "int")
    private int age;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blogUser;


    public User() {

    }

    public User(String account, String password, String name, String email, int age, Blog blogUser) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.blogUser = blogUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Blog getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(Blog blogUser) {
        this.blogUser = blogUser;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String email = user.getEmail();
        if(email.equals("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            errors.rejectValue("email","null",
                    null,"Email khong dung dinh dang!!!");
        }
    }
}
