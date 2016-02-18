package cf;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Armaan on 2/17/2016.
 */
public class UserPhone {
    String CountryCode, Number;

    UserPhone(String countryCode, String number){
        this.CountryCode = countryCode;
        this.Number = number;
    }

    public DBObject toDBObject() {

        return new BasicDBObject("CountryCode", CountryCode)
                .append("Number", Number);
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCountryCode() {

        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }
}
