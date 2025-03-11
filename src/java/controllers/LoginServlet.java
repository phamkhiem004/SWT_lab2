/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.UserAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import models.UserAccount;

/**
 *
 * @author SHD
 */
public class LoginServlet extends HttpServlet {

    private UserAccountDAO userAccountDAO = new UserAccountDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserAccount user = userAccountDAO.loginUser(username, password);
        if (user != null) {
            if (user.getStatus() == 0) {
                req.setAttribute("errorMessage", "Account has been banned");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if (user.getRole() == 0) {
                resp.sendRedirect("vehicles");
            } else if (user.getRole() == 1) {
                resp.sendRedirect("employeeListRental");
            } else if (user.getRole() == 2) {
                resp.sendRedirect("VehicleServlet");
            }else{
                resp.sendRedirect("UserListServlet");
            }

        } else {
            req.setAttribute("errorMessage", "Invalid username or password. Please try again.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

}
