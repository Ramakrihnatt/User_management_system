package com.leucine.servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.leucine.util.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBUtils.getConnection()) {
            // Query to verify credentials and fetch role
            String sql = "SELECT id, role FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create a session and store user information
                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("username", username);
                session.setAttribute("role", rs.getString("role"));

                // Redirect based on user role
                switch (rs.getString("role")) {
                    case "Admin":
                        response.sendRedirect("createSoftware.jsp");
                        break;
                    case "Manager":
                        response.sendRedirect("PendingRequestsServlet");
                        break;
                    case "Employee":
                        response.sendRedirect("SoftwareListServlet");
                        break;

                    default:
                        response.sendRedirect("login.jsp?error=Invalid role");
                        break;
                }
            } else {
                // Invalid credentials
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }
        } catch (Exception e) {
            // Handle exceptions and redirect to login with error message
            response.sendRedirect("login.jsp?error=Login Failed");
            e.printStackTrace();
        }
    }
}
