package org.example;


import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static final String URL =  "jdbc:mysql://localhost:3306/cbs";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
