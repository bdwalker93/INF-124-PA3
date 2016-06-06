/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.util.HashMap; 


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abbas
 */
public class Checkout extends HttpServlet {

    //stores the database connection
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
        final String DB_URL="jdbc:mysql://localhost:4956/inf124grp17";

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            out.println("<title>Products</title>");            
        out.println("<meta charset=\"UTF-8\">");            
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");            
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/checkout_2_style.css\">");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/navigation_style.css\">");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/body_style.css\">");
                
        out.println("</head>");
        out.println("<body>");

        /*BEGINNING OF PAGE*/
        //nav bar
            out.println(" <!--        This is the navigator-->\n" +
            "        <nav>\n" +
            "         <ul>\n" +
            "            <li><a href=\"index.html\">Home</a></li>\n" +
            "            <li><a href=\"Products\">Products</a></li>\n" +
            "            <li><a href=\"meet_the_team.html\">Meet The Team</a></li>\n" +
            "            <li><a href=\"about.html\">About Us</a></li>\n" +
            "           <li style =\"float:right; \"><a style=\"background-color:red;\" href=\"Checkout\">Checkout</a></li>\n" +
            "        </ul>\n" +
            "        </nav>");
            
            

            
            
            out.println("<title>Checkout</title>");     
            out.println("<form name=\"order_form\" id=\"purchase_form\" action=\"updateOrderdb.php\" onsubmit=\"return order_validation()\" method=\"POST\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Checkout" + "</h1>");
            
            out.println("<h2 style=\"text-align:center\">Items in cart:" + "</h2>");
            
            HttpSession session = request.getSession(true);
            
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
            if(cart == null || cart.isEmpty() || session.isNew())
            {
             
            out.println("<table class = \"outside_table\">");
            out.println("<tr class = \"tr1\">");
            out.println("<td class = \"td0\">");
            
            out.println("<h2>Your Cart is Empty" + "</h2>");
            out.println("</td>");

            out.println("</tr>");
            out.println("</table>");
                //output that the cart has nothign in it
            }
            else
            {
                
            out.println("<table class = \"outside_table\">");
            out.println("<tr class = \"tr1\">");
            out.println("<td class = \"td0\">");
//            out.println("Hello! World");
            out.println("</td>");
            out.println("<td class = \"td1\">");
            out.println("Product");
            out.println("</td>");
            out.println("<td class = \"td2\">");
            out.println("Qty");
            out.println("</td>");
            out.println("<td class = \"td3\">");
            out.println("Total");
            out.println("</td>");
            out.println("</tr>");
           
            for (String key: cart.keySet()) {
                
            try{
                Statement stmt;
                ResultSet rs; 
                
               synchronized(mutex) {
                    // Execute SQL query to get all the watch info from the db
                    stmt = conn.createStatement();
                    String sql;
                    sql = "SELECT * FROM product_descriptions WHERE id = " + request.getParameter(key);
                    rs = stmt.executeQuery(sql);

                    int count = 0;
                    out.println("<table align = 'center'>");
                    out.println("<tr class = \"tr1\">");
                    out.println("<td class = \"td0\">");
                    out.println("<img style = \"max-width:100px; max-height: 100px;\" src='" + rs.getString("image_path") + " '");
                    out.println("</td>");
                    out.println("<td class = \"td1\">");
                    out.println(rs.getString("name"));
                    out.println("</td>");
                    out.println("<td class = \"td2\">");
                    out.println(cart.get(key));
                    out.println("</td>");
                    out.println("<td class = \"td3\">");
                    out.println(rs.getString("price"));
                    out.println("</td>");
                    out.println("</tr>");
               
               }
               
            }
          catch ( SQLException ex) {
         Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         
        }       
            out.println("</table>");
//            

            }
                     
                
            }
                
                
            
            
            
            
            out.println("<div class=\"personal_information_container\">\n" +
"                   <hr>\n" +
"                   <h2>Personal Information</h2>\n" +
"                   <hr>\n" +
"\n" +
"                   <table class=\"personal_table\">\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               First name \n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"first_name\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Last name \n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"last_name\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Phone Number\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"phone_number\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Street\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"street\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Zip Code\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input id=\"zip_box\" type=\"text\" name=\"zip_code\" onblur=\"validZip(this.value); getZipInfo(this.value);  updateEntireSummary(<?php echo $productInfo['id'] ?>)\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               City\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"city\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               State \n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"state\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Shipping Method\n" +
"                           </td>\n" +
"                           <td class=\"shipping_col\">\n" +
"                               <div style=\"float: top\">\n" +
"                                   <input id=\"shiping1\" type=\"radio\" name=\"shipping_info\" value=\"Overnight\" onclick=\"updateEntireSummary(<?php echo $productInfo['id'] ?>)\">Overnight ($20)<br>\n" +
"                               </div>\n" +
"                               <div style=\"float: top\">\n" +
"                                   <input id=\"shiping2\" type=\"radio\" name=\"shipping_info\" value=\"2-Day Expedited\" onclick=\"updateEntireSummary(<?php echo $productInfo['id'] ?>)\">2-Day Expedited ($10)<br>\n" +
"                               </div>\n" +
"                               <div style=\"float: top\">\n" +
"                                   <input id=\"shiping3\" type=\"radio\" name=\"shipping_info\" value=\"6-Day Ground\" onclick=\"updateEntireSummary(<?php echo $productInfo['id'] ?>)\" checked>6-Day Ground (Free)<br>\n" +
"                               </div>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Credit Card Number\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"credit_card_number\" onblur=\"detectCardType(this.value)\"required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               Credit Card Expiration\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n" +
"                               <input type=\"text\" name=\"credit_card_expiration\" required>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"\n" +
"\n" +
"                       <tr class=\"personal_table_row\">\n" +
"                           <<td class=\"personal_table_col\">\n" +
"                               Notes\n" +
"                           </td>\n" +
"                           <td class=\"personal_table_col\">\n"  +
"                               <textarea  name=\"notes\" class=\"notes\" form=\"purchase_form\"></textarea>\n" +
"                           </td>\n" +
"                       </tr>\n" +
"                   </table>\n" +
"               </div>\n" +
"\n" +
"\n");

            
            
            
   
            
            out.println("<div class=\"submit_button_container\">\n" +
"                    <input class=\"order_button\" type=\"submit\" value=\"Place Your Order\">\n" +
"                </div>");
            
            out.println("</form>");
            
            String sql = "INSERT INTO order_informaiton (first_name, last_name, phone_number, street, zip_code, city, state, shipping_type, credit_card_number, credit_card_expiration, notes) "
                    + "values ";
            
            
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