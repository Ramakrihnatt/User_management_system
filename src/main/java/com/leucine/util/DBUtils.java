package com.leucine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "ram506";

    // Static block to load the driver
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Oracle JDBC Driver: " + e.getMessage(), e);
        }
    }

    // Method to get a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public static List<String[]> getSoftwareList() {
        List<String[]> softwareList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT id, name FROM software";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                softwareList.add(new String[] { rs.getString("id"), rs.getString("name") });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return softwareList;
    }
	public static void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static List<Request> getPendingRequests() {
	    List<Request> requests = new ArrayList<>();
	    String sql = "SELECT r.id AS request_id, u.username AS employee_name, s.name AS software_name, r.access_type, r.reason "
	                 + "FROM requests r "
	                 + "JOIN users u ON r.user_id = u.id "  // Assuming the employee info is in 'users' table
	                 + "JOIN software s ON r.software_id = s.id "
	                 + "WHERE r.status = 'Pending'";

	    try (Connection conn = DBUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Request request = new Request();
	            request.setRequestId(rs.getInt("request_id"));
	            request.setEmployeeName(rs.getString("employee_name"));
	            request.setSoftwareName(rs.getString("software_name"));	
	            request.setAccessType(rs.getString("access_type"));
	            request.setReason(rs.getString("reason"));
	            requests.add(request);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return requests;
	}

	public static boolean updateRequestStatus(int requestId, String status) {
	    String sql = "UPDATE requests SET status = ? WHERE id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, status);
	        stmt.setInt(2, requestId);

	        int rowsAffected = stmt.executeUpdate();
	        conn.commit();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	 
	 
}
