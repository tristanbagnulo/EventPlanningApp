package venuebrain;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import static venuebrain.DatabaseManager.sharedConnection;

public class guestManagerController {

  
    @FXML
    Button deleteGuest;
    
    @FXML
    Button addGuest;
    
    @FXML
    Button generateCode;
    
    @FXML
    Label newAccessCodeLbl;
    
    @FXML
    Label invalidCreds;
    
    @FXML
    Label noSelection;
    
    @FXML
    TextField firstNameTxt;
    
    @FXML
    TextField lastNameTxt;
    
    @FXML
    TextField emailTxt;
    
    @FXML
    TextField phoneTxt;
    
    @FXML
    TableView guestTable = new TableView();
    
//    Initialise the TableColumns as FXML variables
   @FXML
   TableColumn<Guest, String> guestfNameCol = new TableColumn<>("First Name");
   
    @FXML
   TableColumn<Guest, String> guestlNameCol = new TableColumn<>("Last Name");
    
    @FXML
    TableColumn<Guest, String> guestACodeCol = new TableColumn<>("Access Code");
    
    @FXML
    TableColumn<Guest, String> guestEmailCol = new TableColumn<>("Email");
    
    @FXML
    TableColumn<Guest, String> guestPhoneCol = new TableColumn<>("Phone Number");
    
    Guest newGuest;
    
 
    @FXML
    public void initialize() throws IOException, SQLException {
      
        invalidCreds.setVisible(false);
        addGuest.setVisible(false);
        noSelection.setVisible(false);
        newAccessCodeLbl.setText("");
        
        guestfNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableFName());
        guestlNameCol.setCellValueFactory(cellData -> cellData.getValue().getViewableLName());
        guestACodeCol.setCellValueFactory(cellData -> cellData.getValue().getViewableAccessCode());
        guestEmailCol.setCellValueFactory(cellData -> cellData.getValue().getViewableEmail());
        guestPhoneCol.setCellValueFactory(cellData -> cellData.getValue().getViewablePhone());
     
        guestTable.setItems(getGuestListData());
        
