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
import java.util.Set;

/**
 *
 * 
 */
public class DatabaseManager {
    public static Connection sharedConnection;
    
    /**
     * This method is shared by all the `public static` methods in this class, to reuse the same code.
     * @return whether or not the connection was successfully opened
     */
    public static boolean openConnection() {
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
    
    public static boolean closeConnection() {
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
    
   /* private static boolean createTheOnlyTableWeNeed() {
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
    }*/
    
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
    
/*  public static boolean setupDatabaseOnFirstRun() {
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
    }*/
    
    public static void createSchema() throws SQLException{
        // Initialise statements to create the tables
        
        //Admin table
        String createAdmin = "CREATE TABLE admin"
                + "(admin_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", username TEXT NOT NULL"
                + ", password TEXT NOT NULL"
                + ", first_name TEXT"
                + ", last_name TEXT"
                + ")";
        
        //guest table        
        String createGuest = "CREATE TABLE guest"
                + "(guest_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", access_code TEXT NOT NULL"
                + ", email_address TEXT NOT NULL"
                +", phone_number TEXT NOT NULL"
                + ", first_name VARCHAR2(30)"
                + ", last_name VARCHAR2(30)"
                + ")";
        
        //event table
       String createEvent = "CREATE TABLE event"
                + "(event_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", event_name TEXT"
                + ", location TEXT"
                + ", date_ TEXT"
                + ")";
       
        //invitation table
        String createInvitation = "CREATE TABLE invitation"
                + "(invitation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", event_id INTEGER"
                + ", guest_id INTEGER"
                + ", admin_id INTEGER"
                + ", FOREIGN KEY (event_id) REFERENCES event(event_id)"
                + ", FOREIGN KEY (guest_id) REFERENCES guest(guest_id)"
                + ", FOREIGN KEY (admin_id) REFERENCES admin(admin_id)"
                +")";
        
        //rsvp table
        String createRSVP = "CREATE TABLE rsvp"
                + "(rsvp_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                + ", invitation_id INTEGER"
                + ", accepted INTEGER NOT NULL"
                + ", dietary_requirements TEXT"
                + ", rsvp_datetime DATE NOT NULL"
                + ", FOREIGN KEY (invitation_id) REFERENCES invitation(invitation_id)"
                + ")";
        
        ArrayList<String> checkTableNames = new ArrayList<String>();
        checkTableNames.add("Admin");
        checkTableNames.add("Guest");
        checkTableNames.add("Event");
        checkTableNames.add("Invitation");
        checkTableNames.add("RSVP");
        
        openConnection();
        DatabaseMetaData dbmd = DatabaseManager.sharedConnection.getMetaData();
        Statement st = sharedConnection.createStatement();
        
        for (String tableName : checkTableNames){
            System.out.println("Checking " + tableName + " table...");
            try {
                // Check if the Table exists
               ResultSet rs = dbmd.getTables(null, null, tableName, null);
                if (!rs.next()) {
                    // create table if doesnt exist
                    switch (tableName){
                        case "Admin":
                            st.execute(createAdmin);
                            System.out.println("Admin table created");
                            break;
                        case "Guest":
                            st.execute(createGuest);
                            System.out.println("Guest table created");
                            break;
                        case "Event":
                            st.execute(createEvent);
                            System.out.println("Event table created");
                            break;
                        case "Invitation":
                            st.execute(createInvitation);
                            System.out.println("Invitation table created");
                            break;
                        case "RSVP":
                            st.execute(createRSVP);
                            System.out.println("RSVP table created");
                            break;          
                    }
                } else {
                    System.out.println(tableName + " table exists");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DatabaseManager.closeConnection();
        st.close();
    }
    
    public static boolean fetchAccessCode(String accessCode) {
        boolean accessCodeFound = false;
        DatabaseManager.openConnection();
        try {
            String sqlString = "SELECT * FROM guest" 
                    + " WHERE access_code = ?";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            psmt.setString(1, accessCode);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                accessCodeFound = true;
                Guest guestLoggedin = new Guest(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email_address"), rs.getString("phone_number"));
                guestLoggedin.setAccessCode(rs.getString("access_code"));
                
                App.setCurrentUser(guestLoggedin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
            return accessCodeFound;
        }
    }
    
    
    public static boolean createNewInvite(Invitation invite) throws SQLException{
        boolean addedInvite = false;
        
        DatabaseManager.openConnection();
        Statement st = sharedConnection.createStatement();
        try {
            String insertQuery = "INSERT INTO invitation (event_id, guest_id, admin_id)"
                    + "VALUES (?, ?, ?);";
            PreparedStatement ps = sharedConnection.prepareStatement(insertQuery);
            ps.setInt(1, getEventID(invite.getEvent()));
            ps.setInt(2, getGuestID(invite.getGuest()));
            ps.setInt(3, getAdminID(invite.getAdmin()));
            ps.executeUpdate();
            addedInvite = true;
            System.out.println(invite.getGuest().getAccessCode() + " has been invited to " + invite.getEvent().getEventName() + " by "
            + invite.getAdmin().getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            DatabaseManager.closeConnection();
            return addedInvite;
        }
    }
    
    public static boolean addNewGuest(Guest newGuest) throws SQLException{
        boolean addedGuest = false;
        //Guest newGuest = new Guest(fName, lName, phoneNumber, email);
        //String newAccessCode = newGuest.generateAccessCode(fName, lName);
        System.out.println(newGuest.getFName());
        System.out.println(newGuest.getLName());
        System.out.println(newGuest.getEmail());
        System.out.println(newGuest.getPhoneNumber());
        System.out.println(newGuest.getAccessCode());
        DatabaseManager.openConnection();
        Statement st = sharedConnection.createStatement();
        try {
            String insertQuery = "INSERT INTO guest (access_code, email_address, phone_number, first_name, last_name)"
                    + "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = sharedConnection.prepareStatement(insertQuery);
            ps.setString(1, newGuest.getAccessCode());
            ps.setString(2, newGuest.getEmail());
            ps.setString(3, newGuest.getPhoneNumber());
            ps.setString(4, newGuest.getFName());   
            ps.setString(5, newGuest.getLName());
            ps.executeUpdate();
            addedGuest = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            DatabaseManager.closeConnection();
            return addedGuest;
        }
    }
    
    public static boolean addNewEvent(String eventName, String location) throws SQLException{
        boolean addedEvent = false;
        Event newEvent = new Event(eventName, location);
        System.out.println(newEvent.getEventName());
        System.out.println(newEvent.getLocation());
        DatabaseManager.openConnection();
        Statement st = sharedConnection.createStatement();
        try {
            String insertQuery = "INSERT INTO event (event_name, location)"
                    + "VALUES (?, ?);";
            PreparedStatement ps = sharedConnection.prepareStatement(insertQuery);
            ps.setString(1, newEvent.getEventName());
            ps.setString(2, newEvent.getLocation());
            ps.executeUpdate();
            addedEvent = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st.close();
            DatabaseManager.closeConnection();
            return addedEvent;
        }
    }
    
    
    public static boolean checkAdminLogin (String username, String password) throws SQLException{
        DatabaseManager.openConnection();
        boolean userAuthenticated = false;
        try{
          ResultSet rs;
          String sqlString = "SELECT * FROM admin where username = ? AND password = ?";
          PreparedStatement ps = sharedConnection.prepareStatement(sqlString);
          ps.setString(1, username);
          ps.setString(2, password);
          rs = ps.executeQuery();
          
          if(rs.next()){
            userAuthenticated = true;
            Admin adminLoggedin = new Admin(rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"));
            App.setCurrentUser(adminLoggedin);
          }
        }catch (SQLException e){
            System.out.print("Admin user could not be authenticated");
        }finally{
            DatabaseManager.closeConnection();
            return userAuthenticated;
        }
    }
    
    public static int getEventID (Event event) throws SQLException{
        DatabaseManager.openConnection();
        int eventID= 0;
        try{
          ResultSet rs;
          String sqlString = "SELECT event_id FROM event where event_name = ? AND location = ?";
          PreparedStatement ps = sharedConnection.prepareStatement(sqlString);
          ps.setString(1, event.getEventName());
          ps.setString(2, event.getLocation());
          rs = ps.executeQuery();
          
          if(rs.next()){
           eventID = rs.getInt("event_id");
          }
        }catch (SQLException e){
            System.out.print("Event could not be found!");
        }finally{
            DatabaseManager.closeConnection();
            return eventID;
        }
    }
    
public static int getGuestID (Guest guest) throws SQLException{
        DatabaseManager.openConnection();
        //boolean eventFound = false;
        Integer guestID = null;
        try{
          ResultSet rs;
          String sqlString = "SELECT guest_id FROM guest WHERE access_code = ? AND first_name = ? AND last_name = ?";
          PreparedStatement ps = sharedConnection.prepareStatement(sqlString);
          ps.setString(1, guest.getAccessCode());

          ps.setString(2, guest.getFName());

          ps.setString(3, guest.getLName());

          rs = ps.executeQuery();
          
          if(rs.next()){
            guestID = rs.getInt("guest_id");
          }
          System.out.println("Guest ID = " + guestID);
        }catch (SQLException e){
            System.out.println("Guest ID could not be found!");
        }finally{
            DatabaseManager.closeConnection();
            return guestID;
            //return eventFound;
        }
    }
    
    public static int getAdminID (Admin admin) throws SQLException{
        DatabaseManager.openConnection();
        Integer adminID = null;
        try{
          ResultSet rs;
          String sqlString = "SELECT admin_id FROM admin WHERE username = ? AND password = ? "
                  + "AND first_name = ? AND last_name = ?";
          PreparedStatement ps = sharedConnection.prepareStatement(sqlString);
          ps.setString(1, admin.getUsername());
          ps.setString(2, admin.getPassword());
          ps.setString(3, admin.getFName());
          ps.setString(4, admin.getLName());

          rs = ps.executeQuery();
          
          if(rs.next()){
            adminID = rs.getInt("admin_id");
          }
          System.out.println("Admin ID = " + adminID);
        }catch (SQLException e){
            System.out.println("Admin ID could not be found!");
        }finally{
            DatabaseManager.closeConnection();
            return adminID;
            //return eventFound;
        }
    }
    
    public static boolean deleteGuest (Guest guest) throws SQLException{
        DatabaseManager.openConnection();
        boolean guestDeleted = false;

        try{
            int deletedGuestID = DatabaseManager.getGuestID(guest);
            System.out.println("Guest ID = " + deletedGuestID);
            DatabaseManager.openConnection();
            String deleteString = "DELETE FROM guest WHERE guest_id = ?";
            PreparedStatement ps = sharedConnection.prepareStatement(deleteString);
            ps.setInt(1, deletedGuestID);
            ps.executeUpdate();
            guestDeleted = true;
        }catch (SQLException e){
            System.out.println("Guest could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
            return guestDeleted;
            //return eventFound;
        }
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
