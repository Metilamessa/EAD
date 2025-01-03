



package com.example.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class DBConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/BookstoreDB";
    private String username = "root";
    private String password = "metimysqlhawi12345#";

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}



