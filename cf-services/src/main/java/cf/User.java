package cf;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
public class User {
    String Email, Password, Id, CartId, RegistrationDate, LastVisit;
    UserName Name;
    Date Dob;
    UserPhone Phone;
    List Addresses = new ArrayList<UserAddress>();
    List Orders = new ArrayList<String>();

    User() {

    }

    User(UserName name, Date dob, String registrationDate, String lastVisit, UserPhone phone, List<UserAddress> addresses, List<String> orders, String email, String password, String cartId){
        this.Name = name;
        this.Dob = dob;
        this.RegistrationDate = registrationDate;
        this.LastVisit = lastVisit;
        this.Phone = phone;
        this.Addresses = addresses;
        this.Orders = orders;
        this.Email = email;
        this.Password = password;
        this.CartId = cartId;
    }

    public final DBObject toDBObject () {
        return new BasicDBObject("Name", this.getNameDBObject())
                .append("DOB", this.getDobDBObject())
                .append("RegistrationDate", this.getRegistrationDate())
                .append("LastVisit", this.getLastVisit())
                .append("Phone", this.getPhoneDBObject())
                .append("Addresses", this.getAddressList())
                .append("Orders", this.getOrders())
                .append("Email", this.getEmail())
                .append("Password", this.getPassword())
                .append("ID", this.getId())
                .append("CartId", this.getCartId());
    }

    public String getLastVisit() {
        return LastVisit;
    }

    public void setLastVisit(String lastVisit) {
        LastVisit = lastVisit;
    }

    public String getRegistrationDate() {

        return RegistrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

    public List getOrders() {
        return Orders;
    }

    public DBObject getOrdersDBObject() {

        return new BasicDBObject("Orders", Orders);
    }

    public void setOrders(List orders) {
        Orders = orders;
    }

    public List getAddresses() {

        return Addresses;
    }

    public List<DBObject> getAddressList(){
        List<DBObject> DBObjectList = new ArrayList<DBObject>();
        //DBObject dbObject = new BasicDBObject();
        //String s = "Address";
        //int i=1;
        for(Object adrs : Addresses){
            DBObjectList.add(((UserAddress) adrs).toDBObject());
            //dbObject.put(s+Integer.toString(i),((UserAddress) adrs).toDBObject());
        }
        return DBObjectList;
    }

    public void setAddresses(List addresses) {
        Addresses = addresses;
    }

    public UserPhone getPhone() {

        return Phone;
    }

    public DBObject getPhoneDBObject() {

        return Phone.toDBObject();
    }

    public void setPhone(UserPhone phone) {
        Phone = phone;
    }

    public Date getDob() {

        return Dob;
    }

    public DBObject getDobDBObject(){

        return Dob.toDBObject();
    }

    public void setDob(Date dob) {
        Dob = dob;
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

    public String getCartId() {

        return CartId;
    }

    public void setCartId(String cartId) {
        CartId = cartId;
    }

    public String getId() {

        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {

        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
