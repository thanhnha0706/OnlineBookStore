/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import models.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import services.MarshalService;
import services.SecurityService;

public class LoginController extends HttpServlet {

    private CustomerDAO dao;
    private SecurityService ser;
    private MarshalService mar;

    public LoginController() {
        dao = new CustomerDAO(Customer.class);
        ser = new SecurityService();
        mar = new MarshalService();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("Application/json");
        HttpSession session = req.getSession();
        PrintWriter out = res.getWriter();
        Customer customer = (Customer) session.getAttribute("customer");
        // hide password
        customer.setPassword(null);
        // if the customer has not logged in
        if (customer == null) {
            customer = new Customer();
        }
        
        // respond
        try {
            out.println(mar.marshal(customer, Customer.class));
        } catch (JAXBException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            String inputEmail = request.getParameter("inputEmail");
            String inputPassword = request.getParameter("inputPassword");
            HttpSession session = request.getSession();
            if (inputEmail != null && inputPassword != null) {
                
                Customer cus = dao.authenticate(inputEmail, ser.hashPassword(inputPassword));
               
                if (cus != null) {
                    session.setAttribute("customer", cus);
                    response.sendRedirect("/onlinebookstore");
                } else {
                    response.sendRedirect("register.zul");
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
