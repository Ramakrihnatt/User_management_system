package com.leucine.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leucine.util.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Fetch software list and forward to requestAccess.jsp
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // Retrieve form data
    	String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        try (Connection conn = DBUtils.getConnection()) {
            // Insert request into the database
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, softwareId);
            stmt.setString(3, accessType);
            stmt.setString(4, reason);
            stmt.setString(5, "Pending");

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                // Fetch the software list again
                String fetchSoftwareSql = "SELECT id, name FROM software";
                PreparedStatement fetchStmt = conn.prepareStatement(fetchSoftwareSql);
                var resultSet = fetchStmt.executeQuery();

                List<String[]> softwareList = new ArrayList<>();
                while (resultSet.next()) {
                    softwareList.add(new String[] { resultSet.getString("id"), resultSet.getString("name") });
                }

                // Add softwareList to request scope
                request.setAttribute("softwareList", softwareList);

                // Forward to JSP instead of redirecting
                request.getRequestDispatcher("requestAccess.jsp?success=Request submitted successfully").forward(request, response);
            } else {
                response.sendRedirect("requestAccess.jsp?error=Error submitting request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=Error submitting request");
        }
    }
    }


