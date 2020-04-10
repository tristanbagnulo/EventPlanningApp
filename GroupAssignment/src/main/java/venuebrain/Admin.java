/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Natalie Chan
 */
public class Admin extends User{
    private String username;
    private String password;
    private String [][] logindet = {{"admin1","adminpass1"},{"admin2","adminpass2"}};
    
   /* public Admin (String username, String password){
        this.username = username;
        this.password = password;
    }*/
    
    public String authentication(){
        if((username.equals(logindet [0][0]) && password.equals(logindet [0][1]))){
            return "Login Successful";
        }
        else{
            return "Login Failure";
        }
    }
    
    public Admin (String fname, String lname, String username, String password){
        super (fname, lname);
        this.username = username;
        this.password = password;
    }
    
    public String getUsername (){
        return username;
    }
    
    public void setUsername (String username){
        this.username = username;
    }
    
    public String getPassword (){
        return password;
    }
    
    public void setPassword (){
        this.password = password;
    }
    
    public String createAdminAccess (String username, String password){
        return (username + password);
    }
}