/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Natalie Chan
 */
public abstract class CreatePieChart_RSVPController implements Initializable {
   
    @FXML
    Label eventIDLbl;
    
    @FXML
    Label rsvpAccepted;
    
    
    @FXML
    Label rsvpRejected;
    
    @FXML
    Label rsvpPending;
    
    @FXML
    PieChart rsvpPieChart;
    
    Event selectedEvent;
    
  public void initData(Event event) throws SQLException{
       
       selectedEvent = event;
       rsvpAccepted.setText(Integer.toString(countAccepted));
       rsvpRejected.setText(Integer.toString(countRejected));
       rsvpPending.setText(Integer.toString(countPending));
        
   }  
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() throws IOException, SQLException {
 
    }
    
     //Count for Accepted RSVP
   int countAccepted = 0;
   
   //Count for Rejected RSVP
   int countRejected = 0;
   
   //Count for Pending RSVP
   int countPending = 0;
  
   public void start (Stage stage){
        
        //ObservableList Object prepared
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              new PieChart.Data ("Accepted", countAccepted),
              new PieChart.Data("Declined", countRejected),
              new PieChart.Data("Pending", countPending)); 
        
             //Creating PieChart Object
              PieChart pieChart = new PieChart (pieChartData);
              
              //Setting Title of PieChart
              pieChart.setTitle("RSVP Confirmation");
              
              //Setting direction of slices in PieChart
              pieChart.setClockwise(true);
              
              //Setting PieChart labels
              pieChart.setLabelsVisible(true);
              
              //Setting PieChart Label extended Lines
              pieChart.setLabelLineLength(40);
              
              //Setting angle of PieChart
              pieChart.setStartAngle(180);
              
              //Creating Group Object
              Group root = new Group(pieChart);
              
              //Creating Scene Object - Passing Group Object and two parameters for height and width of the screen
              Scene scene = new Scene(root, 600, 300);
              
              //Setting Title of the Stage
              stage.setTitle("RSVP Respondant Distribution");
              
              //Adding the Scene to the Stage
              stage.setScene(scene);
              
              //Display Contents of the Stage
              stage.show(); 
  
        
    }
    
    //Launch Application
     public static void main (String args[]){
         launch(args);
     }
     
     
    private String countAccepted () throws SQLException{

        try{
            
            //Get accepted (int) from database
            DatabaseManager.openConnection();
            
            //Query for taking RSVP accepted for the event from the Database            
            String acceptedQuery = "SELECT accepted FROM rsvp;" + "Join invitation using invitation_id where event_id = ?";
            Statement st = DatabaseManager.sharedConnection.createStatement();
            ResultSet rs = st.executeQuery(acceptedQuery);
            
            if(rs.next()) {
                
                if (rs.getInt("accepted") == 1)
            {
                //Counting RSVP Accepted
                countAccepted++;
            }
                else if (rs.getInt("accepted") == 2)
            {
                //Counting RSVP Rejected
                countRejected++;
            } 
            else if(rs.getInt("accepted") == 3)
            {
                //Count RSVP Pending
                countPending++;
            }            
        }
        }
        catch (SQLException ex) {
            System.out.print("Chart could not be generated");
        }finally{
            DatabaseManager.closeConnection();
            return "RSVP: Accepted" + countAccepted + "RSVP: Rejected" + countRejected + "RSVP: Pending" +countPending; 
    }
    
    }
    
    private int getEventID() throws SQLException {
        int selectedEventID = DatabaseManager.getEventID(selectedEvent);
        DatabaseManager.openConnection();
        try {
            String eventIdQuery = "﻿SELECT event_id FROM invitation WHERE event_id = ?;";
            ResultSet rs;
            PreparedStatement ps = DatabaseManager.sharedConnection.prepareStatement(eventIdQuery);
            ps.setInt(1, selectedEventID);
            rs = ps.getResultSet();
            while(rs.next()) {
                Guest listGuest = new Guest(rs.getString("first_name"), rs.getString("last_name"), 
                        rs.getString("email_address"), rs.getString("phone_number"));
                listGuest.setAccessCode(rs.getString("access_code"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DatabaseManager.closeConnection();
        }
        
       
       return selectedEventID;
    }
}


