package cf;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
public class MongoCfService implements CfService{

    DBCollection collection;
    MongoClient mc = new MongoClient("localhost",27017);
    DB db = mc.getDB("mydb");
    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    MongoCfService() throws Exception{

        collection = mongoOperation.getCollection("Customers");
    }

    public List<DBObject> find(DBObject query) throws Exception {

        DBCursor cursor = collection.find(query);
        return cursor.toArray();
    }

    public void addToDB(DBObject customer) throws Exception {

        collection.insert(customer);
    }

    public WriteResult removeFromDB(DBObject query) throws Exception{
        /*
            JSONObject query : {
                field : value
            }
         */
        /*MongoClient mc = new MongoClient("localhost",27017);
        DB db = mc.getDB("mydb");
        DBCollection collection = db.getCollection("Customer");*/
        return collection.remove(query);
    }

    public WriteResult addAddress(DBObject query, UserAddress address) throws Exception{
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
        DBObject Address = address.toDBObject();
        DBObject newAddress = new BasicDBObject("Addresses",Address);
        DBObject dbo = new BasicDBObject("$addToSet",newAddress);
        return collection.update(query, dbo);
    }

    public WriteResult addNewOrder(DBObject query, String orderId) throws Exception{
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
        DBObject newOrder = new BasicDBObject("Orders",orderId);
        DBObject dbo = new BasicDBObject("$addToSet",newOrder);
        return collection.update(query,dbo);
    }

    public WriteResult updatePassword(DBObject query, String password) throws Exception{
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
        DBObject newPassword = new BasicDBObject("Password",password);
        DBObject dbo = new BasicDBObject("$set",newPassword);
        return collection.update(query,dbo);
    }
}
