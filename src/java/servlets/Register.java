/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.DAO.AccountDAOImpl;

/**
 *
 * @author Huynh Anh Kiet
 */
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("WEB-INF/views/RegisterView.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try ( PrintWriter out = response.getWriter()) {
            String name = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phoneNumber");
            AccountDAOImpl account = new AccountDAOImpl();
            if (account.createUser(email, name, password, phone)) {
                request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
            } else {
                request.setAttribute("registerStatus", false);
                request.getRequestDispatcher("WEB-INF/views/RegisterView.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
