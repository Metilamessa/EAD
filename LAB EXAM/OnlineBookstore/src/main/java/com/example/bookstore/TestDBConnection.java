package com.example.bookstore;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        DBConnectionManager manager = new DBConnectionManager();

        try {
            Connection connection = manager.openConnection();
            System.out.println("Database connected successfully!");

            manager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }
}

