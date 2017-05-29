/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import models.Book;
import models.Category;
import services.MarshalService;

public class SearchController extends HttpServlet {

    private BookDAO bookDao;
    private MarshalService mar;

    public SearchController() {
        bookDao = new BookDAO(Book.class);
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
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        PrintWriter out = res.getWriter();
        res.setContentType("Application/json");

        if (req.getParameter("q") != null) {

            String searchText = (req.getParameter("q")).trim();

            if (!searchText.isEmpty()) {
                // save keyword to session
                session.setAttribute("searchText", searchText);
                out.println("{\"status\":\"OK\"}");
            } else {
                out.println("{\"error\": {\"message\": \"Your input is empty!\"}}");
            }

        } else {

            String searchText = (String) session.getAttribute("searchText");
            List<Book> results;

            if (searchText != null) {

                // search books with matched keywords
                results = bookDao.findByTitle(searchText);

                if (results == null) {
                    results = new ArrayList<Book>();
                } else {
                                        
//                    Set<Category> categories = new HashSet<Category>();
//
//                    // retrieve categories
//                    for (Book book : results) {
//                        Category category = book.getCategoryId();
//                        categories.add(category);
//                    }
//
//                    // save to session
//                    session.setAttribute("searchedCategories", categories);
                }

            } else {
                results = new ArrayList<Book>();
            }

            try {
                out.println(mar.marshal(results, Book.class));
            } catch (JAXBException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
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
