/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
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
        
        final int MAX_DISPLAYED = 6;

        String currentId = request.getParameter("productID");
        //only output visited items if the user has been here before
        if(session.isNew())
        {
            //sets the array with the initial item 
            visitedIds.add(currentId);
            
            //sets the attribute with the array
            session.setAttribute(visitedIdsKey, visitedIds);
        }
        else
        {
        visitedIds = (LinkedList<String>)session.getAttribute(visitedIdsKey);



        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<table align = 'center'>");

        out.println("<tr>");
        for(int i = 0; i < visitedIds.size(); i++){
            out.println("<td>" + visitedIds.get(i) + "</td>");
//            out.println("<a href='ProductDescription?productID=" + rs.getString("id") + "' >");
//            out.println("<img class = 'product_image' src=" + rs.getString("image_path") + " alt=" + rs.getString("name") + ">  <br> ");
//            out.println("<b>" + rs.getString("brand") + "</b> <br>" + rs.getString("name") + "<br> <span class='price_text'> $" + rs.getString("price") + "</span> ");
//            out.println("</a>");
//            out.println("</td");

        }
        out.println("</tr>");


            out.println("</table>");
            
            //if this key is already in the list there is nothing to do here
            if(!visitedIds.contains(new String(currentId)))
            {
                out.println("dECISION: " + !visitedIds.contains(new String(currentId)));
                //handles cycling the list of keys if the max is reached
                visitedIds.addFirst(currentId);


                //dumps a visited if the list is already at 5
                if(visitedIds.size() >= MAX_DISPLAYED){
                    visitedIds.removeLast();
                }

                //sets the attribute
                session.setAttribute(visitedIdsKey, visitedIds);
            }
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
