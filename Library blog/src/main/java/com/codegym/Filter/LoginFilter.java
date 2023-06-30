package com.codegym.Filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    private static final String loginRequiredURLs = "/loginBlog/createlogin";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getServletPath();
//        if () {
//            chain.doFilter(request, response);
//            return;
//        }
        if (path.contains("loginBlog/login") || path.contains("css") || path.equals(loginRequiredURLs) || path.contains("createlogin.css")  ) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession httpSession = req.getSession();
        String account = (String) httpSession.getAttribute("account");
        if (account == null || "".equals(account)) {
            res.sendRedirect("/loginBlog/login");
        }
        chain.doFilter(request, response);
    }
}
