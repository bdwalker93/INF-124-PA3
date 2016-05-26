/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brett
 */
public class AddViewerTracking extends HttpServlet {

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
            out.println(" <div class=\"viewers\"> Number of people viewing this page: <span class=\"viewers_count\">" + addToServletContext(request) + "</span></div>");
        }
    }

    private String addToServletContext(HttpServletRequest request) {
          ServletContext context = request.getSession().getServletContext();
          String itemTracking = "itemTracking";

          int productID = Integer.valueOf(request.getParameter("productID"));
          HashMap hm = (HashMap)context.getAttribute(itemTracking);
          Integer count = 1;
          
          if(hm == null)
          {
            hm = new HashMap();
            hm.put(productID, count);

            context.setAttribute(itemTracking, hm);

            return "1";
          }
          else
          {
            count = (Integer)hm.get(productID);
            
            if(count == null)
                hm.put(productID, new Integer(1));
            else
                hm.put(productID, ++count);
            
            context.setAttribute(itemTracking, hm);
            
            return String.valueOf(hm.get(productID));
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
