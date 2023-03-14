/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package workservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.OrderDetailDAOImpl;
import models.DAO.PlantDAOImpl;
import models.entities.Account;
import models.entities.Plant;

/**
 *
 * @author Huynh Anh Kiet
 */
public class Purchase extends HttpServlet {

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
            HttpSession session = request.getSession();
            HashMap<Plant, Integer> cart = new HashMap();
            OrderDetailDAOImpl getOrderDetail = new OrderDetailDAOImpl();
            PlantDAOImpl getPlant = new PlantDAOImpl();
            if (session.getAttribute("loginedUser") != null) {
                Account loginedUser = (Account) session.getAttribute("loginedUser");
                if (loginedUser == null) { //Have to login before 
                    request.setAttribute("loginedUser", false);
                    request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
                }
                HashMap<String, Integer> sessionCart = (HashMap) session.getAttribute("cart");
                if (sessionCart != null && !sessionCart.isEmpty()) {
                    for (String id : sessionCart.keySet()) {
                        cart.put(getPlant.read(id), sessionCart.get(id));
                    }
                    for (Plant plant : cart.keySet()) {
                        if (getOrderDetail.create(Integer.toString(loginedUser.getAccID()), Integer.toString(plant.getId()), Integer.toString(cart.get(plant)))) {
                        } 
                    }
                    session.removeAttribute("cart");
                    request.getRequestDispatcher("").forward(request, response);
                } else {
                    out.print("There is nothing in the cart");
                }
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
