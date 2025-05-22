package ua.kpi.service.impl;

import ua.kpi.model.Order;
import ua.kpi.model.OrderItem;
import ua.kpi.model.dao.OrderDAO;
import ua.kpi.model.dao.OrderItemDAO;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private OrderDAO orderDAO = daoFactory.createOrderDAO();
    private OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();

    @Override
    public void createOrder(Order order) {
        orderDAO.create(order);
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderDAO.findById(id);
        if (order != null) {
            List<OrderItem> items = orderItemDAO.findByOrderId(id);
            order.setItems(items);
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderDAO.findAll();
        for (Order order : orders) {
            List<OrderItem> items = orderItemDAO.findByOrderId(order.getId());
            order.setItems(items);
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.update(order);
        // Assuming update means clearing old items and adding new ones
        orderItemDAO.deleteByOrderId(order.getId());
        for (OrderItem item : order.getItems()) {
            item.setOrderId(order.getId());
            orderItemDAO.create(item);
        }
    }

    @Override
    public void deleteOrder(int id) {
        // Order items are deleted due to ON DELETE CASCADE in the database schema
        orderDAO.delete(id);
    }
} 