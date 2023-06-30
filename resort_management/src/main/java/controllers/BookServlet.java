package controllers;

import models.Book;
import services.IBaseServices;
import services.impl.BookServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Optional;

@WebServlet (name = "BookServlet", urlPatterns = "/BookServlet")
public class BookServlet extends HttpServlet {
    private IBaseServices<Book> bookRepositories = new BookServices();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");
        switch (action){
            case "update":
                getInfo(req,resp); break;
            case "create":
                create(req,resp); break;
            case "search":
                findByCondition(req,resp); break;
            case "delete":
                delete(req,resp); break;
            default:
                findByCondition(req,resp);
        }

    }
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int index = req.getParameter("item");
        int i = 1;
        req.setAttribute("list", bookRepositories.findAll(i));
        req.getRequestDispatcher("bookJSP/BookList.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        bookRepositories.delete(id);
        findAll(req,resp);}

    private void findByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        int index = Integer.parseInt(Optional.ofNullable(req.getParameter("index")).orElse("1"));
        req.setAttribute("list", bookRepositories.findByCondition(id, index));
        req.setAttribute("maxPages",bookRepositories.maxPages());
        req.setAttribute("index",index);
        req.setAttribute("id_search",id);
        req.getRequestDispatcher("bookJSP/BookList.jsp").forward(req,resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("bookJSP/BookRegistry.jsp").forward(req,resp);
    }

    private void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("item", bookRepositories.findById(id));

        req.getRequestDispatcher("bookJSP/BookRegistry.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("");
        switch (action){
            case "doUpdate":
                update(req,resp);
                break;
            case "doCreate":
                createNew(req,resp);
                break;
            default:
                findByCondition(req,resp);
        }
    }

    private void createNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String customer_name = req.getParameter("customer_name");
        String facility_name = req.getParameter("facility_name");
        int companion = Integer.parseInt(req.getParameter("companion"));
        Date date_in = Date.valueOf(req.getParameter("date_in"));
        Date date_out = Date.valueOf(req.getParameter("date_out"));
        Book book = new Book(id,customer_name,facility_name,companion,date_in,date_out);
        Map<String, String> error = bookRepositories.create(book);
        if (error.isEmpty()) {
            return;
        }
        req.setAttribute("message", "Create is fail!!!");
        req.setAttribute("error", error);
        req.setAttribute("item", book);
        req.getRequestDispatcher("bookJSP/BookRegistry.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String customer_name = req.getParameter("customer_name");
        String facility_name = req.getParameter("facility_name");
        int companion = Integer.parseInt(req.getParameter("companion"));
        Date date_in = Date.valueOf(req.getParameter("date_in"));
        Date date_out = Date.valueOf(req.getParameter("date_out"));
        Book book = new Book(id,customer_name,facility_name,companion,date_in,date_out);
        Map<String, String> error = bookRepositories.update(book);
        if (error.isEmpty()) {
            return;
        }
        req.setAttribute("message", "Update is fail!!!");
        req.setAttribute("error", error);
        req.setAttribute("item", book);
        req.getRequestDispatcher("bookJSP/BookRegistry.jsp").forward(req, resp); }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
