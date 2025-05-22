package ua.kpi.service;

import ua.kpi.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    void createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(int id);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int id);
    void deleteOrderItemsByOrderId(int orderId);
} 