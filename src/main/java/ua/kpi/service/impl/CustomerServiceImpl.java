package ua.kpi.service.impl;

import ua.kpi.model.Customer;
import ua.kpi.model.dao.CustomerDAO;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private CustomerDAO customerDAO = daoFactory.createCustomerDAO();

    @Override
    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDAO.delete(id);
    }
} 