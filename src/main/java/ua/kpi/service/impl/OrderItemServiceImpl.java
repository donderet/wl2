package ua.kpi.service.impl;

import ua.kpi.model.OrderItem;
import ua.kpi.model.dao.OrderItemDAO;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();

    @Override
    public void createOrderItem(OrderItem orderItem) {
        orderItemDAO.create(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        return orderItemDAO.findById(id);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemDAO.findByOrderId(orderId);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemDAO.update(orderItem);
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemDAO.delete(id);
    }

    @Override
    public void deleteOrderItemsByOrderId(int orderId) {
        orderItemDAO.deleteByOrderId(orderId);
    }
} 