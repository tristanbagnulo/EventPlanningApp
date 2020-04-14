/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
    
    String fName;
    private String lName;

    public User(String fName, String lName) {
        this.fName = fName;
        this.lName= lName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }
    
     public String getLName() {
        return lName;
    }
     
    public void setLName(String lName) {
        this.lName = lName;
    }
    
    public StringProperty getViewableFName() {
        StringProperty viewableFName = new SimpleStringProperty(fName);
        return viewableFName;
    }
    
    public StringProperty getViewableLName() {
        StringProperty viewableLName = new SimpleStringProperty(lName);
        return viewableLName;
    }
    
}
