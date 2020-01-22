import java.util.Collection;

/**
 * Created by njg77 on 2/27/2017.
 * A generic interface used by the two databases in SIS, CourseDB and UserDB.
 */
public interface DB<K,V> {
    /**Add a value entry to the database in constant time. The database will determine the key based on the value type.
     *
     * @param value- the value to add
     * @return the previous value associated with the key, otherwise null
     */
    V addValue(V value);

    /**Get the value for an associated key in constant time.
     *
     * @param key- the key
     * @return the value that is associated with the key, or null if not present
     */
    V getValue(K key);

    /**Indicates whether a key is in the database or not, in constant time.
     *
     * @param key- the key to search for
     * @return whether the key is present or not
     */
    boolean hasKey(K key);

    /**Get all the values in the database in linear time.
     *
     * @return all the values
     */
    Collection<V> getAllValues();
}
