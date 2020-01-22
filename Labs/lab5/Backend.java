import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

/**
 * This class represents that backend that SIS (frontend) interfaces with. It creates the course and user databases from the input files. It is resposible for taking requests from SIS and invoking the appropriate operations on the databases.

 A course file is a comma separated text file in the format (one per line):

 {CourseId},{Course_Name},{CourseLevel}

 A professor file is a comma separated text file in the format (one per line):

 {ProfessorUsername},{CourseId_1},{CourseId_2},...

 A student file is a comma separated text file in the format (one per line):

 {StudentUsername},{CourseId_1},{CourseId_2},....
 */
public class Backend {
    //The Data Bases of courses and users
    private CourseDB courseDB;
    private UserDB userDB;

    /**Creates the backend by initializing the course and user databases.
     *
     *courseFile - name of the course file
     *professorFile - name of the professor file
     *studentFile - name of the student file
     * @throws FileNotFoundException- if any of the files cannot be found
     */
    public Backend(String courseFile, String professorFile, String studentFile) throws FileNotFoundException{
        courseDB = new CourseDB();
        userDB = new UserDB();
        initializeUserDB(professorFile, studentFile);
        initializeCourseDB(courseFile);
        Scanner in = new Scanner(new File(professorFile));
        while(in.hasNext()){
            int x=1;
            String line = in.nextLine();
            String fields[] = line.split(",");
            String username = fields[0];
            while(x<fields.length){
                int id = Integer.parseInt(fields[x]);
                userDB.getValue(username).addCourse(courseDB.getValue(id));
                x++;
            }
        }
        in = new Scanner(new File(studentFile));
        while(in.hasNext()){
            int x=1;
            String line = in.nextLine();
            String fields[] = line.split(",");
            String username = fields[0];
            while(x<fields.length){
                int id = Integer.parseInt(fields[x]);
                userDB.getValue(username).addCourse(courseDB.getValue(id));
                x++;
            }
        }
    }

    /**A utility method for initializing the user database.
     *
     * @param professorFile- name of the professor file
     * @param studentFile- name of the student file
     * @throws FileNotFoundException- if any of the files cannot be found
     */
    private void initializeUserDB(String professorFile, String studentFile) throws FileNotFoundException{
        Scanner in = new Scanner(new File(professorFile));
        String[] professorInfo;
        while(in.hasNext()){
            professorInfo = in.nextLine().split(",");
            Professor p = new Professor(professorInfo[0]);
            userDB.addValue(p);
        }
        Scanner in2 = new Scanner(new File(studentFile));
        String[] studentInfo;
        while(in2.hasNext()){
            studentInfo = in2.nextLine().split(",");
            Student s = new Student(studentInfo[0]);
            userDB.addValue(s);
        }
    }

    /**A utility method for initializing the course database.
     *
     * @param courseFile- name of the course file
     * @throws FileNotFoundException- if any of the files cannot be found
     */
    private void initializeCourseDB(String courseFile) throws FileNotFoundException{
        Scanner in = new Scanner(new File(courseFile));
        String[] courseInfo;
        while(in.hasNext()){
            courseInfo = in.nextLine().split(",");
            int id = Integer.parseInt(courseInfo[0]);
            int level = Integer.parseInt(courseInfo[2]);
            Course course = new Course(id,courseInfo[1],level);
            courseDB.addValue(course);
        }
    }

    /**A utility "utility" method. Used by initializeUserDB when adding a professor or student to a collection of courses.
     *
     * @param user- the user
     * @param courseIds- a collection of course ids
     */
    private void addCourses(User user, String[] courseIds){
        for(String i:courseIds){
            int id = Integer.parseInt(i);
            user.addCourse(courseDB.getValue(id));
        }
    }

    /** Check whether a course exists or not.
     *
     * @param id- the course id
     * @return whether the course exists or not
     */
    public boolean courseExists(int id){
        return courseDB.hasKey(id);
    }

    /**Enroll a student in a course. This requires they are added to both the course and the student's courses.
     *
     * @param username- the username of the student
     * @param courseId- the course if of the student
     * @return whether the student was added or not (false if already enrolled)
     * @Precondition the user exists, the user is a student, the course exists
     */
    public boolean enrollStudent(String username, int courseId){
        User u = userDB.getValue(username);
        Course c = courseDB.getValue(courseId);
        if(u.getCourses().contains(c)){
            return false;
        }
        else{
            u.addCourse(c);
            c.addStudent(username);
            return true;
        }
    }

    /**Get all the courses in the system.
     *
     * @return all the courses
     */
    public Collection<Course> getAllCourses(){
        return courseDB.getAllValues();
    }

    /**Get all users in the system.
     *
     * @return all the users
     */
    public Collection<User> getAllUsers(){
        return userDB.getAllValues();
    }

    /**Check whether a username belongs to a student.
     *
     * @param username- the username
     * @return whether the username belongs to a student or not
     * @Precondition the username exists in the database
     */
    public boolean isStudent(String username) {
        return userDB.getValue(username) instanceof Student;
    }

    /**Get a course by id.
     *
     * @param id- the course id
     * @return the course associated with the id
     * @Precondition the course exists
     */
    public Course getCourse(int id){
        return courseDB.getValue(id);
    }

    /**Unenroll a student in a course. This requires they are removed from both the course and the student's courses.
     *
     * @param username- the username of student to unenroll
     * @param courseId- the id of the course
     * @return true if the student was unerolled, false if the student was not enrolled in the course
     * @Precondition the user exists, the user is a student, the course exists
     */
    public boolean unenrollStudent(String username, int courseId){
        User u = userDB.getValue(username);
        Course c = courseDB.getValue(courseId);
        if(u.getCourses().contains(c)||c.getStudents().contains(u)){
            c.removeStudent(username);
            u.removeCourse(c);
            return true;
        }
        return false;
    }

    /**Check if a username exists in the system.
     *
     * @param username- the username to check
     * @return whether the username is in the system or not
     */
    public boolean userExists(String username){
        return userDB.hasKey(username);
    }

    /**Get the courses for a particular user.
     *
     * @param username- the username
     * @return the collection of courses for a user
     * @Precondition the username exists
     */
    public Collection<Course> getCoursesUser(String username){
        return userDB.getValue(username).getCourses();
    }

}
