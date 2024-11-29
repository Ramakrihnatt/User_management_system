<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, com.leucine.util.*" %>
<%@ page session="true" %>

<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"Manager".equals(role)) {
        response.sendRedirect("login.jsp?error=Unauthorized access");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Requests</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #007bff;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .actions button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 4px;
            cursor: pointer;
            margin: 0 5px;
        }

        .actions button.reject {
            background-color: #f44336;
        }

        .actions button:hover {
            opacity: 0.9;
        }

        .no-data {
            text-align: center;
            font-size: 1.2em;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Pending Access Requests</h2>
        <table>
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Request> pendingRequests = (List<Request>) request.getAttribute("pendingRequests");

                    if (pendingRequests != null && !pendingRequests.isEmpty()) {
                        for (Request req : pendingRequests) {
                %>
                <tr>
                    <td><%= req.getRequestId() %></td>
                    <td><%= req.getEmployeeName() %></td>
                    <td><%= req.getSoftwareName() %></td>
                    <td><%= req.getAccessType() %></td>
                    <td><%= req.getReason() %></td>
                    <td class="actions">
                        <form action="ApprovalServlet" method="post" style="display: inline;">
                            <input type="hidden" name="requestId" value="<%= req.getRequestId() %>" />
                            <button type="submit" name="action" value="Approve">Approve</button>
                            <button type="submit" name="action" value="Reject" class="reject">Reject</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="6" class="no-data">No pending requests available.</td>
                </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
