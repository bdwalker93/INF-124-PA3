<html>
    <head>
        <title>Order Confirmation</title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" type="text/css" href="style_sheets/navigation_style.css">
        <link rel="stylesheet" type="text/css" href="style_sheets/body_style.css">
        <link rel="stylesheet" type="text/css" href="style_sheets/confirmation_style.css">
       
        
    </head>
    <body>
         <!--        This is the navigator-->
        <nav>
         <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="Products">Products</a></li>
            <li><a href="meet_the_team.html">Meet The Team</a></li>
            <li style="float:right"><a class="theme_color" href="about.html">About Us</a></li>

        </ul>
        </nav>
         
<!--         Start of page-->
        <h1>
            Order Confirmation
        </h1>
        <h2>
            <?php 
                
                echo "Hello " . $customerInfo['first_name'] . ",";
            ?>
        </h2>
        <div>
            <?php
                echo "Thank you for shopping at Watch Guyz. We would like to confirm your order and "
                 . " let you know that your order is on the way. Your order number and delivery date is display below. If you "
                        . "would like to view or change the status of your order please contact us on Watch Guyz.";
                ?>
        </div>
        <br>
        <div class="box">
            <br>
        </div>

        <table>
            <tr>
                <td class="customerInfo">
                    <?php
                        echo"The order was sent to : ". "<b><n style=color:orange;>" .$customerInfo['first_name'] . " " . $customerInfo['last_name']."</n></b>". "<br>";
                        //print"\r\n";
                        echo"Contact Number: " ."<b>". $customerInfo['phone_number']. "</b>". "<br>";
                        echo"The order was sent to :" ."<b>". $customerInfo['street']. " ". $customerInfo['city'] . " ". $customerInfo['state']. " ". $customerInfo['zip_code']."</b>". "<br>";                    
                        ?>
                </td>
                <td>
                    <?php
                        echo "Shipping Speed: "."<b>" . $customerInfo['shipping_type']. "</b>". "<br>";
                        echo "Customer's Note: " .$customerInfo['notes'] . "<br>";
                    ?>
                </td>

            </tr>

        </table>
        <hr/>
        <br>
        <table>
            <tr>
                <td>
                    <?php
                        echo"Order number #" . $customerInfo['order_id'] . "<br>";
                        echo"<img class='product_image' src='" .$customerInfo['image_path']. "' alt='This is an image of the: ".$customerInfo['brand']." - ".$customerInfo['name']."'>";
        
                    
                    ?>
                </td>
                <td class="orderDescription">
                    <?php
                        echo "<b>". $customerInfo['name'] ."</b>" . "<br>";
                        echo $customerInfo['description']; 
                    ?>
                </td>
                <td class="orderPrice">
                    <?php
                        echo "<b>Total Cost: $" .$customerInfo['order_cost']. "</b>";
                    ?>
                </td>
            </tr>
        </table>

        <br>
        <br>

         <!--        This is the footer-->
        <footer class="footer" style="position: fixed; bottom: 0; width: 100%">
            <ul>
            <li><Div style="font-size: 20px;">University of California, Irvine</div></li>
            <li><Div style="font-size: 20px;">Informatics 124/ CS 137</div></li>
            <li style="float:right;">Spring 2016</li>
            </ul>
        </footer>
    </body>
</html>

<?php
    $conn = null;
?>  