package com.leucine.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.leucine.util.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class SoftwareServlet
 */
@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String softwareName = request.getParameter("softwareName");
        String description = request.getParameter("description");
        String[] accessLevels = request.getParameterValues("accessLevels");

        // Get the userId from the session
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Ensure the user is an Admin
        if (role == null || !role.equals("Admin")) {
            response.sendRedirect("login.jsp?error=Unauthorized access");
            return;
        }

        // Prepare the access levels string
        StringBuilder accessLevelsStr = new StringBuilder();
        if (accessLevels != null) {
            for (String level : accessLevels) {
                accessLevelsStr.append(level).append(", ");
            }
            accessLevelsStr.delete(accessLevelsStr.length() - 2, accessLevelsStr.length());  // Remove the trailing comma
        }

        // Insert the software into the database
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, softwareName);
            stmt.setString(2, description);
            stmt.setString(3, accessLevelsStr.toString());

            int rowsInserted = stmt.executeUpdate();
            conn.commit();
            if (rowsInserted > 0) {
                response.sendRedirect("createSoftware.jsp");
            } else {
                response.sendRedirect("createSoftware.jsp?error=Failed to create software");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("createSoftware.jsp?error=Internal server error");
        }
    
	}

}
