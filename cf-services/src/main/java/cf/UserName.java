package cf;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Armaan on 2/17/2016.
 */
public class UserName {
    String Initial, FirstName, MiddleName, LastName;

    UserName(String initial, String firstName, String middleName, String lastName){
        this.Initial = initial;
        this.FirstName = firstName;
        this.MiddleName = middleName;
        this.LastName = lastName;
    }

    public DBObject toDBObject() {

        return new BasicDBObject("Initial",Initial)
                .append("FirstName", FirstName)
                .append("MiddleName", MiddleName)
                .append("LastName", LastName);
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {

        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getFirstName() {

        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getInitial() {

        return Initial;
    }

    public void setInitial(String initial) {
        Initial = initial;
    }

}
