import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by njg77 on 2/27/2017.
 */
public class UserDB implements DB<String, User> {

    private HashMap<String, User> users;

    /**Create the user database.
     *
     */
    public UserDB(){
        users = new HashMap<>();
    }

    /**Add a value entry to the database in constant time. The database will determine the key based on the value type.
     *
     * @param user- the value to add
     * @return the previous value associated with the key, otherwise null
     */
    @Override
    public User addValue(User user) {
        String key = user.getUsername();
        User previousVal= null;
        if(users.containsKey(key)){
            previousVal = users.get(key);
        }
        users.put(key,user);
        return previousVal;
    }

    /**Get the value for an associated key in constant time.
     *
     * @param key- the key
     * @return the value that is associated with the key, or null if not present
     */
    @Override
    public User getValue(String key) {
        return users.get(key);
    }

    /**Indicates whether a key is in the database or not, in constant time.
     *
     * @param key- the key to search for
     * @return whether the key is present or not
     */
    @Override
    public boolean hasKey(String key) {
        return users.containsKey(key);
    }

    /**Get all the values in the database in linear time.
     *
     * @return all the values
     */
    @Override
    public Collection<User> getAllValues() {
        Collection<User> userList = new ArrayList<User>();
        for(Map.Entry<String, User> x:users.entrySet()){
            userList.add(x.getValue());
        }
        return userList;
    }
}

