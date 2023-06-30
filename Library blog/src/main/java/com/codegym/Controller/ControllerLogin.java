package com.codegym.Controller;

import com.codegym.Entity.User;
import com.codegym.Service.ServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginBlog")
public class ControllerLogin {

    private final ServiceUser serviceUser;

    public ControllerLogin(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "view/loginUser/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("account") String account,
                          @RequestParam("password") String password
            ,Model model, HttpSession httpSession){
        User user = serviceUser.findByAccount(account);
        if(user == null){
            model.addAttribute("error", "Username hoặc password không được để trống");
            return "view/loginUser/login";
        }else if(password.equals(user.getPassword())){
            httpSession.setAttribute("account", user.getName());
            return "redirect:/newBlog/list";
        }else {
            model.addAttribute("error", "Username hoặc password không đúng");
            return "view/loginUser/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("account");
        return "redirect:/loginBlog/login";
    }

    @GetMapping("/createlogin")
    public String showCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "view/loginUser/createlogin";
    }

    @PostMapping("/createlogin")
    public String doLogin(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "view/loginUser/createlogin";
        }
        serviceUser.create(user);
        redirectAttributes.addFlashAttribute("mess", "thành công!!!");
        return "redirect:/loginBlog/login";
    }
}
