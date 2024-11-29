package com.leucine.servlets;

import java.io.IOException;
import java.util.List;

import com.leucine.util.DBUtils;
import com.leucine.util.Request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemingPandding")
public class RemingPandding extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // doGet method to handle the GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Fetch pending requests from the database
        List<Request> pendingRequests = DBUtils.getPendingRequests();
        
        // Set the list of pending requests as an attribute to be used in the JSP
        request.setAttribute("pendingRequests", pendingRequests);

        // Forward to the JSP page
        request.getRequestDispatcher("pendingRequests.jsp").forward(request, response);
    }

    // Optional: doPost method to handle POST requests if needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);  // Forward POST requests to the doGet method
    }
}
