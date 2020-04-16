/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event{
    
//    private int event_id;
    private String eventName;
    private String location;
    private String date_;

    public Event(String eventName, String location) {
        this.eventName = eventName;
        this.location = location;
    }

    public Event() {
        
    }
     
//    public int getEvent_id(){
//        return event_id;
//    }
//    public void setEvent_id(int event_id){
//        this.event_id = event_id;
//    }
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
    
    public String getDate(){
        return date_;
    }
    
    public void setDate(String date_){
        this.date_ = date_;
    }
    
    public StringProperty getViewableEventName() {
        StringProperty viewableEventName = new SimpleStringProperty(eventName);
        return viewableEventName;
    }
    
    public StringProperty getViewableLocation() {
        StringProperty viewableLocation = new SimpleStringProperty(location);
        return viewableLocation;
    }
     
    public StringProperty getViewableDate() {
        StringProperty viewableDate = new SimpleStringProperty(date_);
        return viewableDate;
    }

}
