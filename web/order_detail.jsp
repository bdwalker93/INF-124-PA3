
<%@page import="java.io.*, java.util.*, java.sql.*" %>
<%@page import="javax.servlet.http.*, javax.servlet.*" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.ResultSet" %>
<%@page session="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order_detail</title>
        <link rel="stylesheet"  href="style_sheets/navigation_style.css">
        <link rel="stylesheet"  href="style_sheets/body_style.css">
        <link rel="stylesheet"  href="style_sheets/confirmation_style.css"> 
    </head>
    <body>

        
        <% //making the connection to the database with the localhost.
            String connectionURL = "jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp17";
            java.sql.Connection connection = null;
            java.sql.Statement stmt = null;
            java.sql.ResultSet result = null;
            java.sql.ResultSet resultCustomer = null;
            String totalPrice = "";//hold the vaule of the total Price to display after the product description
            String note = "";//hold the note to display after the product description
            String orderNumber = request.getParameter("orderId");//get ur parameter as from order id
            String sql = "SELECT * FROM order_products INNER JOIN order_information ON order_products.order_id = order_information.order_id INNER JOIN product_descriptions ON product_descriptions.id = order_products.product_id WHERE order_products.order_id = " + orderNumber;
            String sqlCustomer = "SELECT * FROM order_information WHERE order_id = " + orderNumber;
            try {//connection to the database
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(connectionURL, "inf124grp17", "4ru&RuHU");
                
                stmt = connection.createStatement();
                
                resultCustomer = stmt.executeQuery(sqlCustomer);//execute the first query to load customer information
                while(resultCustomer.next()) {
                    totalPrice = resultCustomer.getString("order_cost");
                    note = resultCustomer.getString("notes");
                %>
                
                
         <nav>
         <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="Products">Products</a></li>
            <li><a href="meet_the_team.html">Meet The Team</a></li>
            <li><a href=about.html>About Us</a></li>
            <li style =float:right;><a style=background-color:red; href=Checkout>Checkout</a></li>

        </ul>
        </nav>   
         
        <h1>Order Detail</h1>
        <h2> 
                
            Hello <%= resultCustomer.getString("first_name") %>
           
        </h2>
        <div>
            Thank you for shopping at Watch Guyz. We would like to confirm your order and 
            let you know that your order is on the way. Your order number and delivery date is display below. If you 
            would like to view or change the status of your order please contact us on Watch Guyz.
        </div>
        <br>
        <div class="box">
            <br>
        </div>

        <table>
            <tr>
                <td style="min-width: 50%;">
                    The order was sent to : <b><n style=color:orange;> <%= resultCustomer.getString("first_name") + " " + resultCustomer.getString("last_name") %> </n></b><br>
                        
                    Contact Number: <b> <%= resultCustomer.getString("phone_number") %> </b> <br>
                    The order was sent to : <b> <%= resultCustomer.getString("street") +" " + resultCustomer.getString("city") +" " + resultCustomer.getString("state") +" " + resultCustomer.getString("zip_code") %> </b><br>              
                        
                </td>
                <td >
                   
                    Shipping Speed: <b><%= resultCustomer.getString("shipping_type") %></b><br>
                       
                    
                </td>

            </tr>

        </table>
        <hr/>
        <p class="orderNum"> Order number # <%= resultCustomer.getString("order_id") %> </p> <br>
        <%  }
        result = stmt.executeQuery(sql);//making another query to pull out product description
        while(result.next()){ %>
        <table>
            <tr>
                <td>
                    
                    
                    <img class='product_image' src=' <%= result.getString("image_path") %>'  alt='This is an image of the: <%= result.getString("brand") %>'>
        
                    
                   
                </td>
                <td class="orderDescription">
                    
                    <b><%= result.getString("name") %>  </b> <br>
                    <%= result.getString("description") %>
                    
                </td>
                <td class ="qty">
                    <b>Qty: <%= result.getString("product_quantity") %></b>
                </td>
                <td class="orderPrice">
                    
                    <b>$ <%= result.getString("price") %></b>
                    
                </td>
            </tr>
           
        </table>
        <%
            }
            
            }catch(Exception ex) {
                out.println("unable to connect to database");
            }
            connection.close();
         %>
         <table>
             <tr>
                 <td class="note">
                     Note: 
                 </td>
                 <td class="noteLine">
                     <% out.print(note); %>
                 </td>
             </tr>
         </table>
         <hr/>
         <P class="total"> Total with tax & shipping: $<% out.print(totalPrice); %> </p>
         <!--        This is the footer-->

        <footer class="footer">
            <ul>
            <li><Div style="font-size: 20px;
                     ">University of California, Irvine</div></li>
            <li><Div style="font-size: 20px;">Informatics 124/ CS 137</div></li>
            <li style="float:right;">Spring 2016</li>
            </ul>
        </footer>
    </body>
</html>
