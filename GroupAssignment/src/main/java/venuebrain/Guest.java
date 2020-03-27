/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;


import java.util.Random;

public class Guest extends User{
    
    private String uniqueLoginID;

    public Guest(String fName, String lName) {
        super(fName, lName);
    }
    
    public String createGuestLoginID(String fName, String lName) {
        String newFName = removeSpecial(fName);
        String newLName = removeSpecial(lName);
        Random rand = new Random();
        String randID = String.format("%04d", rand.nextInt(10000));
        String resultStr = newFName + newLName + randID;
        this.uniqueLoginID = resultStr;
        return resultStr;
    }
    
    public String removeSpecial(String str){
        String resultStr = "";
        for(int i = 0; i < str.length(); i++){
            if (str.charAt(i)>64 && str.charAt(i)<=122){
                resultStr = resultStr + str.charAt(i);
            }
        }
        return resultStr;
    }
    
    public String getGuestLoginID() {
        return uniqueLoginID;
    }
}
