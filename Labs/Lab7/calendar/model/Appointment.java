package calendar.model;

/**
 * Created by njg77 on 4/13/2017.
 */
public class Appointment implements Comparable<Appointment>{

    public int date;
    public Time time;
    public String what;

    public Appointment(int date, Time time, String what){

        this.date= date;
        this.time = time;
        this.what = what;
    }
    //Compares two appointment objects
    public int compareTo(Appointment other){
        if(date==other.date){
            return time.compareTo(other.time);

        }
        else{
            return date-other.date;
        }
    }
    //prints the appointment like date,time and then the description
    public String csvFormat(){
        String csv = String.valueOf(date);
        csv+=", ";
        csv+=  time.toString();
        csv+=", ";
        csv+= what;
        return csv;
    }
    //Uses the compare to function to see if they are equal
    public boolean equals(Object other){
        if(other instanceof Appointment) {
            if (compareTo((Appointment)other) == 0) {
                return true;
            }
            return false;
        }
        return false;
    }
    //creats an appointment from a String array
    public static Appointment fromString(String inputLine){
        String [] line = inputLine.split(",");
        int  date = Integer.parseInt(line[1]);
        Time time = Time.fromString(line[2]);
        String descrip = line[3];
        Appointment a1 = new Appointment(date,time,descrip);
        return a1;
    }
    //getter for date
    public int getDate(){
        return date;
    }
    //getter for the description
    public String getText(){
        return what;
    }
    //getter for the time
    public Time getTime(){
        return time;
    }

    @Override
    //prints the appointment object
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", time=" + time +
                ", what='" + what + '\'' +
                '}';
    }
}

