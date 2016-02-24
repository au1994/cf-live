package cf;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

        try{
            CfService cfs = new MongoCfService();
            JSONObject user = new JSONObject();
            JSONObject address1 = new JSONObject();
            JSONObject name1 = new JSONObject();
            JSONObject phone1 = new JSONObject();
            JSONObject address2 = new JSONObject();
            JSONObject name2 = new JSONObject();
            JSONObject phone2 = new JSONObject();

            name1.put("Initial","Mr.");
            name1.put("FirstName","zxcvb");
            name1.put("MiddleName","K.");
            name1.put("LastName","tebtvMarwaha");

            phone1.put("CountryCode","+92");
            phone1.put("Number","967eve0976720");

            address1.put("Name",name1);
            address1.put("AddressLine1","P-601");
            address1.put("AddressLine2","Ljaipuri");
            address1.put("AddressLine3","Ghaziabad");
            address1.put("Pin","201014");
            address1.put("Phone",phone1);

            name2.put("Initial","Mr.");
            name2.put("FirstName","Asdfg");
            name2.put("MiddleName","K.");
            name2.put("LastName","Marwaha");



            phone2.put("CountryCode","+91");
            phone2.put("Number","9650909291");

            address2.put("Name",name2);
            address2.put("AddressLine1","F-1101");
            address2.put("AddressLine2","Ljaipuri");
            address2.put("AddressLine3","Goa");
            address2.put("Pin","201114");
            address2.put("Phone",phone2);

            user.put("Name",name1);

            JSONObject dob = new JSONObject();
            dob.put("Date","17");
            dob.put("Month","Jan");
            dob.put("Year","1947");
            user.put("Dob",dob);
            user.put("RegistrationDate","02040203");
            user.put("LastVisit","02040203");
            user.put("Phone", phone1);
            List<JSONObject> addresses = new ArrayList<JSONObject>();
            addresses.add(address1);addresses.add(address2);
            user.put("Addresses", addresses);
            List<String> orders = new ArrayList<String>();
            orders.add("order1");orders.add("order2");orders.add("order3");
            user.put("Orders",orders);
            user.put("Email", "email@gmail.com");
            user.put("Password", "pass1234");
            user.put("CartId","cart1101");
            /*UserName name1 = new UserName("Mr.", "Armaan", "K.", "Marwaha");
            UserName name2 = new UserName("Mr.", "Harsh", "K.", "Marwaha");
            UserAddress Address1 = new UserAddress(name1, "A-204", "LJaipuri", "Ghaziabad", "201014", "9670976720");
            UserAddress Address2 = new UserAddress(name2, "A-204", "LJaipuri", "Ghaziabad", "201014", "9650909291");
            UserPhone phone = new UserPhone("+91", "9670976720");
            Date dob = new Date("13", "03", "1994");
            Date registrationDate = new Date("1", "11", "2004");
            Date lastVisit = new Date("3", "08", "2014");
            List<UserAddress> addresses = new ArrayList<UserAddress>();
            addresses.add(Address1);addresses.add(Address2);
            List<String> orders = new ArrayList<String>();
            orders.add("order1");orders.add("order2");orders.add("order3");
            User me = new User(name1, dob, registrationDate, lastVisit, phone, addresses, orders, "email@gmail.com", "password", "cartId1123104");
            cfs.addToDB(me.toDBObject());*/

            //cfs.addAddress("56c64cc7d5d608eaae6203b8", address1);

            cfs.addToDB(user);
            /*JSONObject wr = cfs.findById("56c619dad5d6c480522f1cb9");
            System.out.println(wr.toString());*/
        }
        catch (Exception ex){
            System.out.println("Armaan"+ex.toString());
        }


    }
}
