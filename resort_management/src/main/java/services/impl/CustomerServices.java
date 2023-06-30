package services.impl;

import models.Customer;
import repositories.IBaseRepositories;
import repositories.impl.CustomerRepository;
import services.IBaseServices;
import utils.Valid;

import java.util.List;
import java.util.Map;

public class CustomerServices implements IBaseServices<Customer> {
    IBaseRepositories<Customer> repositories = new CustomerRepository();

    @Override
    public List<Customer> findAll(int index) {
        return repositories.findAll((index - 1) * 10);
    }

    @Override
    public List<Customer> findByCondition(String id, int index) {
        return repositories.findByCondition(id, (index - 1) * 10);
    }

    @Override
    public Customer findById(String id) {
        return repositories.findByCondition(id).get(0);
    }

    @Override
    public Map<String, String> create(Customer customer) {
        Map<String,String> error = Valid.getValidation(customer);
        String id = String.valueOf(customer.getId());
        if (repositories.findByCondition(id).isEmpty() && error.isEmpty()){
            repositories.create(customer);
            return error;
        }
        error.put("id","Id had been existed");
        return error;
    }
    @Override
    public void delete(String id) {
        repositories.delete(id);
    }

    @Override
    public Map<String, String> update(Customer customer) {
        Map<String,String> error = Valid.getValidation(customer);
        if (error.isEmpty()){
            repositories.update(customer);
        }
        return error;
    }

    @Override
    public int maxPages() {
        double countItems = (double) repositories.countItem();
        return (int) Math.ceil (countItems/10);
//        return countItems % 8 == 0 ? countItems /8 : countItems /8 + 1;
    }
}
