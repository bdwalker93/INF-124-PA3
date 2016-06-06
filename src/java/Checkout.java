/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brett
 */
public class Checkout extends HttpServlet {

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
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Checkout" + "</h1>");
            
            out.println("<h2 style=\"text-align:center\">Items in cart:" + "</h2>");
            
            out.println("<table class = \"outside_table\">");
            out.println("<tr class = \"tr1\">");
            out.println("<td class = \"td0\">");
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
            
            
            out.println("<tr class = \"tr1\">");
            out.println("<td class = \"td0\">");
            out.println("<img style = \"max-width:100px; max-height: 100px;\" src=\"/PA3/images/product_images/AP1.jpg\"");
            out.println("</td>");
            out.println("<td class = \"td1\">");
            out.println("Audimar Pidet AP1");
            out.println("</td>");
            out.println("<td class = \"td2\">");
            out.println("2");
            out.println("</td>");
            out.println("<td class = \"td3\">");
            out.println("$38.50");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
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
            
              
            out.println("<form class=\"submit_button_container\" action=\"ExecuteCheckout\">");
            out.println("<input class=\"order_button\" type=\"submit\" value=\"Place Your Order\">\n");
            out.println(" </form>");


            
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