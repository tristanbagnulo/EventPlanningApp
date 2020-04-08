/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        try {
            // Open the connection
            openConnection();
            // Use a Prepared Statement to query the database to check entered credentials
            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM LOGIN WHERE USERNAME = ? AND PASSWORD = ?;");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            // Check the Result Set to see if the query returned a tuple, what should happen then?
            if (rs.next()) {
                loginSuccessful = true;
            }
            // Close the Result Set
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginSuccessful;
    }

    public static void createLoginTable() {

        // Initialise your Prepared Statement to create the LOGIN table
        PreparedStatement createLoginTable = null;
        //Initialise your Prepared Statement to add data to the LOGIN table
        PreparedStatement insertData = null;
        // Initialise your Result Set
        ResultSet rs = null;
        // Open the connection
        openConnection();

        try {
            System.out.println("Checking LOGIN table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "LOGIN", null);
            // Check if the Login Table exists
            if (!rs.next()) {
                // Use the connection to create the Prepared Statement that will create the LOGIN table, and then and execute it
                createLoginTable = conn.prepareStatement("CREATE TABLE LOGIN("
                        + "USERNAME VARCHAR (50),"
                        + " PASSWORD VARCHAR(50));");
                createLoginTable.execute();
                // Use the connection to create the Prepared Statement that will add data to the table, and then and execute it
                insertData = conn.prepareStatement("INSERT INTO LOGIN (USERNAME, PASSWORD) "
                        + "VALUES ('Pretentious','Hipster'),"
                        + "('some','thing');");
                insertData.execute();
                System.out.println("Creating LOGIN Table");
            } else {
                System.out.println("LOGIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createMusicTable() {

        // Initialise your Prepared Statement to create the LOGIN table
        PreparedStatement createMusicTable = null;
        //Initialise your Prepared Statement to add data to the LOGIN table
        PreparedStatement insertData = null;
        // Initialise your Result Set
        ResultSet rs = null;
        // Open the connection
        openConnection();

        try {
            System.out.println("Checking MUSIC table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "MUSIC", null);
            // Check if the Login Table exists
            if (!rs.next()) {
                // Use the connection to create the Prepared Statement that will create the LOGIN table, and then and execute it
                createMusicTable = conn.prepareStatement("CREATE TABLE MUSIC("
                        + "ALBUM VARCHAR (100),"
                        + " ARTIST VARCHAR(100),"
                        + " GENRE VARCHAR(100),"
                        + " YEAR VARCHAR(4));");
                createMusicTable.execute();
                // Use the connection to create the Prepared Statement that will add data to the table, and then and execute it
                insertData = conn.prepareStatement("INSERT INTO MUSIC (ALBUM, ARTIST, GENRE, YEAR) "
                        + "VALUES ('Feels','Animal Collective','Experimental Pop','2005'), "
                        + "('The Money Store','Death Grips','Experimental HipHop','2012'), "
                        + "('No Now','Clarence Clarity','Experimental Pop','2015'), "
                        + "('Infest The Rats Nest','King Gizzard and the Lizard Wizard','Thrash Metal','2019');");
                insertData.execute();
                System.out.println("Creating MUSIC Table");
            } else {
                System.out.println("MUSIC table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
