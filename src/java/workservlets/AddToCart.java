/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package workservlets;

import workconstants.ControllerConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.PlantDAOImpl;
import models.entities.Account;
import models.entities.Plant;

/**
 *
 * @author Huynh Anh Kiet
 */
public class AddToCart extends HttpServlet {

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
            Account loginedUser = (Account) session.getAttribute("loginedUser");
            if (loginedUser != null) {
                String id = request.getParameter("id");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                PlantDAOImpl getPlant = new PlantDAOImpl();
                Plant plant = getPlant.read(id);
                HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart"); //plant : quantity
                if (cart == null) { //This only run once to determine if a cart is in used
                    cart = new HashMap<String, Integer>();
                }
                if (cart.containsKey(id)) {  //Similar id found meaning a same plant is selected before
                    int pastQuantity = cart.get(id);  // key is plant so we get value of key is quantity
                    int updatedQuantity = quantity + pastQuantity;
                    cart.put(id, updatedQuantity); // update the new value of key
                } else {
                    cart.put(id, quantity);  //No similar id found so add plant 
                }
                session.setAttribute("cart", cart);
                request.getRequestDispatcher("MainController?action="+ControllerConstants.VIEW+"&id="+id).forward(request, response);
            } else {
                request.setAttribute("loginedUser", false);
                request.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(request, response);
            }
//            String id = request.getParameter("id");
//            PlantDAOImpl getPlant = new PlantDAOImpl();
//            Plant plant = getPlant.read(id);
//            request.setAttribute("plant", plant);
//            request.getRequestDispatcher("WEB-INF/views/PlantView.jsp").forward(request, response);
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
