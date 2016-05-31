import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicholasimkamp
 */
public class Cart {
    HashMap<String, Items> basket;
    private Connection conn;
    public Cart()
    {
        basket = new HashMap<String, Items>();
    
    }
    
    private void connect() {
        final String DB_URL="jdbc:mysql://localhost/inf124grp17";

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
    
    public void add(String pid, int qty)
    {
         if (qty < 0) {
             return;
         }
         
         if (basket.containsKey(pid)) {
             basket.get(pid).quanity += qty;
             return;
         }
                
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM product_descriptions WHERE id = " + pid;
            ResultSet rs = stmt.executeQuery(sql);
        
            if (!rs.next()) {
                // donzo
                return;
            }
            
            ProductItem p = new ProductItem();
            
            p.id = rs.getString("id");
            p.brand = rs.getString("brand");
            p.name = rs.getString("price");
            p.description = rs.getString("description");
            p.title = rs.getString("title");
            p.price = rs.getString("price");
            p.image_path = rs.getString("image_path");
            
            Items i = new Items();
            i.productItem = p;
            i.quanity = qty;
            
            basket.put(pid, i);
        
   
        } catch (Exception e) {
            // do nothing cause IDGAF
        }
    }
    
    public ProductItem getProduct(String pid) {
        if ( ! basket.containsKey(pid)) {
            return null;
        }
        
        return basket.get(pid).productItem;
    }
    
    public void remove(String pid){
        basket.remove(pid);
    }

    public float getTotal()
    {
        float sum = 0;
        
        for (Items i : basket.values()) {
            sum += Float.parseFloat(i.productItem.price) * i.quanity;
        }
        
        return sum;
    }
    

    
    
    
}
