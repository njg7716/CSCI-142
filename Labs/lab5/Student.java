import java.util.Comparator;

/**
 * Created by njg77 on 2/28/2017.
 */
public class Student extends User implements Comparable<User> {
    /**Create a new student. Here you want to pass to the parent, User, a comparator that orders the courses ascending by course name.
     *
     * @param username- the username
     */
    public Student(String username) {
        super(username, UserType.STUDENTS, ((o1, o2) -> o1.getName().compareTo(o2.getName())));
    }
}
