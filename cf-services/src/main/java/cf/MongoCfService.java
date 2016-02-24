package cf;

import com.mongodb.*;
import com.mongodb.util.JSON;
import com.sun.scenario.effect.PhongLighting;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.StringBasedMongoQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
@Service
@Transactional
public class MongoCfService implements CfService{

    DBCollection collection;
    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    MongoCfService() throws Exception{

        collection = mongoOperation.getCollection("Customers");
    }

    public User createUser(JSONObject userJson) {
        User user = new User();
        UserName name = new UserName(((String) userJson.getJSONObject("Name").get("Initial")),
                ((String) userJson.getJSONObject("Name").get("FirstName")),
                ((String) userJson.getJSONObject("Name").get("MiddleName")),
                ((String) userJson.getJSONObject("Name").get("LastName")));
        user.setName(name);
        Date dob = new Date((String) (userJson.getJSONObject("Dob").get("Date") ),
                (String) (userJson.getJSONObject("Dob").get("Month") ),
                (String) (userJson.getJSONObject("Dob").get("Year") ));
        user.setDob(dob);
        user.setRegistrationDate((String) userJson.get("RegistrationDate"));
        user.setLastVisit((String) userJson.get("LastVisit"));
        UserPhone phone = new UserPhone(((String) userJson.getJSONObject("Phone").get("CountryCode")),
                ((String) userJson.getJSONObject("Phone").get("Number")));
        user.setPhone(phone);
        JSONArray addresses = (JSONArray) userJson.get("Addresses");
        List<UserAddress> userAddresses = new ArrayList<UserAddress>();
        if (addresses != null) {
            int len = addresses.length();
            for (int i = 0; i < len; i++) {
                UserName userName = new UserName(((String) ( (JSONObject) addresses.get(i) ).getJSONObject("Name").get("Initial")),
                        ((String) ( (JSONObject) addresses.get(i)).getJSONObject("Name").get("FirstName")),
                        ((String) ( (JSONObject) addresses.get(i)).getJSONObject("Name").get("MiddleName")),
                        ((String) ( (JSONObject) addresses.get(i)).getJSONObject("Name").get("LastName")));
                UserPhone userPhone = new UserPhone(((String) ( (JSONObject) addresses.get(i)).getJSONObject("Phone").get("CountryCode")),
                        ((String) ( (JSONObject) addresses.get(i)).getJSONObject("Phone").get("Number")));
                userAddresses.add(new UserAddress(userName, (String) ( (JSONObject) addresses.get(i)).get("AddressLine1"),
                        (String) ( (JSONObject) addresses.get(i)).get("AddressLine2"), (String) ( (JSONObject) addresses.get(i)).get("AddressLine3"),
                        (String) ( (JSONObject) addresses.get(i)).get("Pin"), userPhone));
            }
        }
        user.setAddresses(userAddresses);
        JSONArray orders = (JSONArray) userJson.get("Orders");
        List<String> userOrders = new ArrayList<String>();
        if(orders!=null) {
            int len = orders.length();
            for(int i=0; i<len; i++) {
                userOrders.add( (String) orders.get(i) );
            }
        }
        user.setOrders(userOrders);
        user.setEmail( (String) userJson.get("Email"));
        user.setPassword( (String) userJson.get("Password"));
        user.setCartId( (String) userJson.get("CartId"));
        return user;
    }

    public JSONObject findById(String id) throws Exception {

        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBCursor cursor = collection.find(query);
        return new JSONObject(cursor.next().toString());
    }
    public JSONArray getAllUsers() throws Exception {
        DBCursor cursor = collection.find();
        return  new JSONArray(cursor.toArray());
    }

    public void addToDB(JSONObject userJson) throws Exception {

        User user = this.createUser(userJson);
        collection.insert(user.toDBObject());
    }

    public WriteResult removeFromDB(String id) throws Exception{
        /*
            JSONObject query : {
                field : value
            }
         */
        /*MongoClient mc = new MongoClient("localhost",27017);
        DB db = mc.getDB("mydb");
        DBCollection collection = db.getCollection("Customer");*/
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        return collection.remove(query);
    }

    public WriteResult addAddress(String id, JSONObject address) throws Exception{
        /*
            JSONObject address : {
            "AddressList" :{
                    "Name" : ""
                    "AddressLine1" : ""
                    "AddressLine2" : ""
                    "AddressLine3" : ""
                    "Phone no." : ""
                    "PinCode" : ""
                }
            }
        */
        /*
            JSONObject query : {
                field : value
            }

        */
        UserName userName = new UserName((String) address.getJSONObject("Name").get("Initial"),
                (String) address.getJSONObject("Name").get("FirstName"),
                (String) address.getJSONObject("Name").get("MiddleName"),
                (String) address.getJSONObject("Name").get("LastName"));
        UserPhone userPhone = new UserPhone((String) address.getJSONObject("Phone").get("CountryCode"),
                (String) address.getJSONObject("Phone").get("Number"));
        UserAddress userAddress = new UserAddress(userName, (String) address.get("AddressLine1"), (String) address.get("AddressLine2"), (String) address.get("AddressLine3"), (String) address.get("Pin"), userPhone);
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject Address = userAddress.toDBObject();
        DBObject newAddress = new BasicDBObject("Addresses",Address);
        DBObject dbo = new BasicDBObject("$addToSet",newAddress);
        return collection.update(query, dbo);
    }

    public WriteResult addNewOrder(String id, String orderId) throws Exception{
        /*
            JSONObject order : {
            "OrderList" : [ "", "", "" ]
            }
        */
        /*
            JSONObject query : {
                field : value
            }
         */
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject newOrder = new BasicDBObject("Orders",orderId);
        DBObject dbo = new BasicDBObject("$addToSet",newOrder);
        return collection.update(query,dbo);
    }

    public WriteResult updatePassword(String id, String password) throws Exception{
        /*
            JSONObject password : {
            "Password" : ""
            }
        */
        /*
            JSONObject query : {
                field : value
            }
         */
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject newPassword = new BasicDBObject("Password",password);
        DBObject dbo = new BasicDBObject("$set",newPassword);
        return collection.update(query,dbo);
    }
}