        guestTable.setEditable(true);
        guestfNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        guestlNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        guestACodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        guestEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        guestPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        
    }
    
    private ObservableList<Guest> getGuestListData() {
        List<Guest> guestListToReturn = new ArrayList<>();
        try {
            
            // Get the guest list from the database
            DatabaseManager.openConnection();
            String eventListQuery = "SELECT * FROM guest;";
            Statement st = DatabaseManager.sharedConnection.createStatement();
            ResultSet rs = st.executeQuery(eventListQuery);
            
            while(rs.next()) {
                Guest listGuest = new Guest(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email_address"), rs.getString("phone_number"));
                listGuest.setAccessCode(rs.getString("access_code"));
                
                guestListToReturn.add(
                    listGuest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
       return FXCollections.observableArrayList(guestListToReturn);
    }
    
    @FXML
    private void btnGenerateCode() throws IOException, SQLException {
        
        if(firstNameTxt.getText().isEmpty() || lastNameTxt.getText().isEmpty() || emailTxt.getText().isEmpty() || phoneTxt.getText().isEmpty()){
            invalidCreds.setVisible(true);
            
        }else{
            newGuest = new Guest(firstNameTxt.getText(), lastNameTxt.getText(), emailTxt.getText(), phoneTxt.getText());
            String newGuestCode = newGuest.generateAccessCode(firstNameTxt.getText(), lastNameTxt.getText());
            newAccessCodeLbl.setText(newGuestCode);
            addGuest.setVisible(true);
            invalidCreds.setVisible(false);
            
            System.out.println(newGuest.getFName());
            System.out.println(newGuest.getLName());
            System.out.println(newGuest.getAccessCode());
        }
    }
    
    @FXML
    private void btnAddGuest() throws IOException, SQLException {
        
        
        DatabaseManager.addNewGuest(newGuest);
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        newAccessCodeLbl.setText("");
        guestTable.setItems(getGuestListData());
        addGuest.setVisible(false);
    }
    
    @FXML
    private void btnDeleteGuest() throws IOException, SQLException {
        
        Guest deletedGuest = (Guest) guestTable.getSelectionModel().getSelectedItem();
        //DatabaseManager.getGuestID(deletedGuest); -> working
        
       if(DatabaseManager.deleteGuest(deletedGuest)){
            guestTable.setItems(getGuestListData());
            System.out.println("Guest deleted!");
            noSelection.setVisible(false);
        }else{
           noSelection.setVisible(true);
            System.out.println("Guest was not deleted!");
        }  
    }
    
    @FXML
    private void editGuestFirst(CellEditEvent edittedCell) throws IOException, SQLException {
        
        Guest selectedGuest = (Guest) guestTable.getSelectionModel().getSelectedItem();
        //DatabaseManager.getGuestID(deletedGuest); -> working
        
        String newFirstName = edittedCell.getNewValue().toString();
        
        DatabaseManager.openConnection();
        try{
            int selectedGuestID = DatabaseManager.getGuestID(selectedGuest);
            
            DatabaseManager.openConnection();
            String updateString = "UPDATE guest SET first_name = ?, "
                    + "access_code = ? "
                    + "WHERE guest_id = ?";
            selectedGuest.setFName(newFirstName);
            selectedGuest.generateAccessCode(selectedGuest.getFName(), selectedGuest.getLName());
            PreparedStatement ps = sharedConnection.prepareStatement(updateString);
            ps.setString(1, selectedGuest.getFName());
            ps.setString(2, selectedGuest.getAccessCode());
            ps.setInt(3, selectedGuestID);
            ps.executeUpdate();
            System.out.println("Guest ID = " + selectedGuestID + " first name set to " + selectedGuest.getFName() + " new "
                    + "access code = " + selectedGuest.getAccessCode());
        }catch (SQLException e){
            System.out.println("Guest could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
        } 
        guestTable.setItems(getGuestListData()); 
    }
    
    @FXML
    private void editGuestLast(CellEditEvent edittedCell) throws IOException, SQLException {
        
        Guest selectedGuest = (Guest) guestTable.getSelectionModel().getSelectedItem();
        //DatabaseManager.getGuestID(deletedGuest); -> working
        
        String newLastName = edittedCell.getNewValue().toString();
        
        DatabaseManager.openConnection();
        try{
            int selectedGuestID = DatabaseManager.getGuestID(selectedGuest);
            
            DatabaseManager.openConnection();
            String updateString = "UPDATE guest SET last_name = ?, "
                    + "access_code = ? "
                    + "WHERE guest_id = ?";
            selectedGuest.setLName(newLastName);
            selectedGuest.generateAccessCode(selectedGuest.getFName(), selectedGuest.getLName());
            PreparedStatement ps = sharedConnection.prepareStatement(updateString);
            ps.setString(1, selectedGuest.getLName());
            ps.setString(2, selectedGuest.getAccessCode());
            ps.setInt(3, selectedGuestID);
            ps.executeUpdate();
            System.out.println("Guest ID = " + selectedGuestID + " last name set to " + selectedGuest.getLName() + " new "
                    + "access code = " + selectedGuest.getAccessCode());
        }catch (SQLException e){
            System.out.println("Guest could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
        } 
        guestTable.setItems(getGuestListData()); 
    }
    
     @FXML
    private void editGuestEmail(CellEditEvent edittedCell) throws IOException, SQLException {
        
        Guest selectedGuest = (Guest) guestTable.getSelectionModel().getSelectedItem();
        //DatabaseManager.getGuestID(deletedGuest); -> working
        
        String newEmail = edittedCell.getNewValue().toString();
        
        DatabaseManager.openConnection();
        try{
            int selectedGuestID = DatabaseManager.getGuestID(selectedGuest);
            
            DatabaseManager.openConnection();
            String updateString = "UPDATE guest SET email_address = ? WHERE guest_id = ?";
            selectedGuest.setEmail(newEmail);
            PreparedStatement ps = sharedConnection.prepareStatement(updateString);
            ps.setString(1, selectedGuest.getEmail());
            ps.setInt(2, selectedGuestID);
            ps.executeUpdate();
            System.out.println("Guest ID = " + selectedGuestID + " email set to " + selectedGuest.getEmail());
        }catch (SQLException e){
            System.out.println("Guest could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
        } 
        guestTable.setItems(getGuestListData()); 
    }
    
        @FXML
    private void editGuestPhone(CellEditEvent edittedCell) throws IOException, SQLException {
        
        Guest selectedGuest = (Guest) guestTable.getSelectionModel().getSelectedItem();
        //DatabaseManager.getGuestID(deletedGuest); -> working
        
        String newPhone = edittedCell.getNewValue().toString();
        
        DatabaseManager.openConnection();
        try{
            int selectedGuestID = DatabaseManager.getGuestID(selectedGuest);
            
            DatabaseManager.openConnection();
            String updateString = "UPDATE guest SET phone_number = ? WHERE guest_id = ?";
            selectedGuest.setPhoneNumber(newPhone);
            PreparedStatement ps = sharedConnection.prepareStatement(updateString);
            ps.setString(1, selectedGuest.getPhoneNumber());
            ps.setInt(2, selectedGuestID);
            ps.executeUpdate();
            System.out.println("Guest ID = " + selectedGuestID + " phone set to " + selectedGuest.getPhoneNumber());
        }catch (SQLException e){
            System.out.println("Guest could not be found!");
            System.out.println(e.getMessage());
        }finally{
            DatabaseManager.closeConnection();
        } 
        guestTable.setItems(getGuestListData()); 
    }
    
    
    
    
    
    
    /*@FXML
    private void switchToAdminLogin() throws IOException {
        App.setLoginRoot("adminLogin");
    }
    
    @FXML
    private void btnGuestWasClicked() throws IOException {
        
        App.setLoginRoot("guestLogin");
        
    }*/
}
