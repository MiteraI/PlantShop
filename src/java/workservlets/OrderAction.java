/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package workservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.OrderDetailDAOImpl;
import models.entities.Account;
import workconstants.OrderConstants;

/**
 *
 * @author Huynh Anh Kiet
 */
public class OrderAction extends HttpServlet {

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
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("loginedUser");
            OrderDetailDAOImpl getOrder = new OrderDetailDAOImpl();
            switch (account.getRole()) {
                case 0: //Has major flaws involving data breach, will have to redo OrderDetailDAOImpl
                    switch (action) {
                        case OrderConstants.CANCEL:
                            getOrder.updateUserOrder(action, id, account.getAccID());
                            break;
                        case OrderConstants.REORDER:
                            getOrder.updateUserOrder(action, id, account.getAccID());
                            break;
                        default:
                            out.print("You are doing sth here?");
                            System.out.println("Something fishy is going on");
                            break;
                    }
                    break;
                case 1:
                    switch (action) {
                        case OrderConstants.CANCEL:
                            break;
                        case OrderConstants.REORDER:
                            break;
                        case OrderConstants.DELETE:
                            break;
                        case OrderConstants.COMPLETE:
                            break;
                        default:
                            System.out.println("Something fishy is going on");
                            break;
                    }
            }
            request.getRequestDispatcher("ViewOrder").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex);
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
        processRequest(request, response);
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
