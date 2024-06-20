package dev.yocca.fleeca.database;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author mayocca
 */
public class DatabaseConnection {

    private volatile static Connection conn;

    private DatabaseConnection() {}

    private static Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";

        Properties props = new Properties();
        // @TODO: Use environment file
        props.setProperty("ApplicationName", "Fleeca");
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");

        return DriverManager.getConnection(url, props);
    }

    public static Connection getInstance() throws SQLException {
        synchronized (DatabaseConnection.class) {
            if (conn == null) {
                conn = createConnection();
            }
        }

        return conn;
    }
}
