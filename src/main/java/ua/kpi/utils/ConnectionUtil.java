package ua.kpi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionUtil {

    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.user";
    private static final String DB_PASSWORD_KEY = "db.password";

    private static ResourceBundle dbBundle;

    static {
        dbBundle = ResourceBundle.getBundle("db");
    }

    public static Connection getConnection() throws SQLException {
        String url = dbBundle.getString(DB_URL_KEY);
        String user = dbBundle.getString(DB_USER_KEY);
        String password = dbBundle.getString(DB_PASSWORD_KEY);
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} 