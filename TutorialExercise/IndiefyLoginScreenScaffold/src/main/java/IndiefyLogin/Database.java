/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//written by Neil Matani (z5162753) for Lab4 INFS2605 20t1

public class Database {
    
    public static Connection conn;

    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:Indiefy.db");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /* Note that this method is called from the LoginScreen Controller */
    public boolean tryLogin(String username, String password) {
        
        // Assume that the user will enter incorrect credentials
        boolean loginSuccessful = false;
        System.out.println(username + password);
        
        try {
            // Open the connection
            Database.openConnection();
          
            // Use a Prepared Statement to query the database to check entered credentials
            String checkCreds = "SELECT * FROM login"
                                + "WHERE EXISTS username = ? AND password = ?;";
            PreparedStatement pst = Database.conn.prepareStatement(checkCreds);
            pst.setString(1, username);
            pst.setString(2, password);
            
            // Check the Result Set to see if the query returned a tuple, what should happen then?
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
               loginSuccessful = true; 
            }
            
            // Close the Result Set
            pst.close();
            conn.close();
            
               
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return loginSuccessful;
    }
    
    public static void createLoginTable() throws SQLException {
        
        
        // Initialise your Prepared Statement to create the LOGIN table
        String createTable = "CREATE TABLE login"
                + "(username TEXT NOT NULL"
                + ", password TEXT NOT NULL"
                + ")";
        
        //Initialise your Prepared Statement to add data to the LOGIN table
        String addData = "INSERT INTO login (username, password)"
                          + "VALUES ('Pretentious', 'Hipster');";
        
        
        // Initialise your Result Set
        String resultStatement = "SELECT * FROM login";
        ResultSet rs;
        
        // Open the connection
        Database.openConnection();
        Statement st = Database.conn.createStatement();
        
        
        try {
            System.out.println("Checking LOGIN table ");
            // Check if the Login Table exists
            rs = st.executeQuery(resultStatement);
            
            if (!rs.next()) {
                
                // Use the connection to create the Prepared Statement that will create the LOGIN table, and then and execute it
                st.execute(createTable);
                
                // Use the connection to create the Prepared Statement that will add data to the table, and then and execute it
                st.execute(addData);
                
            } else {
                System.out.println("LOGIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        st.close();
    }

}
