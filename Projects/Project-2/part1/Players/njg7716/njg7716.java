package Players.njg7716;

import Interface.PlayerModule;
import Interface.PlayerModulePart1;
import Interface.PlayerMove;

import java.util.*;

/**
 * Created by njg77 on 3/19/2017.
 */
public class njg7716 implements PlayerModulePart1{

    public Node[][] spots;

    /**Part 1 task that tests if a player has won the game given a set of PREMOVEs.
     *
     * @param id - player to test for a winning path.
     * @return- boolean value indicating if the player has a winning path.
     */
    @Override
    public boolean hasWonGame(int id) {
        int x=1;
        int y=1;
        int finish = spots.length-2;
        if(id==1){
            while(y<spots.length-1){
                List<Node> q = new LinkedList<>();
                Set<Node> visited = new HashSet<>();
                if(spots[y][1]!=null &&spots[y][1].getId()==1){
                    Node start = spots[y][1];
                    q.add(start);
                    visited.add(start);
                    while(q.size()>0){
                        Node c = q.remove(0);
                        if(c.getC()==finish){
                            return true;
                        }
                        for(Node n:c.neighbors){
                            if(visited.contains(n)){

                            }else {
                                q.add(n);
                                visited.add(n);
                            }
                        }
                    }
                }
                y+=1;
            }
        }
        if(id==2){
            while(x<spots.length-1){
                List<Node> q = new LinkedList<>();
                Set<Node> visited = new HashSet<>();
                if(spots[1][x]!=null&& spots[1][x].getId()==2){
                    Node start = spots[1][x];
                    q.add(start);
                    visited.add(start);
                    while(q.size()>0){
                        Node c = q.remove(0);
                        if(c.getR()==finish){
                            return true;
                        }
                        for(Node n:c.neighbors){
                            if(visited.contains(n)){

                            }else {
                                q.add(n);
                                visited.add(n);
                            }
                        }
                    }
                }
                x+=1;
            }

        }
        return false;
    }

    /**Method called to initialize a player module. Required task for Part 1. Note that for tournaments of multiple games, only one instance of each PlayerModule is created. The initPlayer method is called at the beginning of each game, and must be able to reset the player for the next game.
     *
     * @param dim- size of the smaller dimension of the playing area for one player. The grid of nodes for that player is of size dim x (dim+1).
     * @param id- id (1 or 2) for this player.
     */
    @Override
    public void initPlayer(int dim, int id) {
        spots = new Node[2*dim+1][2*dim+1];
    }

    /**Method called after every move of the game. Used to keep internal game state current. Required task for Part 1. Note that the engine will only call this method after verifying the validity of the current move. Thus, you do not need to verify the move provided to this method. It is guaranteed to be a valid move.
     *
     * @param m - PlayerMove representing the most recent move
     */
    @Override
    public void lastMove(PlayerMove m) {
       int c =  m.getCoordinate().getCol();
       int r = m.getCoordinate().getRow();
       int size = spots.length-1;
       int id = m.getPlayerId();
       if(r>size){
           System.out.println("Row is outside the size of the game board.");
       }
       else if(c>size){
           System.out.println("Column is outside the size of the game board.");
       }
       else {
           ArrayList<Node> neighbors = new ArrayList<>();
           Node node = new Node(r, c, id, neighbors);
           node.setOccupied(true);
           spots[r][c] = node;
           if(id==2) {
               if (r - 2 >= 0 && spots[r - 2][c] != null) {
                   Node neighbor1 = spots[r - 2][c];
                   if (neighbor1.getId() == id) {
                       neighbors.add(neighbor1);
                       neighbor1.neighbors.add(spots[r][c]);
                   }
               }
               if (r + 2 <= size && spots[r + 2][c] != null) {
                   Node neighbor2 = spots[r + 2][c];
                   if (neighbor2.getId() == id) {
                       neighbors.add(neighbor2);
                       neighbor2.neighbors.add(spots[r][c]);
                   }
               }
           }
           if(id==1) {
               if (c + 2 <= size && spots[r][c + 2] != null) {
                   Node neighbor3 = spots[r][c + 2];
                   if (neighbor3.getId() == id) {
                       neighbors.add(neighbor3);
                       neighbor3.neighbors.add(spots[r][c]);
                   }
               }
               if (c - 2 >= 0 && spots[r][c - 2] != null) {
                   Node neighbor9 = spots[r][c - 2];
                   if (neighbor9.getId() == id) {
                       neighbors.add(neighbor9);
                       neighbor9.neighbors.add(spots[r][c]);
                   }
               }
           }
           if (r-1 >=0 && c - 1 >= 0 && spots[r-1][c - 1] != null) {
               Node neighbor4 = spots[r-1][c - 1];
               if(neighbor4.getId()==id) {
                   neighbors.add(neighbor4);
                   neighbor4.neighbors.add(spots[r][c]);
               }
           }
           if (r-1>=0 && c + 1 <= size && spots[r-1][c + 1] != null) {
               Node neighbor5 = spots[r-1][c + 1];
               if(neighbor5.getId()==id) {
                   neighbors.add(neighbor5);
                   neighbor5.neighbors.add(spots[r][c]);
               }
           }
           if (r + 1 <= size && c + 1 <= size && spots[r+1][c + 1] != null) {
               Node neighbor6 = spots[r+1][c + 1];
               if(neighbor6.getId()==id) {
                   neighbors.add(neighbor6);
                   neighbor6.neighbors.add(spots[r][c]);
               }
           }
           if (r + 1 <= size &&c - 1 >=0 && spots[r+1][c - 1] != null) {
               Node neighbor8 = spots[r+1][c -1];
               if(neighbor8.getId()==id) {
                   neighbors.add(neighbor8);
                   neighbor8.neighbors.add(spots[r][c]);
               }
           }
           spots[r][c].neighbors = neighbors;
       }
    }

    /**Indicates that the other player has been invalidated. Required task for Part 2.
     *
     */
    @Override
    public void otherPlayerInvalidated() {

    }

    /**Generates the next move for this player. Note that it is recommended that updating internal game state does NOT occur inside of this method. See lastMove. An initial, working version of this method is required for Part 2. It may be refined subsequently.
     *
     * @return a PlayerMove object representing the next move.
     */
    @Override
    public PlayerMove move() {
        return null;
    }


}
