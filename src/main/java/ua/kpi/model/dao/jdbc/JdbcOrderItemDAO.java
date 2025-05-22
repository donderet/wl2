package ua.kpi.model.dao.jdbc;

import ua.kpi.model.OrderItem;
import ua.kpi.model.dao.OrderItemDAO;
import ua.kpi.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderItemDAO implements OrderItemDAO {

    @Override
    public void create(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, pizza_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getPizzaId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderItem.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem findById(int id) {
        String sql = "SELECT * FROM order_items WHERE id = ?";
        OrderItem orderItem = null;
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orderItem = new OrderItem();
                    orderItem.setId(rs.getInt("id"));
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setPizzaId(rs.getInt("pizza_id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(rs.getInt("id"));
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setPizzaId(rs.getInt("pizza_id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public void update(OrderItem orderItem) {
        String sql = "UPDATE order_items SET order_id = ?, pizza_id = ?, quantity = ? WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getPizzaId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setInt(4, orderItem.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle exceptions properly
        }
    }

    @Override
    public void deleteByOrderId(int orderId) {
        String sql = "DELETE FROM order_items WHERE order_id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle exceptions properly
        }
    }
}