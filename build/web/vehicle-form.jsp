<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vehicle Form</title>
        <!-- Link Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div class="container-fluid mt-5">
            <h2 class="mb-4">${vehicle == null ? "Add New Vehicle" : "Edit Vehicle"}</h2>
            <form action="VehicleServlet" method="post">
                <input type="hidden" name="action" value="${vehicle == null ? 'create' : 'update'}"/>
                <c:if test="${vehicle != null}">
                    <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}"/>
                </c:if>

                <div class="form-group">
                    <label>Vehicle Type:</label>
                    <select class="form-control" name="vehicleTypeId" required>
                        <c:forEach var="type" items="${vehicleTypes}">
                            <option value="${type.vehicleTypeId}" 
                                    ${vehicle != null && vehicle.vehicleTypeId == type.vehicleTypeId ? 'selected' : ''}>${type.typeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>License Plate:</label>
                    <input type="text" class="form-control" name="licensePlate" value="${vehicle != null ? vehicle.licensePlate : ''}" required/>
                </div>

                <div class="form-group">
                    <label>Brand:</label>
                    <input type="text" class="form-control" name="brand" value="${vehicle != null ? vehicle.brand : ''}" required/>
                </div>

                <div class="form-group">
                    <label>Model:</label>
                    <input type="text" class="form-control" name="model" value="${vehicle != null ? vehicle.model : ''}" required/>
                </div>

                <div class="form-group">
                    <label>Year:</label>
                    <input type="number" class="form-control" name="year" value="${vehicle != null ? vehicle.year : ''}" min="0" required/>
                </div>

                <div class="form-group">
                    <label>Rental Price Per Day:</label>
                    <input type="number" step="0.01" class="form-control" name="rentalPricePerDay" value="${vehicle != null ? vehicle.rentalPricePerDay : ''}" min="0" required/>
                </div>

                <div class="form-group">
                    <label>Image URL:</label>
                    <input type="text" class="form-control" name="imageUrl" value="${vehicle != null ? vehicle.imageUrl : ''}"/>
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
                <a href="VehicleServlet" class="btn btn-secondary">Back</a>
            </form>
        </div>
        <!-- Link Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <jsp:include page="footer.jsp"/>
    </body>
</html>