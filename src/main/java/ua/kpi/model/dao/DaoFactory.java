package ua.kpi.model.dao;

import ua.kpi.model.dao.exception.DaoException;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public abstract class DaoFactory {
    private static volatile DaoFactory instance;

    protected DaoFactory() {}

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                         ResourceBundle dbBundle = ResourceBundle.getBundle("db");
                         String factoryClassName = dbBundle.getString("factory.class");
                         instance = (DaoFactory) Class.forName(factoryClassName).getDeclaredConstructor().newInstance();

                    } catch (IllegalAccessException |
                             InstantiationException | ClassNotFoundException e ) {
                        throw new DaoException(e); 
                    } catch (InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e); 
                    }
                }
            }
        }
        return instance;
    }

    public abstract CustomerDAO createCustomerDAO();
    public abstract PizzaDAO createPizzaDAO();
    public abstract OrderItemDAO createOrderItemDAO();
    public abstract OrderDAO createOrderDAO();

    public abstract DaoConnection getConnection();
}
