<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit User</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container mt-5">
            <h2>Edit User</h2>
            <form action="UserUpdateServlet" method="post">
                <input type="hidden" name="userId" value="${user.userId}"> 
                <div class="form-group"> <label>Full Name</label> <input type="text" class="form-control" name="fullName" value="${user.fullName}" required> </div>
                <div class="form-group"> <label>Role</label> <select name="role" class="form-control"> <option value="0" ${user.role == 0 ? "selected" : ""}>Customer</option> <option value="1" ${user.role == 1 ? "selected" : ""}>Employee</option> <option value="2" ${user.role == 2 ? "selected" : ""}>Manager</option> <option value="3" ${user.role == 3 ? "selected" : ""}>Admin</option> </select> </div>
                <div class="form-group"> <label>Status</label> <select name="status" class="form-control"> <option value="1" ${user.status == 1 ? "selected" : ""}>Active</option> <option value="0" ${user.status == 0 ? "selected" : ""}>Inactive</option> </select> </div>
                <div class="form-group"> <label>Email</label> <input type="email" class="form-control" name="email" value="${user.email}" required> </div>
                <button type="submit" class="btn btn-primary">Update User</button> 
            </form>
            <% if (request.getAttribute("error") != null) { %> 
            <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
            <% } %> 
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>