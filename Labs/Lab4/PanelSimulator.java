import java.util.HashMap;
import java.util.Scanner;

public class PanelSimulator {
    public static Scanner in = new Scanner(System.in);

    //Will ask user for a command to add, view or toggle components
    public static void main(String[] args) {
        HashMap<String, Component> map= new HashMap<>();
        String[] command = Input();
        boolean var = true;
        while (var) {
            if (command[0].equals("A")) {
                if (command[1].equals("C")) {
                    String name = command[2];
                    String sourceName = command[3];
                    int maxCurrent = Integer.parseInt(command[4]);
                    if(map.get(name) instanceof Component){
                        System.out.println("Component name already in use.");
                    }
                    else {
                        if (sourceName.equals("ROOT")) {
                            System.out.println("Circuit '" + name + "' added as root circuit");
                            map.put(name, new Circuit(name, null, maxCurrent));
                        } else {
                            if(map.get(sourceName)==null){
                                System.out.println("The source does not exist.");
                            }
                            else {
                                System.out.println("Circuit '" + name + "' added successfully");
                                map.put(name, new Circuit(name, map.get(sourceName), maxCurrent));
                                map.get(sourceName).add(new Circuit(name, map.get(sourceName), maxCurrent));
                            }
                        }
                    }
                } else if (command[1].equals("R")) {
                    String name = command[2];
                    String sourceName = command[3];
                    if(map.get(name) instanceof Component){
                        System.out.println("Component name already in use.");
                    }
                    else if(map.get(sourceName)==null){
                        System.out.println("The source does not exist.");
                    }
                    else {
                        int maxAttach = Integer.parseInt(command[4]);
                        System.out.println("Receptacle '" + name + "' added successfully");
                        map.put(name, new Receptacle(name, map.get(sourceName), maxAttach));
                        map.get(sourceName).add(new Receptacle(name, map.get(sourceName), maxAttach));
                    }
                } else if (command[1].equals("A")) {
                    String name = command[2];
                    String sourceName = command[3];
                    if(map.get(name) instanceof Component){
                        System.out.println("Component name already in use.");
                    }
                    else if(map.get(sourceName)==null){
                        System.out.println("The source does not exist.");
                    }
                    else {
                        int reqCurr = Integer.parseInt(command[4]);
                        System.out.println("Appliance '" + name + "' added successfully");
                        map.put(name, new Appliance(name, map.get(sourceName), reqCurr));
                    }
                }
            } else if (command[0].equals("D")) {
                String name = command[1];
                if(map.get(name) == null){
                    System.out.println(name+" does not exist.");
                }
                else {
                    map.get(name).display();
                }
            } else if (command[0].equals("T")) {
                String name = command[1];
                if(map.get(name).source == null){
                    System.out.println("The Appliance "+name+" is not connected to anything.");
                }
                else if(map.get(name)==null){
                    System.out.println("The Appliance "+name+" does not exist.");
                }
                else {
                    Component app = map.get(name);
                    if (app instanceof Appliance) {
                        ((Appliance) app).toggleUsage();
                    } else {
                        System.out.println("You cannot toggle anything other than an Appliance.");
                    }
                }
            }
            else if(command[0].equals("Q")) {
                var = false;
            }
            command = Input();

        }
    }
//Prompts the user
    public static String[] Input(){
        System.out.println("PanelSimulator input: ");
        String[] command = in.nextLine().split("\\s+");
        return command;
    }
}
