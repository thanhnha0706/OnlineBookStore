/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookDAO;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import services.MarshalService;

public class CartController extends HttpServlet {

    private BookDAO dao;
    private MarshalService mar;

    public CartController() {
        dao = new BookDAO(Book.class);
        mar = new MarshalService();
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if (action != null) {
            if (action.equals("add")) {
                List<Book> cart = (List<Book>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<Book>();
                    session.setAttribute("cart", cart);
                }
                String bookId = request.getParameter("bookId");
                Book book = dao.findById(bookId);
                if (book != null) {
                    cart.add(book);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.println("ok");
            } else if (action.equals("get")) {
                List<Book> cart = (List<Book>) session.getAttribute("cart");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                if(cart == null) {
                    cart = new ArrayList<Book>();
                }
                try {
                    out.println(this.mar.marshal(cart, Book.class));
                } catch (JAXBException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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
