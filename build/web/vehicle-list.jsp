<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vehicle List</title>
        <!-- Link Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h2 class="mb-4">Vehicle List</h2>
            <a href="VehicleServlet?action=new" class="btn btn-success mb-3">Add New Vehicle</a>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Image</th>
                        <th>Type</th>
                        <th>License Plate</th>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Year</th>
                        <th>Rental Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehicle" items="${vehicles}">
                        <tr>
                            <td style="text-align: center">
                                <img src="${vehicle.imageUrl}" alt="${vehicle.model}" class="img-thumbnail" style="width:200px;height:200px;"/>
                            </td>
                            <td>${vehicle.vehicleTypeId}</td>
                            <td>${vehicle.licensePlate}</td>
                            <td>${vehicle.brand}</td>
                            <td>${vehicle.model}</td>
                            <td>${vehicle.year}</td>
                            <td><fmt:formatNumber value="${vehicle.rentalPricePerDay}" pattern="#,##0 đ" /></td>
                            <td>
                                <span class="badge ${vehicle.status == 0 ? 'badge-success' : 'badge-danger'}">
                                    ${vehicle.status == 1 ? 'Inactive' : 'Active'}
                                </span>
                            </td>
                            <td>
                                <a href="VehicleServlet?action=edit&vehicleId=${vehicle.vehicleId}" class="btn btn-primary btn-sm">Edit</a>
                                <a href="VehicleServlet?action=toggleStatus&vehicleId=${vehicle.vehicleId}" class="btn btn-warning btn-sm">
                                    ${vehicle.status == 1 ? 'Activate' : 'Deactivate'}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Link Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="footer.jsp"/>
    </body>
</html>