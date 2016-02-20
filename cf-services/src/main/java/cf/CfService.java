package cf;

import com.mongodb.*;
import org.json.simple.JSONObject;
import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
public interface CfService {

    public List<DBObject> find(DBObject query) throws Exception;

    public void addToDB(DBObject customer) throws Exception;

    public WriteResult removeFromDB(DBObject query) throws Exception;

    public WriteResult addAddress(DBObject query, UserAddress address) throws Exception;

    public WriteResult addNewOrder(DBObject query, String orderId) throws Exception;

    public WriteResult updatePassword(DBObject query, String password) throws Exception;
}
