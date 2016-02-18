package cf;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Armaan on 2/17/2016.
 */
public class UserAddress {
    UserName Name;
    String AddressLine1;
    String AddressLine2;
    String AddressLine3;
    String Pin;
    String Phone;

    UserAddress(UserName name, String addressLine1, String addressLine2, String addressLine3, String pin, String phone){
        this.Name = name;
        this.AddressLine1 = addressLine1;
        this.AddressLine2 = addressLine2;
        this.AddressLine3 = addressLine3;
        this.Pin = pin;
        this.Phone = phone;
    }

    public final DBObject toDBObject () {
        return new BasicDBObject("Name", this.getNameDBObject())
                .append("AddressLine1", this.getAddressLine1())
                .append("AddressLine2", this.getAddressLine2())
                .append("AddressLine3", this.getAddressLine3())
                .append("Pin", this.getPin())
                .append("Phone", this.getPhone());
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public UserName getName() {

        return Name;
    }

    public DBObject getNameDBObject() {

        return Name.toDBObject();
    }

    public void setName(UserName name) {
        Name = name;
    }

    public String getPhone() {

        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        AddressLine3 = addressLine3;
    }

    public String getAddressLine2() {

        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getAddressLine1() {

        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }
}
