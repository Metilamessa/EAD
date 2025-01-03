package com.example.bookstore;


import java.io.IOException;import java.io.PrintWriter;import java.sql.Connection;import java.sql.PreparedStatement;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/addBook")public class BookRegistrationServlet extends HttpServlet {
	private DBConnectionManager dbConnectionManager;

    public BookRegistrationServlet() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dbConnectionManager = (DBConnectionManager) context.getBean("dbConnectionManager");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

        try (Connection connection = new DBConnectionManager().getConnection()) {
            String sql = "INSERT INTO Books (title, author, status) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, price);

            int rows = statement.executeUpdate();

            PrintWriter out = response.getWriter();
            if (rows > 0) {
                out.println("Book added successfully!");
            } else {
                out.println("Failed to add the book.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



    