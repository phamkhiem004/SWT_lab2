<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <title>Select Vehicle Type</title>
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h1 class="text-center">Select Vehicle Type</h1>
            <form action="vehicles" method="post" class="mb-4">
                <div class="form-group">
                    <label for="vehicleTypeId">Vehicle Type:</label>
                    <select name="vehicleTypeId" id="vehicleTypeId" class="form-control" required>
                        <c:forEach var="type" items="${vehicleTypes}">
                            <option value="${type.vehicleTypeId}" ${vehicleTypeId == type.vehicleTypeId ? "selected":""}>${type.typeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="pickupDate">Expected Pickup Date:</label>
                    <input type="date" name="pickupDate" id="pickupDate" value="${pickupDate}" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="returnDate">Expected Return Date:</label>
                    <input type="date" name="returnDate" id="returnDate" value="${returnDate}" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Search Vehicles</button>
            </form>

            <c:if test="${not empty availableVehicles}">
                <form action="createRentalContracts" method="post" id="rentalForm">
                    <input type="hidden" name="pickupDate" value="${pickupDate}" />
                    <input type="hidden" name="returnDate" value="${returnDate}" />
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>Select</th> <!-- Checkbox Column -->
                                <th>Image</th>
                                <th>License Plate</th>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Year</th>
                                <th>Rental Price Per Day</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="vehicle" items="${availableVehicles}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="selectedVehicles" value="${vehicle.vehicleId}" />
                                    </td>
                                    <td style="text-align: center">
                                        <img src="${vehicle.imageUrl}" alt="${vehicle.model}" class="img-thumbnail" style="width:200px;height:200px;"/>
                                    </td>
                                    <td>${vehicle.licensePlate}</td>
                                    <td>${vehicle.brand}</td>
                                    <td>${vehicle.model}</td>
                                    <td>${vehicle.year}</td>
                                    <td><fmt:formatNumber value="${vehicle.rentalPricePerDay}" pattern="#,##0 đ" /></td>
                                    <td>${vehicle.status == 0 ? 'Available' : 'Not Available'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- Button to submit selected vehicles -->
                    <button type="submit" class="btn btn-success">Rent Selected Vehicles</button>
                </form>
            </c:if>


        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="footer.jsp"/>
    </body>
</html>