package com.leucine.servlets;

import java.io.IOException;
import java.util.List;

import com.leucine.util.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SoftwareListServlet
 */
@WebServlet("/SoftwareListServlet")
public class SoftwareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Get software list from DBUtils
	        List<String[]> softwareList = DBUtils.getSoftwareList();

	        // Set the software list as a request attribute
	        request.setAttribute("softwareList", softwareList);

	        // Forward to requestAccess.jsp
	        request.getRequestDispatcher("requestAccess.jsp").forward(request, response);
	    }

}
