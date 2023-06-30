package controllers;

import models.Employee;
import models.support_person.Degree;
import models.support_person.Gender;
import models.support_person.Position;
import repositories.ITypeRepositories;
import repositories.impl.support_person.DegreeRepositories;
import repositories.impl.support_person.GenderRepositories;
import repositories.impl.support_person.PositionRepositories;
import services.IBaseServices;
import services.impl.EmployeeServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Optional;

@WebServlet (name = "EmployeeServlet", urlPatterns = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private IBaseServices<Employee> repositories = new EmployeeServices();
    private ITypeRepositories<Gender> genderRepositories = new GenderRepositories();
    private ITypeRepositories<Position> positionRepositories = new PositionRepositories();
    private ITypeRepositories<Degree> degreeRepositories = new DegreeRepositories();

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
            case "paging":
                findByCondition(req,resp);
            default:
                findByCondition(req,resp);
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = 1;
        req.setAttribute("gender",genderRepositories.findAll());
        req.setAttribute("degree",degreeRepositories.findAll());
        req.setAttribute("position",positionRepositories.findAll());
        req.setAttribute("list",repositories.findAll(index));
        req.getRequestDispatcher("employeeJSP/EmployeeList.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        repositories.delete(id);
    }

    private void findByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        int index = Integer.parseInt(Optional.ofNullable(req.getParameter("index")).orElse("1"));
        req.setAttribute("gender",genderRepositories.findAll());
        req.setAttribute("degree",degreeRepositories.findAll());
        req.setAttribute("position",positionRepositories.findAll());
        req.setAttribute("list",repositories.findByCondition(id, index));
        req.setAttribute("maxPages",repositories.maxPages());
        req.setAttribute("index",index);
        req.setAttribute("id_search",id);
        req.getRequestDispatcher("employeeJSP/EmployeeList.jsp").forward(req,resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("gender",genderRepositories.findAll());
        req.setAttribute("degree",degreeRepositories.findAll());
        req.setAttribute("position",positionRepositories.findAll());
        req.getRequestDispatcher("employeeJSP/EmployeeRegistry.jsp").forward(req,resp);
    }

    private void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("item", repositories.findById(id));
        req.setAttribute("gender",genderRepositories.findAll());
        req.setAttribute("degree",degreeRepositories.findAll());
        req.setAttribute("position",positionRepositories.findAll());
        req.getRequestDispatcher("employeeJSP/EmployeeRegistry.jsp").forward(req,resp);
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

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Date date = Date.valueOf(req.getParameter("date"));
        int phone = Integer.parseInt(req.getParameter("phone"));
        int gender = Integer.parseInt(req.getParameter("gender"));
        int degree = Integer.parseInt(req.getParameter("degree"));
        int position = Integer.parseInt(req.getParameter("position"));
        int salary = Integer.parseInt(req.getParameter("salary"));
        Employee employee = new Employee(id, name, address, date, phone, gender, degree, position, salary);
        Map<String, String> error = repositories.update(employee);
        if (error.isEmpty()) {
            return;
        }
        req.setAttribute("message", "Update is fail!!!");
        req.setAttribute("error", error);
        req.setAttribute("item", employee);
        req.setAttribute("gender", genderRepositories.findAll());
        req.setAttribute("degree", degreeRepositories.findAll());
        req.setAttribute("position", positionRepositories.findAll());
        req.getRequestDispatcher("employeeJSP/EmployeeRegistry.jsp").forward(req, resp);
    }
    private void createNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Date date = Date.valueOf(req.getParameter("date"));
        int phone = Integer.parseInt(req.getParameter("phone"));
        int gender = Integer.parseInt(req.getParameter("gender"));
        int degree = Integer.parseInt(req.getParameter("degree"));
        int position = Integer.parseInt(req.getParameter("position"));
        int salary = Integer.parseInt(req.getParameter("salary"));
        Employee employee = new Employee(id,name,address,date,phone,gender,degree,position,salary);
        Map<String,String> error = repositories.create(employee);
        if (error.isEmpty()){
            return;
        }
        req.setAttribute("message","Create is fail!!!");
        req.setAttribute("error",error);
        req.setAttribute("item",employee);
        req.setAttribute("gender",genderRepositories.findAll());
        req.setAttribute("degree",degreeRepositories.findAll());
        req.setAttribute("position",positionRepositories.findAll());
        req.getRequestDispatcher("employeeJSP/EmployeeRegistry.jsp").forward(req,resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
