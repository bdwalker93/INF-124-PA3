/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
public class SessionTracking extends HttpServlet {

    private Connection databaseConnect()
    {
        Connection conn = null;
        
      // JDBC driver name and database URL
        final String DB_URL="jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp17";

      //  Database credentials
        final String USER = "inf124grp17";
        final String PASS = "4ru&RuHU";

        try{
        // Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } 
        catch (ClassNotFoundException | SQLException ex) {
         Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         
        }
     return conn;
     
    }
    
    private void databaseDisconnect(Connection conn)
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
        
        // Create a session object if it is already not  created.
          HttpSession session = request.getSession(true);

          //stores the names of the last images visited
        LinkedList<String> visitedIds = new LinkedList<String>();
        String visitedIdsKey = new String("numVisited");
        
        final int MAX_DISPLAYED = 5;
        
        PrintWriter out = response.getWriter();

        String currentId = request.getParameter("productID");
        //only output visited items if the user has been here before
        if(session.isNew())
        {
            //sets the array with the initial item 
            visitedIds.add(currentId);

            //sets the attribute with the array
            session.setAttribute(visitedIdsKey, visitedIds);
            
            out.println("<div></div>");

        }
        else
        {
            visitedIds = (LinkedList<String>)session.getAttribute(visitedIdsKey);

            // Set response content type
            response.setContentType("text/html");

            try{
                Statement stmt; 
                ResultSet rs;
     
                //establishes connection
                Connection conn = databaseConnect();

                // Execute SQL query to get all the watch info from the db
                stmt = conn.createStatement();
                String sql;
                rs = null; 

                out.println("<table align = 'center'>");

                for(int i = 0; i < visitedIds.size() && i < 2; i++)
                    out.println("<th></th>");

                out.println("<th style=\"font-size: 30px\">Recently Visited</th>");

                out.println("<tr>");

                for(int i = 0; i < visitedIds.size(); i++){

                    sql = "SELECT * FROM product_descriptions WHERE id = " + visitedIds.get(i);
                    rs = stmt.executeQuery(sql);

                    //need to advance it to the first row
                    rs.next();

                    out.println("<td class=\"item_cell\">");
                    out.println("<a href='ProductDescription?productID=" + rs.getString("id") + "' >");
                    out.println("<img class = 'visited_image' src=" + rs.getString("image_path") + " alt=" + rs.getString("name") + ">  <br> ");
                    out.println("<b>" + rs.getString("brand") + "</b> <br>" + rs.getString("name"));
                    out.println("</a>");
                    out.println("</td>");

                }

                databaseDisconnect(conn);
                
                out.println("</tr>");
                out.println("</table>");

                
                
                // Clean-up environment
                if(rs != null)
                    rs.close();
                stmt.close();
                
            }   
            catch (SQLException ex) {    
                Logger.getLogger(SessionTracking.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //logic for determining which products are here
            if(!visitedIds.contains(new String(currentId)) && !visitedIds.getLast().equals(currentId))
            {
                //dumps a visited if the list is already at 5
                if(visitedIds.size() >= MAX_DISPLAYED){
                    visitedIds.removeLast();
                }
                //handles cycling the list of keys if the max is reached
                visitedIds.addFirst(currentId);

                //sets the attribute
                session.setAttribute(visitedIdsKey, visitedIds);
            }
        }
        
        //increments our addviewer  

        //displaying the number of people viewing the current page
//        out.println(" <div class=\"viewers\"> Number of people viewing this page: <span class=\"viewers_count\">" + AddViewerTracking.getViewerCount(Integer.parseInt(currentId), request.getSession().getServletContext()) + "</span></div>");
        out.println(" <div class=\"viewers\"> Number of people viewing this page: <span id=\"view_data\" class=\"viewers_count\"></span></div>");
            
        out.println("<script> addView(" + request.getParameter("productID") + ")</script>");

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
