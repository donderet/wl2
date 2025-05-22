package ua.kpi.model.dao;

import ua.kpi.model.Order;

import java.util.List;

public interface OrderDAO {
    void create(Order order);
    Order findById(int id);
    List<Order> findAll();
    void update(Order order);
    void delete(int id);
} 