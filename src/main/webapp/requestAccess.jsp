<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, com.leucine.util.*" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"Employee".equals(role)) {
        response.sendRedirect("login.jsp?error=Unauthorized access");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Access</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Request Access</h2>
        <form action="RequestServlet" method="post">
            <!-- Software Name Selection -->
            <div>
                <label class="form-label" for="softwareId">Software Name:</label>
                <select id="softwareId" name="softwareId" class="form-control" required>
                    <%
                        List<String[]> softwareList = (List<String[]>) request.getAttribute("softwareList");
                        if (softwareList != null) {
                            for (String[] software : softwareList) {
                    %>
                        <option value="<%= software[0] %>"><%= software[1] %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <!-- Access Type Selection -->
            <div>
                <label class="form-label" for="accessType">Access Type:</label>
                <select id="accessType" name="accessType" class="form-control" required>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <!-- Reason Textarea -->
            <div>
                <label class="form-label" for="reason">Reason:</label>
                <textarea id="reason" name="reason" class="form-control" rows="5" required></textarea>
            </div>
            <!-- Submit Button -->
            <button type="submit" class="btn">Submit</button>
        </form>
    </div>
</body>
</html>
