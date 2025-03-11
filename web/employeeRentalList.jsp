<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Employee Rental List</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h1>Rental Contracts for Employee</h1>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Contract ID</th>
                        <th>Customer ID</th>
                        <th>Expected Pickup</th>
                        <th>Expected Return</th>
                        <th>Deposit Amount</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="rental" items="${rentals}">
                        <tr>
                            <td>${rental.contractId}</td>
                            <td>${rental.userId}</td>
                            <td>${rental.getFormattedPickupDate()}</td>
                            <td>${rental.getFormattedReturnDate()}</td>
                            <td><fmt:formatNumber value="${rental.depositAmount}" pattern="#,##0 đ" /></td>
                    <td><fmt:formatNumber value="${rental.totalAmount}" pattern="#,##0 đ" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${rental.status == 0}">Pending</c:when>
                            <c:when test="${rental.status == 1}">Deposited</c:when>
                            <c:when test="${rental.status == 2}">Rented</c:when>
                            <c:when test="${rental.status == 3}">Canceled</c:when>
                            <c:when test="${rental.status == 4}">Paid & Returned</c:when>
                            <c:otherwise>Unknown</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="employeeListRental?contractId=${rental.contractId}&action=detail" class="btn btn-info">View & Update</a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="footer.jsp"/>
    </body>
</html>