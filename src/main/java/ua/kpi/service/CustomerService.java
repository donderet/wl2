package ua.kpi.service;

import ua.kpi.model.Customer;

import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
} 