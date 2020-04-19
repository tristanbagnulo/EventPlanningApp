
package venuebrain;

/**
 *
 * @author Shai C
 */
public class rsvp {
    private int invitationId;
    private int guestId;
    private String dietaryRequirements;
    private String date;
    
    public rsvp(int invitationId, int guestId, String date){
        this.invitationId = invitationId;
        this.guestId = guestId;
        this.date = date;
    }
    
    public int getInvitationId() {
        return invitationId;
    }
    public int getGuestId() {
        return guestId;
    }
    public String getDietaryRequirements() {
        return dietaryRequirements;
    }
    public String getDate(){
        return date;
    }
    
    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }
     public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }
    public void setDate(String date){
        this.date = date;
    }
}