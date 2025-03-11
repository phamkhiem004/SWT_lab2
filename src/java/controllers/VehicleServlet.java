/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.VehicleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Vehicle;
import models.VehicleType;

/**
 *
 * @author SHD
 */
public class VehicleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<VehicleType> vehicleTypes = new VehicleDAO().getAllVehicleTypes();
            request.setAttribute("vehicleTypes", vehicleTypes);
            request.getRequestDispatcher("rental.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
        LocalDate pickupLocalDate = LocalDate.parse(request.getParameter("pickupDate"));
        LocalDate returnLocalDate = LocalDate.parse(request.getParameter("returnDate"));

        Date pickupDate = Date.from(pickupLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date returnDate = Date.from(returnLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        request.setAttribute("vehicleTypeId", vehicleTypeId);
        request.setAttribute("pickupDate", request.getParameter("pickupDate"));
        request.setAttribute("returnDate", request.getParameter("returnDate"));
        

        try {
            List<Vehicle> availableVehicles = new VehicleDAO().getAvailableVehicles(vehicleTypeId, pickupDate, returnDate);
            request.setAttribute("availableVehicles", availableVehicles);
            List<VehicleType> vehicleTypes = new VehicleDAO().getAllVehicleTypes();
            request.setAttribute("vehicleTypes", vehicleTypes);
            request.getRequestDispatcher("rental.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

}
