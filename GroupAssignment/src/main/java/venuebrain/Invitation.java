/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;


public class Invitation{
    
    private Event event;
    private Guest guest;
    private Admin admin;

    public Invitation(Event event, Guest guest, Admin admin){
       this.event = event;
       this.guest = guest;
       this.admin = admin;
    }
    
    public Invitation(){

    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public Guest getGuest() {
        return guest;
    }
    
    public void setGuest (Guest guest) {
        this.guest = guest;
    }
    
    public Admin getAdmin() {
        return admin;
    }
    
    public void setAdmin (Admin admin) {
        this.admin = admin;
    }
}
