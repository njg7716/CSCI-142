package Players.njg7716;

import java.util.ArrayList;

/**
 * Created by njg77 on 3/21/2017.
 */
public class Node {

    public int r;
    public int c;
    public boolean occupied;
    public int id;
    public ArrayList<Node> neighbors;

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Node(int r, int c, int id, ArrayList<Node> neighbors){
        this.r=r;
        this.c=c;
        this.occupied=false;
        this.id= id;
        this.neighbors = neighbors;
    }
    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }
}
