/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//test commit from shai
package venuebrain;


import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guest extends User{
    
    private String accessCode;
    private String email;
    private String phoneNumber;

    public Guest(String fName, String lName, String email, String phoneNumber) {
        super(fName, lName);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
    //Guest ID creation
    public String generateAccessCode(String fName, String lName) {
        String newFName = removeSpecial(fName);
        String newLName = removeSpecial(lName);
        Random rand = new Random();
        String randID = String.format("%04d", rand.nextInt(10000));
        String resultStr = newFName + newLName + randID;
        this.accessCode = resultStr;
        return resultStr;
    }
    
    //removes special characters from string for Guest ID generation
    public String removeSpecial(String str){
        String resultStr = "";
        for(int i = 0; i < str.length(); i++){
            if (str.charAt(i) > 64 && str.charAt(i) <= 122){
                resultStr = resultStr + str.charAt(i);
            }
        }
        return resultStr;
    }
    
    //only for TableView
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
    
    public String getAccessCode() {
        return accessCode;
    }
    
     public StringProperty getViewableAccessCode() {
        StringProperty viewableAccessCode = new SimpleStringProperty(accessCode);
        return viewableAccessCode;
    }
    
    public StringProperty getViewableEmail() {
        StringProperty viewableEmail = new SimpleStringProperty(email);
        return viewableEmail;
    }
    
    public StringProperty getViewablePhone() {
        StringProperty viewablePhone = new SimpleStringProperty(phoneNumber);
        return viewablePhone;
    }
   
}
