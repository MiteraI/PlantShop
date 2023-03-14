/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.view;

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
public class AdminViewOrder extends HttpServlet {

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
            Date date = dateFormat.parse("1999-01-01"); //Default date to include all orders
            ArrayList<OrderDetail> userFilteredOrderList = new ArrayList(); //User will be filterd first
            ArrayList<OrderDetail> pendingOrderList = new ArrayList(); //Pending order filtered list
            ArrayList<OrderDetail> completedOrderList = new ArrayList(); //Completed order filterd list
            ArrayList<OrderDetail> canceledOrderList = new ArrayList(); //Cancelled order filtered list
            if (request.getParameter("dateFilter") != null) { //Test if the filter parameter is sent
                if (!request.getParameter("dateFilter").isEmpty() || !request.getParameter("dateFilter").isBlank()) {
                    String dateString = request.getParameter("dateFilter");
                    date = dateFormat.parse(dateString);
                }
            }
            String userID = ""; //Default user id in String to know if parameter is sent
            int id = -1; //Default user id in Integer to not include anything in case of breach
            if (request.getParameter("userFilter") != null) { //If and only if user id is sent will id be changed into valid id to filter
                if (!request.getParameter("userFilter").isEmpty() || !request.getParameter("userFilter").isBlank()) {
                    userID = request.getParameter("userFilter");
                    id = Integer.parseInt(userID);
                }
            }
            HttpSession session = request.getSession();
            Account loginedUser = (Account) session.getAttribute("loginedUser");

            if (loginedUser != null) {
                OrderDetailDAOImpl getOrderDetail = new OrderDetailDAOImpl();
                ArrayList<OrderDetail> unfilteredOrderList = getOrderDetail.readAllAdmin();
                if (id != -1) { //When id for filter is valid then put certain order into userFiltered list
                    for (OrderDetail order : unfilteredOrderList) {
                        if (order.getuOrder().getAccID() == id) {
                            userFilteredOrderList.add(order);
                        }
                    }
                } else { //When id is not valid for filter then copy data of unfiltered list and proceed as noraml
                    userFilteredOrderList = unfilteredOrderList;
                }
                for (OrderDetail order : userFilteredOrderList) {
                    Date orderDate = dateFormat.parse(order.getuOrder().getOrderDate());
                    if (!orderDate.before(date)) { //After date
                        switch (order.getuOrder().getStatus()) {
                            case 1:
                                pendingOrderList.add(order);
                                break;
                            case 2:
                                completedOrderList.add(order);
                                break;
                            case 3:
                                canceledOrderList.add(order);
                                break;
                        }
                    }
                }
                //request.setAttribute("orderList", filteredOrderList);
                request.setAttribute("pendingOrderList", pendingOrderList); //List for pending orders
                request.setAttribute("completedOrderList", completedOrderList); //List for completed orders
                request.setAttribute("canceledOrderList", canceledOrderList); //List for canceled orders
                request.getRequestDispatcher("WEB-INF/views/admin/AdminViewOrder.jsp").forward(request, response);
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
