import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * A class to represent a single configuration in the Nurikabe puzzle.
 *
 * @author Sean Strout @ RITCS
 * @author Nick Graca
 */
public class NurikabeConfig implements Configuration {

    public int row;
    public int col;
    public int total_islands;
    public int total_sea;
    private int cursorRow;
    private int cursorCol;
    private char[][] board;

    /**
     * Construct the initial configuration from an input file whose contents
     * are, for example:<br>
     * <tt><br>
     * 3 3          # rows columns<br>
     * 1 . #        # row 1, .=empty, 1-9=numbered island, #=island, &#64;=sea<br>
     * &#64; . 3    # row 2<br>
     * 1 . .        # row 3<br>
     * </tt><br>
     * @param filename the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public NurikabeConfig(String filename) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(filename))) {
            int z;
            String[] line;
            row = in.nextInt();
            col = in.nextInt();
            board = new char[row][col];
            int x =0;
            int y=0;
            char num;
            System.out.println(in.nextLine());
            while(x<row){
                line = in.nextLine().split(" ");
                while(y<col){
                    if(line[y].equals(".")==false){
                        z = Integer.parseInt(line[y]);
                        num = line[y].charAt(0);
                        board[x][y]= num;
                        total_islands+=z;
                    }
                    else{
                        board[x][y]='.';
                    }
                    y++;
                }
                x++;
                y=0;
            }
            int total_tiles = row*col;
            total_sea=total_tiles-total_islands;
        }
    }

    /**
     * The copy constructor takes a config, other, and makes a full "deep" copy
     * of its instance data.
     *
     * @param other the config to copy
     */
    protected NurikabeConfig(NurikabeConfig other) {
        int i = 0;
        this.total_islands = other.total_islands;
        this.total_sea = other.total_sea;
        this.row = other.row;
        this.col = other.col;
        this.board = new char[this.row][this.col];
        while(i<this.row){
            System.arraycopy(other.board[i], 0, this.board[i], 0, this.board[i].length);
            i++;
        }
        this.cursorCol = other.cursorCol;
        this.cursorRow = other.cursorRow;

    }

    @Override
    public Collection<Configuration> getSuccessors() {
        ArrayList<Integer> coor = new ArrayList<>();
        LinkedList<Configuration> children = new LinkedList<>();
        coor = nextMove();
        int x = 0;
        int r = coor.get(0);
        int c = coor.get(1);
        while(x<2){
            if(x==0) {
                NurikabeConfig successor = new NurikabeConfig(this);
                successor.board[r][c] = '#';
                children.add(successor);
            }
            else{
                NurikabeConfig successor = new NurikabeConfig(this);
                successor.board[r][c] = '@';
                children.add(successor);
            }
            x++;
        }

        return children;
    }

