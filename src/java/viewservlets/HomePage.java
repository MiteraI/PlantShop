/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package viewservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DAO.AccountDAOImpl;
import models.DAO.PlantDAOImpl;
import models.entities.Account;
import models.entities.Plant;

/**
 *
 * @author Huynh Anh Kiet
 */
public class HomePage extends HttpServlet {

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
            PlantDAOImpl getPlant = new PlantDAOImpl();
            AccountDAOImpl getAccount = new AccountDAOImpl();
            ArrayList<Plant> plantList = getPlant.readAll();
            //Cookie asking montage
            Cookie[] biscuit = request.getCookies();
            String token = "";
            if (biscuit != null) {
                for (Cookie aBiscuit : biscuit) {
                    if (aBiscuit.getName().equals("PlantShopToken")) {
                        token = aBiscuit.getValue();
                    }
                }
            }
            if (!token.equals("")) { //Meaning it found a cookie in the browser memory with name "PlantShopToken" which we will tell it to save
                Account cookiedUser = getAccount.readCookie(token);
                HttpSession session = request.getSession();
                session.setAttribute("loginedUser", cookiedUser);
            }
            request.setAttribute("list", plantList);
            request.getRequestDispatcher("WEB-INF/views/Homepage.jsp").forward(request, response);
            request.removeAttribute("list");
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
