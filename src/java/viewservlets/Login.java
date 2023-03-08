/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package viewservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.AccountDAOImpl;
import models.entities.Account;
import workconstants.AccountConstants;

/**
 *
 * @author Huynh Anh Kiet
 */
public class Login extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            request.setAttribute("loginedUser", true);
            request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            AccountDAOImpl account = new AccountDAOImpl();
            Account loginedUser = account.read(email, password);
            if (loginedUser != null) { //Checking if such account exist
                if (loginedUser.getStatus() == AccountConstants.ACTIVE) { //Checking for account status active or blocked
                    if (loginedUser.getRole() == AccountConstants.USER) { //Active so check for account's role
                        //If account is user
                        session.setAttribute("loginedUser", loginedUser);
                        request.getRequestDispatcher("").forward(request, response);
                    } else {
                        //If account is admin
                        session.setAttribute("loginedUser", loginedUser);
                        request.getRequestDispatcher("WEB-INF/views/admin/AdminView.jsp").forward(request, response);
                    }
                } else {
                    //If account is blocked
                    if (loginedUser.getRole() == AccountConstants.ADMIN) { 
                        //If account is admin grant access regardless
                        session.setAttribute("loginedUser", loginedUser);
                        request.getRequestDispatcher("WEB-INF/views/admin/AdminView.jsp").forward(request, response);
                    } else {
                        //If account is blocked and is user
                        request.setAttribute("isBlocked", true);
                        request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
                        session.invalidate();
                    }
                }
            } else {
                //Account don't exist
                request.setAttribute("loginStatus", false);
                request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher("WEB-INF/views/error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
