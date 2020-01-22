package calendar.view_controller.fx;
import calendar.model.Appointment;
import calendar.model.Calendar;
import calendar.model.Time;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Observable;

/**
 * Created by njg77 on 4/25/2017.
 */
public class KalGUI extends Application {

    private Calendar cal;

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    //Starts the GUI
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(makeBorderPane());
        primaryStage.setTitle("Calendar GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //Makes the grid pane
    public GridPane makeGrid(){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        int row;
        int col;
        int days=1;
        for (row=0;row<4;row++){
            for (col=0;col<7;col++){
                Button temp = new Button(String.valueOf(days));
                pane.add(temp,col,row);
                temp.setOnAction(event->{
                    int date = Integer.parseInt(temp.getText());
                    List<Appointment> appointments = cal.appointmentsOn(date);
                    BorderPane t = (BorderPane) temp.getParent().getParent();
                    VBox v = (VBox) t.getLeft();
                    int size = appointments.size();
                    if(size==0){
                        v.getChildren().remove(size+1);
                        return;
                    }
                    for(int x=1;x<v.getChildren().size();x++){
                        v.getChildren().remove(x);
                    }
                    for(int i=0;i<appointments.size();i++) {
                        v.getChildren().add(new Label(appointments.get(i).toString()));
                    }
                });
                days++;
            }
        }
        return pane;
    }
    //Makes the Border Pane
    public BorderPane makeBorderPane(){
        BorderPane pane = new BorderPane();
        Label title = new Label("                                                                       Calendar");
        pane.setCenter(makeGrid());
        pane.setTop(title);
        pane.setLeft(makeVbox());
        pane.setBottom(makeVbox2());
        return pane;
    }
    //Makes the bottom part of the GUI
    public VBox makeVbox2(){
        VBox vb = new VBox();
        TextField tdate = new TextField();
        TextField ttime = new TextField();
        TextField twhat = new TextField();
        HBox hb = new HBox();
        HBox date = new HBox();
        HBox time = new HBox();
        HBox what = new HBox();
        Button b1 = new Button("Add Appointment ");
        Button b2 = new Button("Delete Appointment ");
        date.getChildren().add(new Label("Date:            "));
        date.getChildren().add(tdate);
        time.getChildren().add(new Label("Time:            "));
        time.getChildren().add(ttime);
        what.getChildren().add(new Label("Description: "));
        what.getChildren().add(twhat);
        vb.getChildren().add(date);
        vb.getChildren().add(time);
        vb.getChildren().add(what);
        hb.getChildren().add(b1);
        hb.getChildren().add(b2);
        vb.getChildren().add(hb);
        b1.setOnAction(event -> {
            int d = Integer.parseInt(tdate.getText());
            String t = ttime.getText();
            Time ti = Time.fromString(t);
            String des = twhat.getText();
            cal.add(d,ti,des);
        });
        b2.setOnAction(event -> {
            int d = Integer.parseInt(tdate.getText());
            String t = ttime.getText();
            Time ti = Time.fromString(t);
            String des = twhat.getText();
            Appointment appt = new Appointment(d,ti,des);
            cal.remove(appt);
        });
        return vb;
    }
    //makes the left side of the GUI
    public VBox makeVbox(){
        VBox vb = new VBox();
        vb.getChildren().add(new Label("APPOINTMENTS (Date, Time, Description):                                "));
        vb.getChildren().add(new VBox());
        return vb;
    }
    @Override
    //creates the calendar object
    public void init(){
        this.cal = new Calendar(28);
    }
}
