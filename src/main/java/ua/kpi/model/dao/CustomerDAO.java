package ua.kpi.model.dao;

import ua.kpi.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void create(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(int id);
} 