package ua.kpi.model.dao.jdbc;

import ua.kpi.model.Order;
import ua.kpi.model.OrderItem;
import ua.kpi.model.dao.OrderDAO;
import ua.kpi.model.dao.OrderItemDAO;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderDAO implements OrderDAO {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public void create(Order order) {
        String sql = "INSERT INTO orders (customer_id) VALUES (?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }

            // Create order items
            OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();
            for (OrderItem item : order.getItems()) {
                item.setOrderId(order.getId());
                orderItemDAO.create(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = null;
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setCustomerId(rs.getInt("customer_id"));

                    // Get order items
                    OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();
                    List<OrderItem> items = orderItemDAO.findByOrderId(order.getId());
                    order.setItems(items);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customer_id"));

                // Get order items
                OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();
                List<OrderItem> items = orderItemDAO.findByOrderId(order.getId());
                order.setItems(items);

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET customer_id = ? WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();

            // Update order items (delete existing and create new ones)
            OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();
            orderItemDAO.deleteByOrderId(order.getId());
            for (OrderItem item : order.getItems()) {
                item.setOrderId(order.getId());
                orderItemDAO.create(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        // Delete order items first due to foreign key constraint with ON DELETE CASCADE
        OrderItemDAO orderItemDAO = daoFactory.createOrderItemDAO();
        orderItemDAO.deleteByOrderId(id);

        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 