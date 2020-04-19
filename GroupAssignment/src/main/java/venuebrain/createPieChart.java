/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain; 
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Natalie Chan
 */
        

public class createPieChart extends Application{
    
    //Count for Accepted RSVP
   int countAccepted;
   
   //Count for Rejected RSVP
   int countRejected;
   
   //Count for Pending RSVP
   int countPending;
   
   
    public void initialize() throws IOException, SQLException {
        
    @Override
       public void start (Stage stage){
        
        //ObservableList Object prepared
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              new PieChart.Data ("Accepted", countAccepted),
              new PieChart.Data("Declined", countRejected));
              new PieChart.Data("Declined", countPending)); 
        
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
    }
    //Launch Application
     public static void main (String args[]){
         launch(args);
     }
     
     
    public static int countAccepted () throws SQLException{

        try{
            
            //Get accepted (int) from database
            DatabaseManager.openConnection();
            
            //Query for taking RSVP accepted for the event from the Database            
            String acceptedQuery = "SELECT accepted FROM rsvp;Join invitation using invitation_id where event_id = ?";
            Statement st = DatabaseManager.sharedConnection.createStatement();
            ResultSet rs = st.executeQuery(acceptedQuery);
            
            if(rs.next()) {
                if (accepted == 1)
            {
                //Counting RSVP Accepted
                countAccepted++;
            } else if (accepted == 2)
            {
                //Counting RSVP Rejected
                countRejected++;
            } else (accepted == 3)
            {
                //Count RSVP Pending
                countPending++;
            }            
        }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
  
    }
    
 
