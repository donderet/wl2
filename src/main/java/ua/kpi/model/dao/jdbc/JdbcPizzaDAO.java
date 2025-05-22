package ua.kpi.model.dao.jdbc;

import ua.kpi.model.Pizza;
import ua.kpi.model.dao.PizzaDAO;
import ua.kpi.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPizzaDAO implements PizzaDAO {

    @Override
    public void create(Pizza pizza) {
        String sql = "INSERT INTO pizzas (name, price) VALUES (?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pizza.getName());
            stmt.setBigDecimal(2, pizza.getPrice());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pizza.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pizza findById(int id) {
        String sql = "SELECT * FROM pizzas WHERE id = ?";
        Pizza pizza = null;
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pizza = new Pizza();
                    pizza.setId(rs.getInt("id"));
                    pizza.setName(rs.getString("name"));
                    pizza.setPrice(rs.getBigDecimal("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizza;
    }

    @Override
    public List<Pizza> findAll() {
        String sql = "SELECT * FROM pizzas";
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setId(rs.getInt("id"));
                pizza.setName(rs.getString("name"));
                pizza.setPrice(rs.getBigDecimal("price"));
                pizzas.add(pizza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle exceptions properly
        }
        return pizzas;
    }

    @Override
    public void update(Pizza pizza) {
        String sql = "UPDATE pizzas SET name = ?, price = ? WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pizza.getName());
            stmt.setBigDecimal(2, pizza.getPrice());
            stmt.setInt(3, pizza.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pizzas WHERE id = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 