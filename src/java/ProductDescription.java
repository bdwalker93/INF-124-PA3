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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brett
 */
public class ProductDescription extends HttpServlet {
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
        final String DB_URL="jdbc:mysql://localhost/inf124grp17";

      //  Database credentials
        final String USER = "root";
        final String PASS = "";

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
           
            //to store the returned result set and statement
            ResultSet rs;
            Statement stmt;
            
            //critical section of function
            synchronized(mutex){
                // Execute SQL query to get all the watch info from the db
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM product_descriptions WHERE id = " + request.getParameter("productID");
                rs = stmt.executeQuery(sql);
            }
            //need to advance it to the first row
            rs.next();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> " + rs.getString("title") + "</title>");            
            out.println("<meta charset=\"UTF-8\">");            
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");            
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/product_description_style.css\">");
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
                "            <li style=\"float:right\"><a class=\"theme_color\" href=\"about.html\">About Us</a></li>\n" +
                "        </ul>\n" +
                "        </nav>");

            //products display

            out.println("<h1>" + rs.getString("brand") + " </h1>");
            out.println("<h2>" + rs.getString("name") + " </h2>");


            out.println("<img class='product_image' src='" + rs.getString("image_path") + " ' alt='This is an image of the: " + rs.getString("brand") + "  - " + rs.getString("name") + " '>");

            out.println("<div class=\"description_text\">" + rs.getString("description") + "</div>");
            out.println("<div class=\"specs_text\">Price: $" + rs.getString("price") + " <br>Product ID: #" + rs.getString("id") + " </div>");

            out.println("<form class=\"checkout_button_form\" action=\"Checkout\">");
             out.println("<input type='hidden' name='productID' value='" + request.getParameter("productID") + "'>");
            out.println("<input class=\"checkout_button\" type=\"submit\" value=\"Buy It Now!\">");
            out.println(" </form>");


            // Clean-up environment
            rs.close();
            stmt.close();

            //including session tracking info (no the reappending of the product id)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/SessionTracking");
            dispatcher.include(request, response);
            
            //including session tracking info (no the reappending of the product id)
            RequestDispatcher viewerDispatcher = request.getRequestDispatcher("/AddViewerTracking");
            viewerDispatcher.include(request, response);
                        
            //output the footer
            out.println("  <!--        This is the footer-->\n" +
            "        <footer>\n" +
            "            <ul>\n" +
            "            <li><Div style=\"font-size: 20px;\">University of California, Irvine</div></li>\n" +
            "            <li><Div style=\"font-size: 20px;\">Informatics 124/ CS 137</div></li>\n" +
            "            <li style=\"float:right;\">Spring 2016</li>\n" +
            "            </ul>\n" +
            "        </footer>");

            out.println("</body>");
            out.println("</html>");
        } 
        catch (SQLException ex) {
        Logger.getLogger(ProductDescription.class.getName()).log(Level.SEVERE, null, ex);
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
