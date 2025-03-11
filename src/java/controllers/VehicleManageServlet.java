/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.VehicleDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserAccount;
import models.Vehicle;
import models.VehicleType;

/**
 *
 * @author SHD
 */
public class VehicleManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null || (user.getRole() != 2 && user.getRole() != 3)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        try {
            String action = request.getParameter("action");
            if ("new".equals(action)) {
                List<VehicleType> vehicleTypes = new VehicleDAO().getVehicleTypes();
                request.setAttribute("vehicleTypes", vehicleTypes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-form.jsp");
                dispatcher.forward(request, response);
            } else if ("edit".equals(action)) {
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                Vehicle vehicle;

                vehicle = new VehicleDAO().getVehicle(request.getParameter("vehicleId")); // Implement phương thức này để lấy xe theo ID

                List<VehicleType> vehicleTypes = new VehicleDAO().getVehicleTypes();
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("vehicleTypes", vehicleTypes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-form.jsp");
                dispatcher.forward(request, response);
            } else if ("toggleStatus".equals(action)) {
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                new VehicleDAO().toggleVehicleStatus(vehicleId);
                response.sendRedirect("VehicleServlet");
            } else {
                // Default: list all vehicles
                List<Vehicle> vehicles = new VehicleDAO().getAllVehicles(); // Implement phương thức này để lấy danh sách xe
                request.setAttribute("vehicles", vehicles);
                RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-list.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehicleManageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null || (user.getRole() != 2 && user.getRole() != 3)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        try {
            String action = request.getParameter("action");

            if ("create".equals(action)) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleTypeId(Integer.parseInt(request.getParameter("vehicleTypeId")));
                vehicle.setLicensePlate(request.getParameter("licensePlate"));
                vehicle.setBrand(request.getParameter("brand"));
                vehicle.setModel(request.getParameter("model"));
                vehicle.setYear(Integer.parseInt(request.getParameter("year")));
                vehicle.setRentalPricePerDay(Double.parseDouble(request.getParameter("rentalPricePerDay")));
                vehicle.setImageUrl(request.getParameter("imageUrl"));
                new VehicleDAO().createVehicle(vehicle);
                response.sendRedirect("VehicleServlet");
            } else if ("update".equals(action)) {
                int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
                Vehicle vehicle = new VehicleDAO().getVehicle(request.getParameter("vehicleId")); // Implement phương thức này để lấy xe theo ID
                vehicle.setVehicleTypeId(Integer.parseInt(request.getParameter("vehicleTypeId")));
                vehicle.setLicensePlate(request.getParameter("licensePlate"));
                vehicle.setBrand(request.getParameter("brand"));
                vehicle.setModel(request.getParameter("model"));
                vehicle.setYear(Integer.parseInt(request.getParameter("year")));
                vehicle.setRentalPricePerDay(Double.parseDouble(request.getParameter("rentalPricePerDay")));
                vehicle.setImageUrl(request.getParameter("imageUrl"));
                new VehicleDAO().updateVehicle(vehicle);
                response.sendRedirect("VehicleServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehicleManageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
