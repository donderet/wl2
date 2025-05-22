package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.*;
import ua.kpi.model.dao.exception.DaoException;

import java.sql.*;

public class JdbcDaoFactory extends DaoFactory {

    public JdbcDaoFactory() {
    }

    @Override
    public CustomerDAO createCustomerDAO() {
        return new JdbcCustomerDAO();
    }

    @Override
    public PizzaDAO createPizzaDAO() {
        return new JdbcPizzaDAO();
    }

    @Override
    public OrderItemDAO createOrderItemDAO() {
        return new JdbcOrderItemDAO();
    }

    @Override
    public OrderDAO createOrderDAO() {
        return new JdbcOrderDAO();
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(ua.kpi.utils.ConnectionUtil.getConnection());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
