<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- webapps/signup.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Sign Up</h2>
        <form action="SignUpServlet" method="get">
            <!-- Username Field -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            
            <!-- Password Field -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            
            <!-- Hidden Role Field (Defaulted to "Employee") -->
            <input type="hidden" name="role" value="Employee">
            
            <button type="submit" class="btn">Sign Up</button>
        </form>
        <!-- Link to the login page -->
        <a href="login.jsp" class="signup-link">Already have an account? Login here</a>

    </div>
</body>
</html>
