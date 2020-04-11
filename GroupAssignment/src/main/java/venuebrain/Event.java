/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;


public class Event{
    
    private String eventName;
    private String location;

    public Event(String eventName, String location) {
        this.eventName = eventName;
        this.location = location;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation (String location) {
        this.location = location;
    }
}
