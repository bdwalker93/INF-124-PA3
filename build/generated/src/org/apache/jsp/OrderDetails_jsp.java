package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class OrderDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Order Confirmation</title>\n");
      out.write("\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        \n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/navigation_style.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/body_style.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style_sheets/confirmation_style.css\">\n");
      out.write("       \n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <!--        This is the navigator-->\n");
      out.write("        <nav>\n");
      out.write("         <ul>\n");
      out.write("             <li><a href=\"index.html\">Home</a></li>\n");
      out.write("            <li><a href=\"Products\">Products</a></li>\n");
      out.write("            <li><a href=\"meet_the_team.html\">Meet The Team</a></li>\n");
      out.write("            <li style=\" \"><a  href=\\\"about.html\\\">About Us</a></li>\n");
      out.write("            <li style =\"float:right; background-color:red\"><a class=\\\"theme_color\\\" href=\\\"Checkout\\\">Checkout</a></li>\n");
      out.write("            </ul>\n");
      out.write("            </nav>\n");
      out.write("             \n");
      out.write("             \n");
      out.write("            \n");
      out.write("         \n");
      out.write("<!--         Start of page-->\n");
      out.write("        <h1>\n");
      out.write("            Order Confirmation\n");
      out.write("        </h1>\n");
      out.write("        <h2>\n");
      out.write("            <?php \n");
      out.write("                \n");
      out.write("                echo \"Hello \" . $customerInfo['first_name'] . \",\";\n");
      out.write("            ?>\n");
      out.write("        </h2>\n");
      out.write("        <div>\n");
      out.write("            <?php\n");
      out.write("                echo \"Thank you for shopping at Watch Guyz. We would like to confirm your order and \"\n");
      out.write("                 . \" let you know that your order is on the way. Your order number and delivery date is display below. If you \"\n");
      out.write("                        . \"would like to view or change the status of your order please contact us on Watch Guyz.\";\n");
      out.write("                ?>\n");
      out.write("        </div>\n");
      out.write("        <br>\n");
      out.write("        <div class=\"box\">\n");
      out.write("            <br>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"customerInfo\">\n");
      out.write("                    <?php\n");
      out.write("                        echo\"The order was sent to : \". \"<b><n style=color:orange;>\" .$customerInfo['first_name'] . \" \" . $customerInfo['last_name'].\"</n></b>\". \"<br>\";\n");
      out.write("                        //print\"\\r\\n\";\n");
      out.write("                        echo\"Contact Number: \" .\"<b>\". $customerInfo['phone_number']. \"</b>\". \"<br>\";\n");
      out.write("                        echo\"The order was sent to :\" .\"<b>\". $customerInfo['street']. \" \". $customerInfo['city'] . \" \". $customerInfo['state']. \" \". $customerInfo['zip_code'].\"</b>\". \"<br>\";                    \n");
      out.write("                        ?>\n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <?php\n");
      out.write("                        echo \"Shipping Speed: \".\"<b>\" . $customerInfo['shipping_type']. \"</b>\". \"<br>\";\n");
      out.write("                        echo \"Customer's Note: \" .$customerInfo['notes'] . \"<br>\";\n");
      out.write("                    ?>\n");
      out.write("                </td>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <hr/>\n");
      out.write("        <br>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <td>\n");
      out.write("                    <?php\n");
      out.write("                        echo\"Order number #\" . $customerInfo['order_id'] . \"<br>\";\n");
      out.write("                        echo\"<img class='product_image' src='\" .$customerInfo['image_path']. \"' alt='This is an image of the: \".$customerInfo['brand'].\" - \".$customerInfo['name'].\"'>\";\n");
      out.write("        \n");
      out.write("                    \n");
      out.write("                    ?>\n");
      out.write("                </td>\n");
      out.write("                <td class=\"orderDescription\">\n");
      out.write("                    <?php\n");
      out.write("                        echo \"<b>\". $customerInfo['name'] .\"</b>\" . \"<br>\";\n");
      out.write("                        echo $customerInfo['description']; \n");
      out.write("                    ?>\n");
      out.write("                </td>\n");
      out.write("                <td class=\"orderPrice\">\n");
      out.write("                    <?php\n");
      out.write("                        echo \"<b>Total Cost: $\" .$customerInfo['order_cost']. \"</b>\";\n");
      out.write("                    ?>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        <br>\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("         <!--        This is the footer-->\n");
      out.write("        <footer class=\"footer\" style=\"position: fixed; bottom: 0; width: 100%\">\n");
      out.write("            <ul>\n");
      out.write("            <li><Div style=\"font-size: 20px;\">University of California, Irvine</div></li>\n");
      out.write("            <li><Div style=\"font-size: 20px;\">Informatics 124/ CS 137</div></li>\n");
      out.write("            <li style=\"float:right;\">Spring 2016</li>\n");
      out.write("            </ul>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("<?php\n");
      out.write("    $conn = null;\n");
      out.write("?>  ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
