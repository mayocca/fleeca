/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yocca.fleeca.database;

import dev.yocca.fleeca.exceptions.DatabaseConnectionException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author mayocca
 */
public class DatabaseConnection {

    private volatile static Connection conn;

    private DatabaseConnection() {}

    private static Connection createConnection() throws DatabaseConnectionException {
        Connection conn;
        String url = "jdbc:postgresql://localhost:5432/postgres";

        Properties props = new Properties();
        // @TODO: Use environment file
        props.setProperty("ApplicationName", "Fleeca");
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");

        try {
            conn = DriverManager.getConnection(url, props);    
        } catch (SQLException e) {
            throw new DatabaseConnectionException("No se pudo realizar la conexión a la base de datos");
        }
        
        
        return conn;
    }

    public static Connection getInstance() throws DatabaseConnectionException {
        synchronized (DatabaseConnection.class) {
            if (conn == null) {
                conn = createConnection();
            }
        }

        return conn;
    }
}
