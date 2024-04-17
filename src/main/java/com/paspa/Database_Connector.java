package com.paspa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connector {
    private static final String URL = "jdbc:mysql://192.168.1.99:3306/snipeit"; 
    private static final String USER = "admin"; 
    private static final String PASSWORD = "Pagano01"; 

    private Connection connection;

    public Database_Connector() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
