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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import models.UserAccount;

/**
 *
 * @author SHD
 */
public class RegisterServlet extends HttpServlet {
   
    private UserAccountDAO userAccountDAO = new UserAccountDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        int role = 0; // 0 - Khách hàng
        int status = 1;


        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPasswordHash(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        boolean isRegistered = userAccountDAO.registerUser(user);

        if (isRegistered) {
            resp.sendRedirect("login");
        } else {
            req.setAttribute("errorMessage", "Registration failed, please try again.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
