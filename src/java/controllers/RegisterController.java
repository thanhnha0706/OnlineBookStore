/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.SecurityService;

/**
 *
 * @author quyqu
 */
public class RegisterController extends HttpServlet {

    private CustomerDAO dao;
    private SecurityService ser;

    public RegisterController() {
        dao = new CustomerDAO(Customer.class);
        ser = new SecurityService();
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

        try {
            String inputFirstName = request.getParameter("inputFirstName");
            String inputLastName = request.getParameter("inputLastName");
            String inputEmail = request.getParameter("inputEmail");
            String inputPassword = request.getParameter("inputPassword");
            String inputAddress = request.getParameter("inputAddress");
            String inputPhone = request.getParameter("inputPhone");

            Customer cus = new Customer();
            cus.setCustomerId(java.util.UUID.randomUUID().toString());
            cus.setFirstName(inputFirstName);
            cus.setLastName(inputLastName);
            cus.setPhone(inputPhone);
            cus.setAddress(inputAddress);
            cus.setEmail(inputEmail);
            cus.setPassword(ser.hashPassword(inputPassword));
            
            Customer registered = dao.insert(cus);
            
            if (registered != null ){
                response.sendRedirect("login.zul");
            } else {
                response.sendRedirect("/");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
