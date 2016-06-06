/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brett
 */
public class ExecuteCheckout extends HttpServlet {

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
      
        int orderId = 25;
        
        response.setContentType("text/html;charset=UTF-8");
      
        HttpSession session = request.getSession(true);

        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
        
        /*
            This is where the DB push should be.
        
            All of the personal information needs to be taken from the request
                and push to the order_information table. The order_id for this push
                needs to be grabbed.
        
            Now all of the items in the cart and all of its quanities needs to be 
                pushed to the order_products tables with the order_id generated from 
                the above push.
        
            Cart now needs to be emptied
        
            Finally this should redirect to the Products servlet   
        */
        
        //empts the cart by push a new instantiation of the cart into the seesion attribute
        session.setAttribute("cart", new HashMap<String, Integer>());
        
         
        //redirects the page        
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/order_detail.jsp?orderId=" + orderId);
        RequetsDispatcherObj.forward(request, response);
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
