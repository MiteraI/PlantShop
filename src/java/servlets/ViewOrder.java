/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.OrderDetailDAOImpl;
import models.entities.Account;
import models.entities.OrderDetail;

/**
 *
 * @author Huynh Anh Kiet
 */
public class ViewOrder extends HttpServlet {

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = dateFormat.parse("1999-01-01");
            ArrayList<OrderDetail> filteredOrderList = new ArrayList();
            if (request.getParameter("dateFilter") != null) {
                String dateString = request.getParameter("dateFilter");
                date = dateFormat.parse(dateString);
            }
            HttpSession session = request.getSession();
            Account loginedUser = (Account) session.getAttribute("loginedUser");
            if (loginedUser != null) {
                OrderDetailDAOImpl getOrderDetail = new OrderDetailDAOImpl();
                ArrayList<OrderDetail> unfilteredOrderList = getOrderDetail.readAll(Integer.toString(loginedUser.getAccID()));
                for (OrderDetail order : unfilteredOrderList) {
                    Date orderDate = dateFormat.parse(order.getOrderDate());
                    if (!orderDate.before(date)) {
                        filteredOrderList.add(order);
                        System.out.println("One left");
                    }
                }
                request.setAttribute("orderList", filteredOrderList);
                request.getRequestDispatcher("WEB-INF/views/OrderView.jsp").forward(request, response);
            } else {
                request.setAttribute("loginedUser", false);
                request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
            }

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
