<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="rental.jsp">Vehicle Hiring System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="vehicles">Home</a>
            </li>
            <li class="nav-item active">
                <c:if test="${sessionScope.user.role == 0}">
                    <a class="nav-link" href="listRental">List Rental For Customer</a>
                </c:if>
                <c:if test="${sessionScope.user.role == 1}">
                    <a class="nav-link" href="employeeListRental">List Rental For Employee</a>
                </c:if>
                <c:if test="${sessionScope.user.role == 3}">
                    <a class="nav-link" href="employeeListRental">List Rental For Admin</a>
                </c:if>
            </li>
            <li class="nav-item active">
                <c:if test="${sessionScope.user.role == 3}">
                    <a class="nav-link" href="UserListServlet">User Management</a>
                </c:if>
            </li>
            <li class="nav-item active">
                <c:if test="${sessionScope.user.role == 2 || sessionScope.user.role == 3}">
                    <a class="nav-link" href="VehicleServlet">Vehicle Management</a>
                </c:if>
            </li>
        </ul>

        <!-- Right-aligned Login/Logout Button -->
        <ul class="navbar-nav ml-auto">
            <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a class="nav-link btn custom-login-btn px-3" href="login">Login</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <li class="nav-item">
                    <a class="nav-link btn custom-login-btn px-3" href="logout">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<!-- CSS for Custom Button Style -->
<style>
    .custom-login-btn {
        color: #ffffff !important;
        border: 1px solid #ffffff !important;
        font-weight: bold;
    }

    .custom-login-btn:hover {
        background-color: #ffffff !important;
        color: #000000 !important;
    }
</style>