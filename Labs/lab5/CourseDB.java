import java.util.*;

/**
 * Created by njg77 on 2/27/2017.
 * Storage of all courses where the key is the unique course id and the value is the associated Course object.
 */
public class CourseDB implements DB<Integer,Course> {

    private HashMap<Integer, Course> courses;

    /**
     *
     */
    public CourseDB(){
        courses = new HashMap<>();
    }

    /**Add a value entry to the database in constant time. The database will determine the key based on the value type.
     *
     * @param course- the value to add
     * @return the previous value associated with the key, otherwise null
     */
    @Override
    public Course addValue(Course course) {
        int key = course.getId();
        Course prevCourse;
        if(courses.containsKey(key)){
            prevCourse = courses.get(key);
        }
        else{
            prevCourse = null;
        }
        courses.put(key,course);
        return prevCourse;
    }

    /**Get the value for an associated key in constant time.
     *
     * @param key- the key
     * @return the value that is associated with the key, or null if not present
     */
    @Override
    public Course getValue(Integer key) {
        return courses.get(key);
    }

    /**Indicates whether a key is in the database or not, in constant time.
     *
     * @param key- the key to search for
     * @return whether the key is present or not
     */
    @Override
    public boolean hasKey(Integer key) {
        return courses.containsKey(key);
    }

    /**Get all the values in the database in linear time.
     *
     * @return all the values
     */
    @Override
    public Collection<Course> getAllValues() {
        Collection<Course> courseList = new ArrayList<Course>();
        for(Map.Entry<Integer, Course> x:courses.entrySet()){
            courseList.add(x.getValue());
        }
        return courseList;
    }
}
