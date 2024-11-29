<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.leucine.util.*" %>
<%
    // Ensure the user is an Admin
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("Admin")) {
        response.sendRedirect("login.jsp?error=You are not authorized to access this page");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Software</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Create Software</h2>
        <form method="post" action="SoftwareServlet">
            <div class="mb-3">
                <label for="softwareName" class="form-label">Software Name</label>
                <input type="text" class="form-control" id="softwareName" name="softwareName" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="accessLevels" class="form-label">Access Levels</label><br>
                <input type="checkbox" id="read" name="accessLevels" value="Read"> Read<br>
                <input type="checkbox" id="write" name="accessLevels" value="Write"> Write<br>
                <input type="checkbox" id="admin" name="accessLevels" value="Admin"> Admin<br>
            </div>
            <button type="submit" class="btn btn-primary">Create Software</button>
        </form>
    </div>
</body>
</html>
