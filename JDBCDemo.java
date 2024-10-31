package jdbc_connection;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "studentsdb";
        String username = "root"; 
        String password = "metimysqlhawi12345#"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            System.out.println("Database '" + dbName + "' created or already exists.");

            statement.close();
            connection.close();

            String dbUrl = url + dbName;
            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "firstname VARCHAR(255), "
                    + "lastname VARCHAR(255), "
                    + "grade INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'students' created or already exists.");

            String clearTableSQL = "DELETE FROM students";
            statement.executeUpdate(clearTableSQL);
            System.out.println("Existing data cleared from 'students' table.");

            String insertSQL = "INSERT INTO students (firstname, lastname, grade) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            String[][] sampleData = {
                    {"Melat", "Tesfaye", "85"},
                    {"Bruk", "Asefa", "90"},
                    {"Sara", "Nega", "78"},
                    {"Jemal", "Edris", "92"},
                    {"Haile", "Naol", "88"}
            };
            for (String[] data : sampleData) {
                preparedStatement.setString(1, data[0]);
                preparedStatement.setString(2, data[1]);
                preparedStatement.setInt(3, Integer.parseInt(data[2]));
                preparedStatement.executeUpdate();
            }
            System.out.println("Sample data inserted successfully!");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 5");
            System.out.println("Retrieving data:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");
                System.out.println("ID: " + id + ", Name: " + firstname + " " + lastname + ", Grade: " + grade);
            }

            String updateSQL = "UPDATE students SET firstname = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            updateStatement.setString(1, "UpdatedFirstName");
            updateStatement.setInt(2, 1); 
            updateStatement.executeUpdate();
            System.out.println("Updated student with ID = 1.");

            String deleteSQL = "DELETE FROM students WHERE id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setInt(1, 2); 
            deleteStatement.executeUpdate();
            System.out.println("Deleted student with ID = 2.");

            String avgSQL = "SELECT AVG(grade) AS average_grade FROM students";
            ResultSet avgResultSet = statement.executeQuery(avgSQL);
            if (avgResultSet.next()) {
                double averageGrade = avgResultSet.getDouble("average_grade");
                System.out.println("Average Grade: " + averageGrade);
            }

            preparedStatement.close();
            updateStatement.close();
            deleteStatement.close();
            resultSet.close();
            avgResultSet.close();
            statement.close();
            connection.close();
            System.out.println("Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
