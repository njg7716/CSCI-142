package calendar.model;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

/**
 * Created by njg77 on 4/13/2017.
 */
public class Calendar extends Observable {

    private final int monthSize;
    private List<List<Appointment>> appointments;

    //creates a new calendar
    public Calendar(int monthSize){
        this.monthSize = monthSize;
        appointments = new ArrayList<>();
        int x= 0;
        while(x<monthSize){
            appointments.add(new ArrayList<Appointment>());
            x++;
        }
    }
    //adds an appointment to the calendar at the date in the appointment object
    public void add(Appointment appt){
        for(Appointment x:appointments.get(appt.date)){
            if(x.equals(appt)){
                return;
            }
        }
        appointments.get(appt.date).add(appt);
    }
    //creates an appointment object and adds it to the calendar
    public void add(int date, Time time, String what){
        Appointment appt = new Appointment(date,time,what);
        List<Appointment> appts = appointments.get(date);
        if(appts.contains(appt)){
            return;
        }
        else{
            add(appt);
        }
    }
    //Lists the appointments on the specified date
    public List<Appointment> appointmentsOn(int date){
        List<Appointment> some = new ArrayList();
        some = appointments.get(date);
        return some;
    }
    //changes the date
    protected void dateChanged(int date){
        setChanged();
        notifyObservers(date);
    }
    //creates a calendar from a file
    public static Calendar fromFile(String fileName) throws IOException{
        Scanner in = new Scanner(new File(fileName));
        int monthSize = in.nextInt();
        Calendar month = new Calendar(monthSize);
        while(in.hasNext()){
            int date = in.nextInt();
            Time time = Time.fromString(in.next());
            String what = in.next();
            Appointment appt = new Appointment(date,time,what);
            month.add(appt);
        }
        return month;
    }
    //getter for monthSize
    public int numDays(){
        return monthSize;
    }
    public void toFile() throws IOException{
    }
    //removes an appointment from the calendar
    public void remove(Appointment appt){
        appointments.get(appt.date).remove(appt);
    }
}
