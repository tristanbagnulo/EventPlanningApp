package venuebrain;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class adminLoginController {
   
    @FXML
    Button backButton;
    
    @FXML
    Button loginButton;
    
    @FXML
    TextField adminUsername;
    
    @FXML
    PasswordField adminPassword;
    
    @FXML
    Label invalidLogin;
    
     @FXML
    protected void initialize() {
        invalidLogin.setVisible(false);
    }
    
    @FXML
    private void btnLoginClicked() throws IOException, SQLException {
        System.out.println("Login Clicked");
        String enteredUser = adminUsername.getText();
        String enteredPass = adminPassword.getText();
        if (DatabaseManager.checkAdminLogin(enteredUser, enteredPass)){
            System.out.println("Admin authentication was successful.");
            invalidLogin.setVisible(false);
            App.setDashboardRoot("adminDashboard", 950, 640);
        }else {
            invalidLogin.setVisible(true);
        }
    }
    

    @FXML
    private void switchToPrimary() throws IOException, SQLException {
        //App.setRoot("primary");
        
        //testing addNewGuest method
         if(DatabaseManager.addNewGuest("T''est", "Guest", "testguest@mail.com", "04983247")){
            System.out.println("guest added");
        } 
    }
    
      @FXML
    private void btnBackWasClicked() throws IOException, SQLException {
        App.setLoginRoot("primary");
    }
}