package cf;

import com.mongodb.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
public interface CfService {

    public User createUser(JSONObject user);

    public JSONObject findById(String id) throws Exception;

    public void addToDB(JSONObject customer) throws Exception;

    public WriteResult removeFromDB(String id) throws Exception;

    public WriteResult addAddress(String id, JSONObject address) throws Exception;

    public WriteResult addNewOrder(String id, String orderId) throws Exception;

    public WriteResult updatePassword(String id, String password) throws Exception;
}
