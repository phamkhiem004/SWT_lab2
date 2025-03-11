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
import java.sql.SQLException;
import java.util.List;
import models.UserAccount;

/**
 *
 * @author minhs
 */
public class UserListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 3) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        UserAccountDAO dao = new UserAccountDAO();
        try {
            List<UserAccount> users = dao.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("listUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
