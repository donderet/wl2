package ua.kpi.service;

import ua.kpi.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int id);
} 