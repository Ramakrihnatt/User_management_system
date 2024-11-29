package com.leucine.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.leucine.util.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  // Get the request ID and action (approve/reject) from the form submission
        String action = request.getParameter("action");
        String requestIdStr = request.getParameter("requestId");

        // If action or requestId is null, redirect back with an error
        if (action == null || requestIdStr == null) {
            response.sendRedirect("pendingRequests.jsp?error=Invalid request");
            return;
        }

        try {
            // Parse the request ID
            int requestId = Integer.parseInt(requestIdStr);

            // Based on the action, update the status of the request
            String status = "";
            if ("Approve".equals(action)) {
                status = "Approved";
            } else if ("Reject".equals(action)) {
                status = "Rejected";
            }

            // Update the request status in the database
            boolean success = DBUtils.updateRequestStatus(requestId, status);
            
            DBUtils.getConnection().commit();

            // Redirect to the pending requests page with a success message
            if (success) {
                response.sendRedirect("RemingPandding");
            } else {
                response.sendRedirect("pendingRequests.jsp?error=Failed to update request");
            }

        } catch (NumberFormatException e) {
            // Handle invalid request ID
            response.sendRedirect("pendingRequests.jsp?error=Invalid request ID");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
}

