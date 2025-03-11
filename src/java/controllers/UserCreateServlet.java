/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import daos.UserAccountDAO;
import models.UserAccount;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserCreateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserAccount userLogin = (UserAccount) request.getSession().getAttribute("user");
            if (userLogin == null || userLogin.getRole() != 3) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            UserAccount user = new UserAccount();
            user.setUsername(request.getParameter("username"));

            user.setPasswordHash(request.getParameter("passwordHash"));
            user.setRole(Integer.parseInt(request.getParameter("role")));
            user.setStatus(Integer.parseInt(request.getParameter("status")));
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            UserAccountDAO dao = new UserAccountDAO();

            if (dao.getUserByUsername(request.getParameter("username")) != null) {
                request.setAttribute("error", "Username exist");
                request.getRequestDispatcher("createUser.jsp").forward(request, response);
            }
            if (dao.createUser(user)) {
                response.sendRedirect("UserListServlet");
            } else {
                request.setAttribute("error", "User creation failed.");
                request.getRequestDispatcher("createUser.jsp").forward(request, response);
            }
        } catch (SQLException e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount userLogin = (UserAccount) request.getSession().getAttribute("user");
        if (userLogin == null || userLogin.getRole() != 3) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("createUser.jsp").forward(request, response);

    }
}
