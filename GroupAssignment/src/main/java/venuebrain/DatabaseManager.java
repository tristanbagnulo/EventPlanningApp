/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Blair
 */
public class DatabaseManager {
    private static Connection sharedConnection;
    
    /**
     * This method is shared by all the `public static` methods in this class, to reuse the same code.
     * @return whether or not the connection was successfully opened
     */
    private static boolean openConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.sharedConnection = DriverManager.getConnection("jdbc:sqlite:Database.db");
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    private static boolean closeConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            sharedConnection.close();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    private static boolean createTheOnlyTableWeNeed() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_PLANETS + " ("
                    + "planet_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "planet_name TEXT, "
                    + "planet_radius_km INTEGER)";
            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    /*private static boolean setupDummyData() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + DatabaseManager.TABLE_NAME_FOR_PLANETS
                    + " (planet_name, planet_radius_km)"
                    + " VALUES (?, ?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] planetNames = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
            int[] radiusKmSizes = {2440, 6052, 6371, 3390, 69911, 58232, 25362, 24622};
            for (int i = 0; i < planetNames.length; i++) {
                psmt.setString(1, planetNames[i]);
                psmt.setInt(2, radiusKmSizes[i]);
                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);
            }
            
            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
        
    }*/
    
    public static boolean setupDatabaseOnFirstRun() {
        boolean wasThisMethodSuccessful = false;
        try {
            // check if we need to setup database
            DatabaseManager.openConnection();
            DatabaseMetaData dbmd = DatabaseManager.sharedConnection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_PLANETS, null);
            boolean needToSetupDatabase = false;
            if (!rs.next()) {
                needToSetupDatabase = true;
            }
            DatabaseManager.closeConnection();
            
            // do further stuff if required
            if (needToSetupDatabase) {
                boolean createdTableSuccessfully = DatabaseManager.createTheOnlyTableWeNeed();
                boolean createdDataSuccessfully = DatabaseManager.setupDummyData();
                wasThisMethodSuccessful = (createdTableSuccessfully && createdDataSuccessfully);
            } else {
                wasThisMethodSuccessful = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    public static boolean createSchema(){
        // Initialise your Prepared Statement to create the tables
       boolean methodSuccess = true;
        ArrayList<String> insertTables = new ArrayList<String>();
       
        //Admin table
        insertTables.add("CREATE TABLE admin"
                + "(admin_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", username TEXT NOT NULL"
                + ", password TEXT NOT NULL"
                + ", first_name TEXT"
                + ", last_name TEXT"
                + ")"
        );
        //guest table        
        insertTables.add("CREATE TABLE guest"
                + "(guest_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", access_code INTEGER NOT NULL"
                + ", email_address TEXT NOT NULL"
                +", phone_number TEXT NOT NULL"
                + ", first_name TEXT"
                + ", last_name TEXT"
                + ")"
        );
        //event table
        insertTables.add("CREATE TABLE event"
                + "(event_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", event_name TEXT"
                + ", location TEXT"
                + ")"
        );
        //invitation table
        insertTables.add("CREATE TABLE invitation"
                + "(invitation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", event_id INTEGER FOREIGN KEY (event_id) REFERENCES event(event_id)"
                + ", guest_id INTEGER FOREIGN KEY (guest_id) REFERENCES guest(guest_id)"
                + ", admin_id INTEGER FOREIGN KEY (admin_id) REFERENCES admin(admin_id)"
                + ", invitation_name TEXT"
                + ", no_people INTEGER"
                +")"
        );
        //rsvp table
        insertTables.add("CREATE TABLE rsvp"
                + "(rsvp_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", invitation_id INTEGER FOREIGN KEY (invitation_id) REFERENCES invitation(invitation_id)"
                + ", decision BOOLEAN NOT NULL"
                + ", dietary_requirements TEXT NOT NULL"
                + ", rsvp_datetime DATE NOT NULL"
                + ")"
        );
       return methodSuccess; 
    }
        
  
    
    /*public static Planet fetchPlanetByName(String planetName) {
        Planet preparedReturn = null;
        try {
            DatabaseManager.openConnection();
            String sqlString = "SELECT * FROM " + DatabaseManager.TABLE_NAME_FOR_PLANETS
                    + " WHERE planet_name = ?";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            psmt.setString(1, planetName);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                preparedReturn = new Planet(rs.getString("planet_name"), rs.getInt("planet_radius_km"));
            }
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return preparedReturn;
        }
    }*/
}
