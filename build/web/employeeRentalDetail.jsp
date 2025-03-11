<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rental Detail for Employee</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h1>Rental Contract Detail</h1>
            <div class="card mb-4">
                <div class="card-body">
                    <p><strong>Contract ID:</strong> ${rental.contractId}</p>
                    <p><strong>Customer ID:</strong> ${rental.userId}</p>
                    <p><strong>Expected Pickup:</strong> ${rental.getFormattedPickupDate()}</p>
                    <p><strong>Expected Return:</strong> ${rental.getFormattedReturnDate()}</p>
                    <p><strong>Deposit Amount:</strong> <fmt:formatNumber value="${rental.depositAmount}" pattern="#,##0 đ" /></p>
                    <p><strong>Total Amount:</strong> <fmt:formatNumber value="${rental.totalAmount}" pattern="#,##0 đ" /></p>
                    <p><strong>Status:</strong> 
                        <c:choose>
                            <c:when test="${rental.status == 0}">Pending</c:when>
                            <c:when test="${rental.status == 1}">Deposited</c:when>
                            <c:when test="${rental.status == 2}">Rented</c:when>
                            <c:when test="${rental.status == 3}">Canceled</c:when>
                            <c:when test="${rental.status == 4}">Paid & Returned</c:when>
                        </c:choose>
                    </p>
                    <form action="UpdateRentalStatusServlet" method="post">
                        <input type="hidden" name="contractId" value="${rental.contractId}" />
                        <input type="hidden" name="currentStatus" value="${rental.status}" />
                        <c:choose>
                            <c:when test="${rental.status == 0}">
                                <button type="submit" name="newStatus" value="1" class="btn btn-success">Approve Deposit</button>
                                <button type="submit" name="newStatus" value="3" class="btn btn-danger">Cancel</button>
                            </c:when>
                            <c:when test="${rental.status == 1}">
                                <button type="submit" name="newStatus" value="2" class="btn btn-success">Rent Out</button>
                            </c:when>
                            <c:when test="${rental.status == 2}">
                                <button type="submit" name="newStatus" value="4" class="btn btn-success">Complete & Return</button>
                            </c:when>
                        </c:choose>
                    </form>
                </div>
            </div>
            <a href="employeeListRental" class="btn btn-primary">Back to Rental List</a>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="footer.jsp"/>
    </body>
</html>