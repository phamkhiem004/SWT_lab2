/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.UserAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import models.UserAccount;

/**
 *
 * @author minhs
 */
public class UserUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount userLogin = (UserAccount) request.getSession().getAttribute("user");
        if (userLogin == null || userLogin.getRole() != 3) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        UserAccount user = new UserAccount();
        user.setUserId(Integer.parseInt(request.getParameter("userId")));
        user.setFullName(request.getParameter("fullName"));
        user.setRole(Integer.parseInt(request.getParameter("role")));
        user.setStatus(Integer.parseInt(request.getParameter("status")));
        user.setEmail(request.getParameter("email"));
        UserAccountDAO dao = new UserAccountDAO();
        try {
            if (dao.updateUser(user)) {
                response.sendRedirect("UserListServlet");
            } else {
                request.setAttribute("error", "User update failed.");
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            response.sendRedirect("error.jsp");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount userLogin = (UserAccount) request.getSession().getAttribute("user");
        if (userLogin == null || userLogin.getRole() != 3) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        int userId = Integer.parseInt(request.getParameter("id"));
        UserAccountDAO dao = new UserAccountDAO();
        try {
            UserAccount user = dao.getUserById(userId); // Assume getUserById method exists in DAO
            request.setAttribute("user", user);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
