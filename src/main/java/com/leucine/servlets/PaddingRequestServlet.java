package com.leucine.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.leucine.util.DBUtils;
import com.leucine.util.Request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PendingRequestsServlet")

public class PaddingRequestServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        
        // List to hold the requests
        List<Request> pendingRequests = new ArrayList<>();

        // SQL query to get pending requests
        String sql = """
                SELECT r.id AS request_id,
                       u.username AS USERNAME,
                       s.name AS software_name,
                       r.access_type,
                       r.reason
                FROM requests r
                JOIN users u ON r.user_id = u.id
                JOIN software s ON r.software_id = s.id
                WHERE r.status = 'Pending'
            """;

        try (
             Statement stmt = DBUtils.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Process the result set and populate the list
            while (rs.next()) {
                Request req = new Request();
                req.setRequestId(rs.getInt("request_id"));
                req.setEmployeeName(rs.getString("USERNAME"));
                req.setSoftwareName(rs.getString("software_name"));
                req.setAccessType(rs.getString("access_type"));
                req.setReason(rs.getString("reason"));
                pendingRequests.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions appropriately
        }

        // Set the list of requests as an attribute to be used in the JSP
        request.setAttribute("pendingRequests", pendingRequests);

        // Forward to the JSP page
        request.getRequestDispatcher("pendingRequests.jsp").forward(request, response);
    }
}