    @Override
    public boolean isValid() {
        int x=0;
        int y=0;
        //Checks if there are any pools of sea tiles
        while(x<row-1) {
            while (y < col - 1) {
                if (board[x][y] == '@') {
                    if(x-1>=0 && y-1>=0 && Character.toString(board[x][y-1]) == "@" && Character.toString(board[x-1][y])=="@" && Character.toString(board[x-1][y-1]) == "@"){
                        return false;
                    }
                    System.out.println(Character.toString(board[x][y+1]));
                    if((x-1>0 && board[x-1][y]=='.')||(x+1<col && board[x+1][y]=='.')||(y-1>0 && board[x][y-1]=='.')||(y+1<row &&board[x][y+1]=='.')){
                        break;
                    }
                    else if((x-1>0 && board[x-1][y]=='@')||(x+1<col && board[x+1][y]=='@')||(y-1>0 && board[x][y-1]=='@')||(y+1<row &&board[x][y+1]=='@')){
                        break;
                    }else{
                        return false;
                    }
                }
                y++;
                }

            x++;
            y=0;
            }
        x=0;
        y=0;
        //Checks if there more island tiles connected to the main island than there should be and if two Integers are
        //connected
        while(x<=row-1) {
            while (y <= col - 1) {
                if (Character.isDigit(board[x][y])) {
                    int num = Integer.parseInt(Character.toString(board[x][y]));
                    if(dfs(board, x, y, num, "island")!= true){
                        return false;
                    }
                }
                y++;
            }
            x++;
            y=0;
        }
        x=0;
        y=0;
        int currIslands=0;
        while(x<=row-1) {
            while (y <= col - 1) {
                if(board[x][y] == '#'|| Character.isDigit(board[x][y])){
                    currIslands++;
                }
                y++;
            }
            x++;
            y=0;
        }
        if(currIslands>total_islands){
            return false;
        }
        int currSea = 0;
        while(x<=row-1) {
            while (y <= col - 1) {
                if(board[x][y] == '@'){
                    currSea++;
                }
                y++;
            }
            x++;
            y=0;
        }
        if(currSea>total_sea) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean isGoal() {
        //Checks to see if the sea is all connected, if there are any empty tiles, and that there are the correct amount
        //of islands connected to the integer tile
        int x=0;
        int y=0;
        while(x<row) {
            while (y < col ) {
                if (board[x][y] == '.') {
                    return false;
                }
                y++;
            }
            x++;
            y=0;
        }
        return true;

    }

    /**
     * Returns a string representation of the puzzle, e.g.: <br>
     * <tt><br>
     * 1 . #<br>
     * &#64; . 3<br>
     * 1 . .<br>
     * </tt><br>
     */
    @Override
    public String toString() {
        String  finish="";
        int x=0;
        int y=0;
        while(x<=row-1){
            while(y<=col-1){
                finish += board[x][y] + " ";
                y++;
            }
            x++;
            y=0;
            finish+="\n";
        }
        return finish;
    }

    /**
     * Runs DFS on a sea tile and checks to see if the amount of tiles that it found is the same as the total
     * amount of tiles that should be on the board and will return true or false accordingly.
     * @param b - the current board
     * @param total - the amount of tiles of that type there should be
     * @param startx - starting x coordinate
     * @param starty - starting y coordinate
     * @param type - is the staring node an island or a sea?
     * @return if DFS finds the number of tiles connected with the starting tile and of the same type equals
     * the total, then it will return true
     */
    public boolean dfs(char[][] b, int startx, int starty, int total, String type){
        ArrayList<String> visited = new ArrayList<>();
        visited = visitDFS(startx,starty,visited,type);
        if(visited == null){
            return false;
        }
        int size = visited.size();
        if(size<=total){
            return true;
        }
        return false;
    }

    public ArrayList<String> visitDFS(int x, int y, ArrayList<String> visited, String type) {
        if (type == "island") {
            String coor = "(" + x + ", " + (y) + ")";
            if (visited.contains(coor) == false) {
                visited.add(coor);
            }
            if (y + 1 <= col - 1){
                if(board[x][y + 1] == '#'|| Character.isDigit(board[x][y + 1])){
                    coor = "(" + x + ", " + (y + 1) + ")";
                    if (Character.isDigit(board[x][y + 1])) {
                        visited = null;
                        return visited;
                    }
                }
                if (visited.contains(coor) == false) {
                    visited.add(coor);
                    visitDFS(x, y + 1, visited, "island");
                }
            }
                if (y - 1>0 && board[x][y -1] == '#') {
                    coor = "(" + x + ", " + (y - 1) + ")";
                    if(Character.isDigit(board[x][y - 1])){
                        visited = null;
                        return visited;
                    }
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x,y-1,visited,"island");
                    }
                }
            if (x + 1 < row - 1){
                if(board[x+1][y] == '#'|| Character.isDigit(board[x+1][y])){
                    coor = "(" + (x + 1) + ", " + (y) + ")";
                    if (Character.isDigit(board[x + 1][y])) {
                        visited = null;
                        return visited;
                    }
                }
                if (visited.contains(coor) == false) {
                    visited.add(coor);
                    visitDFS(x+1, y, visited, "island");
                }
            }
            if (x - 1 >= 0 ) {
                if (board[x - 1][y] == '#' || Character.isDigit(board[x - 1][y])) {
                    coor = "(" + (x - 1) + ", " + (y) + ")";
                    if (Character.isDigit(board[x - 1][y])) {
                        visited = null;
                        return visited;
                    }
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x - 1, y, visited, "island");
                    }
                }
            }
            return visited;
            }
            else if(type == "sea"){
                String coor = "(" + x + ", " + (y) + ")";
                visited.add(coor);
                if (y + 1 < row - 1 && board[x][y + 1] == '@') {
                    coor = "(" + x + ", " + (y + 1) + ")";
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x, y + 1, visited, "sea");
                    }
                }
                if (y - 1 < row - 1 && board[x][y -1] == '@') {
                    coor = "(" + x + ", " + (y - 1) + ")";
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x,y-1,visited,"sea");
                    }
                }
                if (x + 1 < row - 1 && board[x+1][y] == '@') {
                    coor = "(" + (x+1) + ", " + (y) + ")";
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x+1, y, visited, "sea");
                    }
                }
                if (x - 1 > 0 && board[x-1][y] == '@') {
                    coor = "(" + (x-1) + ", " + (y) + ")";
                    if (visited.contains(coor) == false) {
                        visited.add(coor);
                        visitDFS(x-1,y,visited,"sea");
                    }
                }
                return visited;
            }

            return visited;
        }

        //Finds where the next move should be made
    public ArrayList<Integer> nextMove(){
        int x=0;
        int y=0;
        ArrayList<Integer> list = new ArrayList<>();
        while(x<=row-1) {
            while (y <= col - 1) {
                if(board[x][y]=='.'){
                 list.add(x);
                 list.add(y);
                 return list;
                }
                y++;
            }
            x++;
            y=0;
        }
        return list;
    }
}

