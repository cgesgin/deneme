 
package entity;
 
import java.util.Date;

public class Actor {

    private int actorId;
    private String firstName;
    private String LastName;
    private Date lastUpdate;
            
    public Actor() {
    }

    public Actor(int actorId, String firstName, String LastName, Date lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.LastName = LastName;
        this.lastUpdate = lastUpdate;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.actorId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (this.actorId != other.actorId) {
            return false;
        }
        return true;
    }
    
    
    
}
