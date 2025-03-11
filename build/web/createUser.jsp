<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create User</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container mt-5">
            <h2>Create User</h2>
            <form action="UserCreateServlet" method="post">
                <div class="form-group"> <label>Username</label> <input type="text" class="form-control" name="username" required> </div>
                <div class="form-group"> <label>Password</label> <input type="password" class="form-control" name="passwordHash" required> </div>
                <div class="form-group">
                    <label>Role</label> 
                    <select name="role" class="form-control">
                        <option value="0">Customer</option>
                        <option value="1">Employee</option>
                        <option value="2">Manager</option>
                        <option value="3">Admin</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Status</label> 
                    <select name="status" class="form-control">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>
                <div class="form-group"> <label>Full Name</label> <input type="text" class="form-control" name="fullName" required> </div>
                <div class="form-group"> <label>Email</label> <input type="email" class="form-control" name="email" required> </div>
                <button type="submit" class="btn btn-primary">Create User</button> 
            </form>
            <% if (request.getAttribute("error") != null) { %> 
            <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
            <% } %> 
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>