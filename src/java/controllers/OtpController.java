/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerOrderDAO;
import dao.StaffDAO;
import models.Book;
import models.Customer;
import models.CustomerOrder;
import models.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import services.SecurityService;

public class OtpController extends HttpServlet {

    private SecurityService ser;
    private CustomerOrderDAO dao;
    private StaffDAO sdao;

    public OtpController() {
        ser = new SecurityService();
        dao = new CustomerOrderDAO(CustomerOrder.class);
        sdao = new StaffDAO(Staff.class);
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
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String phone = request.getParameter("phone");
        String sessionOtp = (String) session.getAttribute("otp");
        //if (session.getAttribute("otp") == null) {
        String otp = ser.getOtp();
        //String phone = "01267698452";
        session.setAttribute("otp", otp);
        try {
        this.ser.otpv2(otp + "-" + phone);
        out.println(otp);
            } catch (JSONException ex) {
                Logger.getLogger(OtpController.class.getName()).log(Level.SEVERE, null, ex);
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
        //response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String opt = request.getParameter("otp");
        String sessionOtp = (String) session.getAttribute("otp");

        if (sessionOtp.equals(opt)) {
            CustomerOrder customerOrder = this.initOrder(request);
            String link = "https://www.baokim.vn/payment/product/version11?business=tranquy2512%40gmail.com&id=&order_description=Payment for cart&product_name=Cart number 152&product_price=" + customerOrder.getTotalAmount() + "&product_quantity=1&total_amount=" + customerOrder.getTotalAmount() + "&url_cancel=http://localhost:8080/onlinebookstore/success.zul&url_detail=das&url_success=ed";
            out.println(link);
        } else {
            out.println("error");
        }
    }

    private CustomerOrder initOrder(HttpServletRequest request) {
        Customer cus = (Customer) request.getSession().getAttribute("cus");
        CustomerOrder order = new CustomerOrder();
        order.setOrderDate(new Date());
        order.setCustomerId(cus);
        order.setShippingAddress(request.getParameter("inputAddress"));
        order.setShippingPhone(request.getParameter("inputTelephone"));
        order.setTax(0);
        order.setOrderId(java.util.UUID.randomUUID().toString());
        order.setTotalAmount(Float.parseFloat(request.getParameter("total")));
        order.setStaffId(sdao.findById("1"));
        order.setStatus("pendding");
        List<Book> bookList = (List<Book>) request.getSession().getAttribute("cart");
        
        order.setBookList(bookList);
        
        CustomerOrder insertedOrder = dao.insert(order);
        request.getSession().setAttribute("order", insertedOrder);
        return insertedOrder;
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
