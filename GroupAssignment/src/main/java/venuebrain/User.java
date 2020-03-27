/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;


public class User {
    
    private String fName;
    private String lName;

    public User(String fName, String lName) {
        this.fName = fName;
        this.lName= fName;
    }

    public String getGuestFName() {
        return fName;
    }

    public void setGuestFName(String fName) {
        this.fName = fName;
    }
    
     public String getlName() {
        return lName;
    }
     
    public void setGuestLName(String lName) {
        this.lName = lName;
    }
    
}
