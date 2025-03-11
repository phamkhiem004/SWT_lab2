/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.RentalContractDAO;
import daos.VehicleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RentalContract;
import models.UserAccount;

/**
 *
 * @author SHD
 */
public class CreateRentalContracts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateRentalContracts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateRentalContracts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String[] selectedVehicleIds = request.getParameterValues("selectedVehicles");
            String pickupDate = request.getParameter("pickupDate");
            String returnDate = request.getParameter("returnDate");

            // Assuming userId is available from session or request
            UserAccount user = (UserAccount) request.getSession().getAttribute("user");
            if (user == null || user.getRole() != 0) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            RentalContractDAO rentalContractDAO = new RentalContractDAO();
            int totalAmount = 0; // You may calculate total amount based on selected vehicles
            RentalContract contract = new RentalContract();
            contract.setUserId(user.getUserId());
            contract.setExpectedPickupDateTime(Timestamp.valueOf(pickupDate + " 00:00:00"));
            contract.setExpectedReturnDateTime(Timestamp.valueOf(returnDate + " 00:00:00"));
            contract.setDepositAmount(0); // Set deposit amount as per your business logic
            contract.setStatus(0); // Status 0 - Chờ duyệt
            long dayHire = contract.getDifferenceInDays();

            int contractId = rentalContractDAO.createRentalContract(contract);

            // Loop through selected vehicles and create rental contracts
            if (selectedVehicleIds != null) {
                for (String vehicleId : selectedVehicleIds) {

                    // Create RentalContract
                    totalAmount += new VehicleDAO().getVehicle(vehicleId).getRentalPricePerDay() * (double) dayHire;

                    // Save each vehicle to contract detail
                    rentalContractDAO.addVehicleToContract(contractId, Integer.parseInt(vehicleId));
                }
            }
            new RentalContractDAO().updateTotalMoney(totalAmount, contractId);

            // Redirect to confirmation page or display success message
            response.sendRedirect("listRental");
        } catch (SQLException ex) {
            Logger.getLogger(CreateRentalContracts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
