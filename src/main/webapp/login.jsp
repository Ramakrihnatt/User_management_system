<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <!-- Display error messages -->
        <% String errorMessage = request.getParameter("error"); %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message">
                <p><%= errorMessage %></p>
            </div>
        <% } %>
        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <!-- Redirect to signup.jsp -->
        <a href="signup.jsp" class="signup-link">Don't have an account? Sign up here</a>
    </div>
</body>
</html>
