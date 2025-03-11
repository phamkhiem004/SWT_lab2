<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rental Detail</title>
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
                <p><strong>Expected Pickup:</strong> ${rental.getFormattedPickupDate()}</p>
                <p><strong>Expected Return:</strong> ${rental.getFormattedReturnDate()}</p>
                <p><strong>Deposit Amount:</strong> <fmt:formatNumber value="${rental.depositAmount}" pattern="#,##0 đ" /></p>
                <p><strong>Total Amount:</strong> <fmt:formatNumber value="${rental.totalAmount}" pattern="#,##0 đ" /></p>
                <p><strong>Status:</strong>
                    <c:choose>
                        <c:when test="${rental.status == 0}">Available</c:when>
                        <c:when test="${rental.status == 1}">Suspended</c:when>
                        <c:otherwise>Unknown</c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>

        <h2>Vehicles in this Contract</h2>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Vehicle ID</th>
                    <th>License Plate</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Rental Price Per Day</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehicle" items="${vehicles}">
                    <tr>
                        <td>${vehicle.vehicleId}</td>
                        <td>${vehicle.licensePlate}</td>
                        <td>${vehicle.brand}</td>
                        <td>${vehicle.model}</td>
                        <td>${vehicle.year}</td>
                        <td><fmt:formatNumber value="${vehicle.rentalPricePerDay}" pattern="#,##0 đ" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${vehicle.status == 0}">Available</c:when>
                                <c:when test="${vehicle.status == 1}">Suspended</c:when>
                                <c:otherwise>Unknown</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="listRental" class="btn btn-primary">Back to Rental List</a>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>