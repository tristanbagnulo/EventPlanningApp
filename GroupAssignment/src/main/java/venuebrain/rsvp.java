
package venuebrain;

/**
 *
 * @author Shai C
 */
public class rsvp {
    private String invitationId;
    private String guestId;
    private int accepted; 
    private String dietaryRequirements;
    private String date;
    
    public rsvp(String invitationId, String guestId, String date){
        this.invitationId = invitationId;
        this.guestId = guestId;
        this.date = date;
    }
    
    public rsvp(int accepted){
        this.accepted = accepted;
    }
    
    public String getInvitationId() {
        return invitationId;
    }
    public String getGuestId() {
        return guestId;
    }
    
    public int getAccepted(){
        return accepted;
    }
    
    public String getDietaryRequirements() {
        return dietaryRequirements;
    }
    public String getDate(){
        return date;
    }
    
    
    
    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }
     public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
     
     public void setAccetped(int accepted) {
         this.accepted = accepted;
     }
     
    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }
    public void setDate(String date){
        this.date = date;
    }
}