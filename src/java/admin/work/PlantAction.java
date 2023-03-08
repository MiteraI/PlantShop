/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.work;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.DAO.PlantDAOImpl;
import workconstants.CategoryConstants;

/**
 *
 * @author Huynh Anh Kiet
 */
public class PlantAction extends HttpServlet {

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
        try {
            String action = request.getParameter("action");
            PlantDAOImpl getPlant = new PlantDAOImpl();
            String id = request.getParameter("id"); //In change and delete, it is plant's id
            //In create, it is category's id
            switch (action) {
                case CategoryConstants.CREATE:
                    if (!id.isBlank() || !id.isEmpty()) {
                        String PName = request.getParameter("PName");
                        String price = request.getParameter("price");
                        String description = request.getParameter("description");
                        if (!PName.isEmpty() || !PName.isBlank() || !price.isEmpty() || !price.isBlank() || !description.isEmpty() || !description.isBlank()) {
                            getPlant.create(PName, price, description, id);
                        }
                    }
                    break;
                case CategoryConstants.CHANGE:
                    if (!id.isBlank() || !id.isEmpty()) {
                        String status = request.getParameter("status");
                        if (!status.isBlank() || !status.isEmpty()) {
                            getPlant.statusChange(id, status);
                        }
                    }
                    break;
                case CategoryConstants.DELETE:
                    if (!id.isBlank() || !id.isEmpty()) {
                        getPlant.delete(id);
                    }
                    break;
                default:
                    request.getRequestDispatcher("AdminPageView").forward(request, response);
            }

            request.getRequestDispatcher("AdminViewPlant").forward(request, response);
        } catch (Exception ex) {
            // log the error
            System.out.println(ex);
            // redirect to the error page
            request.setAttribute("error", ex);
            request.getRequestDispatcher("WEB-INF/views/error.jsp").forward(request, response);
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
