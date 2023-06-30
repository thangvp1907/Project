package services.impl;

import models.Employee;
import repositories.IBaseRepositories;
import repositories.impl.EmployeeRepositories;
import services.IBaseServices;
import utils.Valid;

import java.util.List;
import java.util.Map;

public class EmployeeServices implements IBaseServices<Employee> {

    IBaseRepositories<Employee> repositories = new EmployeeRepositories();

    @Override
    public List<Employee> findAll(int i) {
        return repositories.findAll((i - 1) * 10);
    }

    @Override
    public List<Employee> findByCondition(String id, int index) {
        return repositories.findByCondition(id,(index - 1) * 10);
    }

    @Override
    public Employee findById(String id) {
        return repositories.findByCondition(id).get(0);
    }

    @Override
    public Map<String, String> create(Employee employee) {
        Map<String,String> error = Valid.getValidation(employee);
        List<Employee> list = repositories.findByCondition(employee.getId());
        // find no item by id in repo and error is empty --> create new employee
        if (list.isEmpty()){
            if (error.isEmpty()){
                repositories.create(employee);
            }
        } else {
            error.put("id","Id had been existed");
        }
        return error;
    }

    @Override
    public Map<String, String> update(Employee employee) {
        Map<String,String> error = Valid.getValidation(employee);
        if (error.isEmpty()){
            repositories.update(employee);
        }
        return error;
    }

    @Override
    public void delete(String id) {
        repositories.delete(id);

    }

    @Override
    public int maxPages() {
        double countItems = (double) repositories.countItem();
        return (int) Math.ceil (countItems/10);
    }
}
