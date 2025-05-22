package ua.kpi.model.dao;

import ua.kpi.model.OrderItem;

import java.util.List;

public interface OrderItemDAO {
    void create(OrderItem orderItem);
    OrderItem findById(int id);
    List<OrderItem> findByOrderId(int orderId);
    void update(OrderItem orderItem);
    void delete(int id);
    void deleteByOrderId(int orderId);
} 