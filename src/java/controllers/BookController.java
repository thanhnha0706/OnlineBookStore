/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.CustomerDAO;
import models.Book;
import models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import models.Customer;
import services.MarshalService;

public class BookController extends HttpServlet {
    
    private CategoryDAO dao;
    private BookDAO bDao;
    
    public BookController() {
        dao = new CategoryDAO(Category.class);
        bDao = new BookDAO(Book.class);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        BookDAO dao = new BookDAO(Book.class);
        List<Book> a = dao.findAll();
        MarshalService m = new MarshalService();
        PrintWriter out = response.getWriter();
        
        try {
            out.println(m.marshal(a, Book.class));
        } catch (JAXBException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        
        // if the user is logged in
        if (customer != null) {
            
            String inputTitle = request.getParameter("inputTitle");
            String inputAuthor = request.getParameter("inputAuthor");
            String inputIsbn = request.getParameter("inputIsbn");
            int inputPage = Integer.parseInt(request.getParameter("inputPage"));
            String inputKeyword = request.getParameter("inputKeyword");
            String inputDescription = request.getParameter("inputDescription");
            //String inputReleaseDate = request.getParameter("inputReleaseDate");
            String inputThumbnail = request.getParameter("inputThumbnail");
            String inputPrice = request.getParameter("inputPrice");
            String categoryId = request.getParameter("inputCategory");

            Book newBook = new Book();
            newBook.setTitle(inputTitle);
            newBook.setAuthor(inputAuthor);
            newBook.setIsbn(inputIsbn);
            newBook.setNumberOfPage(inputPage);
            newBook.setKeyword(inputKeyword);
            newBook.setShortDescription(inputDescription);
            newBook.setReleaseDate(new Date());
            newBook.setThumbnail(inputThumbnail);
            newBook.setPrice(new BigDecimal(inputPrice));
            newBook.setCategoryId(dao.findById(categoryId));
            newBook.setBookId(java.util.UUID.randomUUID().toString());
            newBook.setCustomerId(customer);
            newBook.setStatus("pendding");
            bDao.insert(newBook);

            response.sendRedirect("/onlinebookstore");
        }
        // otherwise
        else {
            response.sendRedirect("login.zul");
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
