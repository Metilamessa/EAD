package com.example.bookstore;

import java.io.IOException;import java.io.PrintWriter;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import javax.servlet.ServletException;import javax.servlet.annotation.WebServlet;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;
@WebServlet("/searchBooks")public class SearchBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("query");

        try (Connection connection = new DBConnectionManager().getConnection()) {
            String sql = "SELECT * FROM Books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setString(2, "%" + searchQuery + "%");

            ResultSet rs = statement.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Status</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                        rs.getString("title") + "</td><td>" +
                        rs.getString("author") + "</td><td>" +
                        rs.getString("price") + "</td></tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

