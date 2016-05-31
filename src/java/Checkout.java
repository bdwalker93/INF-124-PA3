/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brett
 */
public class Checkout extends HttpServlet {
    
    private Connection conn;
    private String mutex = ""; //to make the db connection thread-safe
    
    //initi function to open db connection
    @Override
    public void init(ServletConfig config){
        try { 
            super.init(config);       
            databaseConnect();
            
        } 
        catch (ServletException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //closes db connection
    @Override
    public void destroy(){
        databaseDisconnect();
    }
    
    private void databaseConnect()
    {
      // JDBC driver name and database URL
        final String DB_URL="jdbc:mysql://localhost/inf124grp17";

      //  Database credentials
        final String USER = "root";
        final String PASS = "root";

        try{
        // Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } 
        catch (ClassNotFoundException | SQLException ex) {
         Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         
        }
     
    }
    
    private void databaseDisconnect()
    {
        try {
            conn.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            // logic
            HttpSession session = request.getSession();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Checkout</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Checkout at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        doPost(request, response);
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
        PrintWriter out = response.getWriter(); 
        // all the fields come this way
        
        // <input name="address"> .. city, zip, credit card, name,
        
        int error = 0;
        
        String productId = request.getParameter("product_id");
        if (productId == null) {
            error++;
        }
        
        String orderCost = request.getParameter("order_cost");
        if (orderCost == null) {
            error++;
        }
        
        String size = request.getParameter("size");
        if (size == null) {
            error++;
        }
        
        String quantity = request.getParameter("quantity");
        if (quantity == null) {
            error++;
        }
        
        String firstName = request.getParameter("first_name");
        if (firstName == null) {
            error++;
        }
        
        String lastName = request.getParameter("last_name");
        if (lastName == null) {
            error++;
        }
        
        String phoneNumber = request.getParameter("phone_number");
        if (phoneNumber == null) {
            error++;
        }
        
        String street = request.getParameter("street");
        if (street == null) {
            error++;
        }
        
        String city = request.getParameter("city");
        if (city == null) {
            error++;
        }
        
        String state = request.getParameter("state");
        if (state == null) {
            error++;
        }
        
        String zipCode = request.getParameter("zip_code");
        if (zipCode == null) {
            error++;
        }
        
        String shippingType = request.getParameter("shipping_type");
        if (shippingType == null) {
            error++;
        }
        
        String creditCardNumber = request.getParameter("credit_card_number");
        if (creditCardNumber == null) {
            error++;
        }
        
        String creditCardExpiration = request.getParameter("credit_card_expiration");
        if (creditCardExpiration == null) {
            error++;
        }
        
        String notes = request.getParameter("notes");
        if (notes == null) {
            error++;
        }
        
        if (error > 0 ) {
            //out.println("<h1>All fields must be filled</h1>");
            //return;
        }
        
        ///// test remove before submission
        productId = "1";
        creditCardNumber = "23423423423423";
        orderCost = "1234";
        size = "5";
        quantity = "3";
        firstName = "Daddy";
        lastName = "Jones";
        phoneNumber = "949-371-3773";
        street = "Veneto";
        city = "Irvine";
        state = "CA";
        zipCode = "92614";
        shippingType = "Ground";
        creditCardExpiration = "6-20-2019";

        ////// end test
        
        
        
        
        
        
        
        
        try {
            // Last InsertId() STATEMENT.ID
            
            String sql = "INSERT INTO order_information "
                + " (product_id, order_cost, size, quantity, first_name, last_name, phone_number, street,city, state, zip_code, shipping_type, credit_card_number, credit_card_expiration, notes) "
                + " VALUES ('" + productId + "', '" + orderCost + "', '" +  size + "', '" + quantity + "', '" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + street + "', '" + city + "', '" + state + "', '" + zipCode + "', '" + shippingType + "', '" + creditCardNumber + "', '" + creditCardExpiration + "', 'notes')";
            
            System.out.println(sql);
            
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int orderId;
            orderId = stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
            
            System.out.println("------- " + Integer.toString(orderId));
            // redirect order id
            // "http://localhost:8080/PA3/order_detail.jsp?orderId=" + Integer.toString(orderId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
