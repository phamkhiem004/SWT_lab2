<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List of Users</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h2>List of Users</h2>
             <a href="UserCreateServlet" class="btn btn-success mb-3">Add New</a>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.username}</td>
                            <td>${user.fullName}</td>
                            <td>${user.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role == 0}">Customer</c:when>
                                    <c:when test="${user.role == 1}">Employee</c:when>
                                    <c:when test="${user.role == 2}">Manager</c:when>
                                    <c:when test="${user.role == 3}">Admin</c:when>
                                </c:choose>
                            </td>
                            <td>${user.status == 1 ? "Active" : "Inactive"}</td>
                            <td> <a href="UserUpdateServlet?id=${user.userId}" class="btn btn-warning btn-sm">Edit</a> <a href="UserStatusToggleServlet?userId=${user.userId}" class="btn btn-danger btn-sm">Change Status</a> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>