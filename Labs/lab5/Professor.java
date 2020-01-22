import java.util.Comparator;

/**
 * Created by njg77 on 2/28/2017.
 */
public class Professor extends User implements Comparable<User> {
    /**Create a new professor. Here you want to pass to the parent, User, a comparator that orders courses first by ascending
     *  course level and second alphabetically by course name.
     *
     * @param username- the username of the professor
     */
    public Professor(String username) {
        super(username, UserType.PROFESSOR, (o1, o2) ->{
                int x = o1.getLevel()-o2.getLevel();
                if(x == 0){
                    return o1.getName().compareTo(o2.getName());
                }
                return x;
        });
    }
}
