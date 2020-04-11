/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;


public class Invitation{
    
    private Event event;
    private Guest guest;

    public Invitation(Event event, Guest guest){
       this.event = event;
       this.guest = guest;
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
}
