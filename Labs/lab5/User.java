import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by njg77 on 2/27/2017.
 */
public class User implements Comparable<User> {

    public enum UserType{
        PROFESSOR, STUDENTS
    }

    private TreeSet<Course> courses;
    private User.UserType type;
    private String username;

    /**Create a new user.
     *
     * @param username- the username (must be unique)
     * @param type- the user type (professor or student)
     * @param comp- the comparator that the tree set, courses, is to use
     */
    public User(String username, User.UserType type, Comparator<Course> comp){
        this.username = username;
        this.type = type;
        this.courses = new TreeSet<Course>();

    }

    /**Get the username.
     *
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**Get the user type.
     *
     * @return user type
     */
    public User.UserType getType(){
        return type;
    }

    /**Add a course for this user. For a professor it means they add it to the courses they are teaching. If they are a student,
     * they are enrolling in the course.
     *
     * @param course- the course to remove
     * @return true if the student was removed from the course, false if the student was not in the course
     */
    public boolean addCourse(Course course) {
        if(type == UserType.PROFESSOR){
            course.addProfessor(username);
            courses.add(course);
            return true;
        }
        else if(type == UserType.STUDENTS){
            course.addStudent(username);
            courses.add(course);
            return true;
        }
        return false;
    }

    /**
     *
     * @param course
     * @return
     */
    public boolean removeCourse(Course course){
        if(type == UserType.PROFESSOR){
            if(course.getProfessor().contains(username)) {
                courses.remove(course);
                course.addProfessor(null);
                return true;
            }
        }
        else if(type == UserType.STUDENTS){
            course.removeStudent(username);
            courses.remove(course);
            return true;
        }
        return false;
    }

    /**Returns the courses the user is currently teaching or enrolled in.
     *
     * @return the courses
     */
    public Collection<Course> getCourses(){
            return courses;
    }

    /**Two users are equal if they have the same username.
     *
     * @param other- the other user to compare to
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof User){
            return username.equals(((User) other).getUsername());
        }
        else{
            return false;
        }
    }

    /**The hash code of a user is the hash code of the username.
     *
     * @return hash code
     */
    public int hashCode(){
        return username.hashCode();
    }

    /**Returns a string representation of the user in the format:
     *
     *User{username=USERNAME, type=TYPE, courses=COURSE_LIST}
     *
     *Here, COURSE_LIST is a list of courses, with brackets surrounding the comma separated entries,
     *which are the course names in ascending alphabetical order for students,
     *and first by course level and second by ascending alphabetical course name for professors.
     *
     * @return the formatted string
     */
    @Override
    public String toString(){
        return ("User{username = "+username+", type = "+ type+ ", courses = "+courses);
    }

    /**Users are naturally ordered alphabetically by username.
     *
     * @param o- the user to compare to
     * @return a value less than 0 if this username is less than other's username, 0 if the usernames are the same,
     * a value greater than 0 if this username is greater than other's username.
     */
    @Override
    public int compareTo(User o) {
        return username.compareTo(o.getUsername());
    }
}
