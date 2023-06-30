package com.codegym.Controller;

import com.codegym.Entity.Blog;
import com.codegym.Entity.User;
import com.codegym.Service.ServiceBlog;
import com.codegym.Service.ServiceCategory;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/newBlog")
public class ControllerBlog {

    private final ServiceBlog serviceBlog;
    private final ServiceCategory serviceCategory;

    public ControllerBlog(ServiceBlog serviceBlog, ServiceCategory serviceCategory) {
        this.serviceBlog = serviceBlog;
        this.serviceCategory = serviceCategory;
    }


//    @GetMapping("/list")
//    public ModelAndView showList(Pageable pageable){
//        Page<Blog> listBlog = serviceBlog.findAllPage(pageable);
//        ModelAndView modelAndView =new ModelAndView("/view/Blog/list");
//        modelAndView.addObject("blog",listBlog);
//        return modelAndView;
//    }
    @GetMapping("/list")
    public String showList(Model model){
        List<Blog> list = serviceBlog.findAll();
        model.addAttribute("blog",list);
        model.addAttribute("category",serviceCategory.findAll());
        return "view/Blog/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("category", serviceCategory.findAll());
        return "view/Blog/create";
    }

    @PostMapping("/create")
    public String doCreate(@Validated @ModelAttribute("blog") Blog blog, BindingResult bindingResult, Model model,User user,HttpSession httpSession) throws ParseException {
        new Blog().validate(blog,bindingResult);
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("category", serviceCategory.findAll());
            return "view/Blog/create";
        }else {
            LocalDateTime today = LocalDateTime.now();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(today));
            blog.setDate(date);
            blog.setCategory(serviceCategory.findById(blog.getCategory().getIdCategory()));
            serviceBlog.create(blog);
            return "redirect:/newBlog/list";
        }
    }

    @GetMapping("/detail/{id}")
    public String showDetail(Model model,
                             @PathVariable("id") int id) {
        Blog blog = serviceBlog.findById(id);
        model.addAttribute("blog", blog);
        return "/view/Blog/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceBlog.deleteById(id);
        return "redirect:/newBlog/list";
    }


    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model){
        List<Blog> list = serviceBlog.findBlogByNameBlog(name);
        model.addAttribute("blog",list);
        model.addAttribute("name",name);
        return "view/Blog/list";
    }

}
