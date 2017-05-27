/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Customer;
import dao.CustomerDAO;
import dao.CustomerOrderDAO;
import dao.StaffDAO;
import entity.Book;
import entity.CustomerOrder;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author quyqu
 */
public class test extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            CustomerDAO cdao = new CustomerDAO(Customer.class);
            CustomerOrderDAO dao = new CustomerOrderDAO(CustomerOrder.class);
            StaffDAO sdao = new StaffDAO(Staff.class);
            Customer cus = (Customer) cdao.findById("771a8d1c-352f-481a-9904-bcb7ba46039c");
            if(cus == null){
                out.println("sad");
            }
        CustomerOrder order = new CustomerOrder();
//        order.setOrderDate(null);
//        order.setCustomerId(cus);
//        order.setShippingAddress("ss");
//        order.setShippingPhone("dsa");
//        order.setTax(11);
        order.setOrderId("sad");
//        //order.setTotalAmount((float)110.2);
//        if(sdao.findById("1") == null) {
//            out.println("ssssss");
//        }        order.setStaffId(sdao.findById("1"));
//        List<Book> bookList = (List<Book>) request.getSession().getAttribute("cart");
//        
//        //order.setBookList(bookList);
        
       dao.insert(order);
        } finally {
            out.close();
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
