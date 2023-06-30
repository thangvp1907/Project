package controllers;

import models.Facility;
import models.FacilityDto;
import models.support_facility.FacilityType;
import models.support_facility.Period;
import models.support_facility.UsedTimes;
import repositories.ITypeRepositories;
import repositories.impl.support_facility.FacilityTypeRepositories;
import repositories.impl.support_facility.PeriodRepositories;
import repositories.impl.support_facility.UsedTimesRepositories;
import services.IBaseServices;
import services.impl.FacilityServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet (name = "FacilityServlet", urlPatterns = "/FacilityServlet")
public class FacilityServlet extends HttpServlet {
    private IBaseServices<FacilityDto> facilityRepositories = new FacilityServices();
    private ITypeRepositories<Period> periodRepositories = new PeriodRepositories();
    private ITypeRepositories<FacilityType> facilityTypeRepositories = new FacilityTypeRepositories();
    private ITypeRepositories<UsedTimes> usedTimesRepositories = new UsedTimesRepositories();

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
        int index = 1;
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.setAttribute("list",facilityRepositories.findAll(index));
        req.getRequestDispatcher("facilityJSP/FacilityList.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        facilityRepositories.delete(id);
        findAll(req,resp);
    }

    private void findByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        int index = Integer.parseInt(Optional.ofNullable(req.getParameter("index")).orElse("1"));
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.setAttribute("list",facilityRepositories.findByCondition(id, index));
        req.setAttribute("maxPages",facilityRepositories.maxPages());
        req.setAttribute("index",index);
        req.setAttribute("id_search",id);
        req.getRequestDispatcher("facilityJSP/FacilityList.jsp").forward(req,resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.getRequestDispatcher("facilityJSP/FacilityRegistry.jsp").forward(req,resp);
    }

    private void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = Optional.ofNullable((req.getParameter("id"))).orElse("");
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.setAttribute("item",facilityRepositories.findById(id));
        req.getRequestDispatcher("facilityJSP/FacilityRegistry.jsp").forward(req,resp);
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
        String id = (req.getParameter("id"));
        int period = Integer.parseInt(req.getParameter("period"));
        int area = Integer.parseInt(req.getParameter("area"));
        int max_person = Integer.parseInt(req.getParameter("max_person"));
        int price = Integer.parseInt(req.getParameter("price"));
        int type = Integer.parseInt(req.getParameter("type"));
        int floor = Integer.parseInt(req.getParameter("floor"));
        int pool_area = Integer.parseInt(req.getParameter("pool_area"));
        FacilityDto facility = new FacilityDto(new Facility(id, period, area, max_person, price, type, floor, pool_area));
        Map<String, String> error = facilityRepositories.create(facility);
        if (error.isEmpty()) {
            return;
        }
        req.setAttribute("message", "Create is fail!!!");
        req.setAttribute("error", error);
        req.setAttribute("item", facility);
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.getRequestDispatcher("facilityJSP/FacilityRegistry.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (req.getParameter("id"));
        int period = Integer.parseInt(req.getParameter("period"));
        int area = Integer.parseInt(req.getParameter("area"));
        int max_person = Integer.parseInt(req.getParameter("max_person"));
        int price = Integer.parseInt(req.getParameter("price"));
        int type = Integer.parseInt(req.getParameter("type"));
        int floor = Integer.parseInt(req.getParameter("floor"));
        int pool_area = Integer.parseInt(req.getParameter("pool_area"));
        FacilityDto facility = new FacilityDto(new Facility(id, period, area, max_person, price, type, floor, pool_area));
        Map<String, String> error = facilityRepositories.update(facility);
        if (error.isEmpty()) {
            return;
        }
        req.setAttribute("message", "Update is fail!!!");
        req.setAttribute("error", error);
        req.setAttribute("item", facility);
        req.setAttribute("period",periodRepositories.findAll());
        req.setAttribute("type",facilityTypeRepositories.findAll());
        req.setAttribute("time",usedTimesRepositories.findAll());
        req.getRequestDispatcher("facilityJSP/FacilityRegistry.jsp").forward(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
