package Players.CeleryStalkers;

import Interface.*;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * file: Players.Part2.java
 * language: java
 * @author Nick Graca
 * @author Jacob Brown
 * description: part 2 of the Pathbuilder project
 */
public class CeleryStalkers implements PlayerModulePart1, PlayerModulePart2, PlayerModulePart3 {

    private int playerID;
    private int[][] gameBoard;
    private int turn = 0;

    /**
     * Generates all legal moves and returns them as Coordinate objects in a list
     * Precondition: there is no winner and it is your players turn
     *
     * @return list of PlayerMove objects of all legal moves
     */
    @Override
    public List<PlayerMove> allLegalMoves() {
        int row = 1;
        int col = 1;
        List<PlayerMove> moves = new ArrayList<>();
        PlayerMove move;
        int size = gameBoard[0].length;
        while (row < size - 1) {
            while (col < size - 1) {
                if ((gameBoard[row][col] == 0) && (row % 2 == 0 && col % 2 == 0) || (gameBoard[row][col] == 0) && (row % 2 == 1 && col % 2 == 1)) {
                    move = new PlayerMove(new Coordinate(row, col), this.playerID);
                    moves.add(move);
                }
                col++;
            }
            row++;
            col = 1;
        }
        return moves;
    }

    /**
     * Computes the fewest segments needed for victory if the other player does not move
     * Precondition: you may assume that the other player has not already won the game.
     * That is, you may assume that a winning path still exists for the player of interest.
     *
     * @param playerID the player of interest
     * @return fewest number of segments to add to complete a path
     */
    @Override
    public int fewestSegmentsToVictory(int playerID) {

        ArrayList<Coordinate> start_nodes = new ArrayList<>();

        //finds lowest path cost if playerID == 1
        if (playerID == 1) {
            for (int i = 1; i < this.gameBoard.length; i += 2) {
                start_nodes.add(new Coordinate(i, 0));
            }
        }

        //finds lowest path cost if playerID == 2
        if (playerID == 2) {
            for (int i = 1; i < this.gameBoard.length; i += 2) {
                start_nodes.add(new Coordinate(0, i));
            }
        }

        return dijkstra(start_nodes, playerID);
    }

    /**
     * This function runs the dijkstra algorithm. It coppies the board, initializes all start nodes,
     * and computes cost based on the findCost() method listed below.
     *
     * @param unvisited list of unvisited nodes
     * @param playerID  player number currently being played
     * @return fewest number of segments to add to complete a path
     */
    private int dijkstra(ArrayList<Coordinate> unvisited, int playerID) {
        //copies the game board configuration to a new board
        int[][] board_copy = new int[this.gameBoard.length][this.gameBoard.length];
        for (int i = 0; i < this.gameBoard.length; i++) {
            System.arraycopy(this.gameBoard[i], 0, board_copy[i], 0, board_copy[i].length);
        }

        //Add infinity to initialize the board
        if (playerID == 1) {
            for (int i = 1; i < board_copy.length; i += 2) {
                for (int j = 2; j < board_copy.length; j += 2) {
                    board_copy[i][j] = Integer.MAX_VALUE;
                    unvisited.add(new Coordinate(i, j));
                }
            }
        }
        //Add infinity to initialize the board
        if (playerID == 2) {
            for (int i = 2; i < board_copy.length; i += 2) {
                for (int j = 1; j < board_copy.length; j += 2) {
                    board_copy[i][j] = Integer.MAX_VALUE;
                    unvisited.add(new Coordinate(i, j));
                }
            }
        }

        while (!unvisited.isEmpty()) {
            Coordinate lowest = findLowest(unvisited, board_copy);

            ArrayList<Coordinate> neighbors = getDijkstraNeighbors(lowest);

            //The for loop computes the cost to connect to each neighbor and adding the cost to the board
            //The if statements check to see what neighbor you're dealing with, and acts accordingly
            for (Coordinate neighbor : neighbors) {
                if (neighbor.getCol() < lowest.getCol()) {
                    Coordinate westNeighbor = new Coordinate(lowest.getRow(), lowest.getCol() - 1);
                    int west_cost = findCost(board_copy, westNeighbor, playerID);
                    if (board_copy[lowest.getRow()][lowest.getCol()] + west_cost < board_copy[neighbor.getRow()][neighbor.getCol()])
                        board_copy[neighbor.getRow()][neighbor.getCol()] = board_copy[lowest.getRow()][lowest.getCol()] + west_cost;
                }
                if (neighbor.getCol() > lowest.getCol()) {
                    Coordinate eastNeighbor = new Coordinate(lowest.getRow(), lowest.getCol() + 1);
                    int east_cost = findCost(board_copy, eastNeighbor, playerID);
                    if (board_copy[lowest.getRow()][lowest.getCol()] + east_cost < board_copy[neighbor.getRow()][neighbor.getCol()])
                        board_copy[neighbor.getRow()][neighbor.getCol()] = board_copy[lowest.getRow()][lowest.getCol()] + east_cost;
                }
                if (neighbor.getRow() < lowest.getRow()) {
                    Coordinate northNeighbor = new Coordinate(lowest.getRow() - 1, lowest.getCol());
                    int north_cost = findCost(board_copy, northNeighbor, playerID);
                    if (board_copy[lowest.getRow()][lowest.getCol()] + north_cost < board_copy[neighbor.getRow()][neighbor.getCol()])
                        board_copy[neighbor.getRow()][neighbor.getCol()] = board_copy[lowest.getRow()][lowest.getCol()] + north_cost;
                }
                if (neighbor.getRow() > lowest.getRow()) {
                    Coordinate southNeighbor = new Coordinate(lowest.getRow() + 1, lowest.getCol());
                    int south_cost = findCost(board_copy, southNeighbor, playerID);
                    if ((board_copy[lowest.getRow()][lowest.getCol()] + south_cost) < board_copy[neighbor.getRow()][neighbor.getCol()])
                        board_copy[neighbor.getRow()][neighbor.getCol()] = board_copy[lowest.getRow()][lowest.getCol()] + south_cost;
                }
            }
            unvisited.remove(lowest);
        }

        int min = Integer.MAX_VALUE;

        //finds the lowest cost if the player at hand is 1
        if (playerID == 1) {
            for (int i = 1; i < board_copy.length; i += 2) {
                if (board_copy[i][board_copy.length - 1] < min)
                    min = board_copy[i][board_copy.length - 1];
            }
        }

        //finds the lowest cost if the player at hand is 2
        if (playerID == 2) {
            for (int i = 1; i < board_copy.length; i += 2) {
                if (board_copy[board_copy.length - 1][i] < min)
                    min = board_copy[board_copy.length - 1][i];
            }
        }
        return min;
    }

    /**
     * Find the cost based on the value in the neighbor spot. The following is how cost is defined...
     * Cost of 0: if the value of the board at the neighbors coordinates has a value of playerID
     * Cost of 1: if the value of the board at the neighbors coordinates has a value of 0
     * Cost of a large number: if the value of the board at the neighbors coordinates has a value of the other player
     *
     * @param board    2D array representing a board to check
     * @param neighbor Coordinate object representing a neighbor
     * @param playerID integer value representing the player ID
     * @return cost
     */
    private int findCost(int[][] board, Coordinate neighbor, int playerID) {
        if (board[neighbor.getRow()][neighbor.getCol()] == playerID)
            return 0;
        if (board[neighbor.getRow()][neighbor.getCol()] == 0)
            return 1;
        return 100000;
    }

    /**
     * Loop through all Coordinate objects and find the Coordinate which corresponds to the lowest
     * cost on the given board
     *
     * @param unvisited list of Coordinate objects
     * @param board     represents the game board to find the cost
     * @return the lowest Coordinate node
     */
    private Coordinate findLowest(ArrayList<Coordinate> unvisited, int[][] board) {
        Coordinate lowest = unvisited.get(0);
        for (Coordinate node : unvisited) {
            if (board[node.getRow()][node.getCol()] < board[lowest.getRow()][lowest.getCol()])
                lowest = node;
        }
        return lowest;
    }

    /**
     * This function gets all neighbors of the given coordinate. Used in fewestSegmentsToVictory and NOT in hasWonGame.
     *
     * @param coordinate given Coordinate to find the neighbors of
     * @return list of valid neighbors
     */
    private ArrayList<Coordinate> getDijkstraNeighbors(Coordinate coordinate) {
        Coordinate north = new Coordinate(coordinate.getRow() - 2, coordinate.getCol());
        Coordinate south = new Coordinate(coordinate.getRow() + 2, coordinate.getCol());
        Coordinate east = new Coordinate(coordinate.getRow(), coordinate.getCol() + 2);
        Coordinate west = new Coordinate(coordinate.getRow(), coordinate.getCol() - 2);

        ArrayList<Coordinate> neighbors = new ArrayList<>();

        if (dijkstraIsValid(north)) {
            neighbors.add(north);
        }
        if (dijkstraIsValid(south)) {
            neighbors.add(south);
        }
        if (dijkstraIsValid(east)) {
            neighbors.add(east);
        }
        if (dijkstraIsValid(west)) {
            neighbors.add(west);
        }
        return neighbors;
    }

    /**
     * Tests to see if the coordinate object given is a valid node for the dijkstra algorithm.
     *
     * @param coordinate coordinate of the node to test
     * @return true if valid, false otherwise
     */
    private boolean dijkstraIsValid(Coordinate coordinate) {
        if (coordinate.getCol() < 0 || coordinate.getRow() < 0)
            return false;
        if (coordinate.getCol() > this.gameBoard.length - 1 || coordinate.getRow() > this.gameBoard.length - 1)
            return false;
        return true;
    }

    /**
     * Part 1 task that tests if a player has won the game given a set of PREMOVEs
     *
     * @param id - player to test for a winning path.
     * @return boolean value indicating if the player has a winning path.
     */
    @Override
    public boolean hasWonGame(int id) {
        LinkedList<Coordinate> queue = new LinkedList<>();
        ArrayList<Coordinate> visited = new ArrayList<>();

        if (id == 1) {
            for (int i = 1; i < this.gameBoard.length; i += 2) {
                queue.add(new Coordinate(i, 0));
                visited.add(new Coordinate(i, 0));
            }
        } else {
            for (int i = 1; i < this.gameBoard.length; i += 2) {
                queue.add(new Coordinate(0, i));
                visited.add(new Coordinate(0, i));
            }
        }
        while (!queue.isEmpty()) {
            Coordinate dequeue = queue.pollFirst();
            if (dequeue.getCol() == this.gameBoard.length - 1 || dequeue.getRow() == this.gameBoard.length - 1)
                return true;
            ArrayList<Coordinate> neighbors = getNeighbors(dequeue, id);
            for (Coordinate neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * This function gets all neighbors of the given coordinate. Used in hasWonGame and NOT in fewestSegmentsToVictory.
     *
     * @param coordinate coordinate object to find the neighbors of
     * @param playerID   player number of interest
     * @return neighbors of the given node, null if no neighbors
     */
    private ArrayList<Coordinate> getNeighbors(Coordinate coordinate, int playerID) {
        Coordinate north = new Coordinate(coordinate.getRow() - 1, coordinate.getCol());
        Coordinate south = new Coordinate(coordinate.getRow() + 1, coordinate.getCol());
        Coordinate east = new Coordinate(coordinate.getRow(), coordinate.getCol() + 1);
        Coordinate west = new Coordinate(coordinate.getRow(), coordinate.getCol() - 1);

        ArrayList<Coordinate> neighbors = new ArrayList<>();

        if (isValid(north) && this.gameBoard[north.getRow()][north.getCol()] == playerID) {
            Coordinate northNeighbor = new Coordinate(coordinate.getRow() - 2, coordinate.getCol());
            neighbors.add(northNeighbor);
        }
        if (isValid(south) && this.gameBoard[south.getRow()][south.getCol()] == playerID) {
            Coordinate southNeighbor = new Coordinate(coordinate.getRow() + 2, coordinate.getCol());
            neighbors.add(southNeighbor);
        }
        if (isValid(east) && this.gameBoard[east.getRow()][east.getCol()] == playerID) {
            Coordinate eastNeighbor = new Coordinate(coordinate.getRow(), coordinate.getCol() + 2);
            neighbors.add(eastNeighbor);
        }
        if (isValid(west) && this.gameBoard[west.getRow()][west.getCol()] == playerID) {
            Coordinate westNeighbor = new Coordinate(coordinate.getRow(), coordinate.getCol() - 2);
            neighbors.add(westNeighbor);
        }
        return neighbors;
    }

    /**
     * Tests to see if the coordinate object given is a valid node for the BFS algorithm
     *
     * @param coordinate coordinate of the node to test
     * @return true if valid, false otherwise
     */
    private boolean isValid(Coordinate coordinate) {
        if (coordinate.getCol() < 1 || coordinate.getRow() < 1)
            return false;
        if (coordinate.getCol() >= this.gameBoard.length - 1 || coordinate.getRow() >= this.gameBoard.length - 1)
            return false;
        return true;
    }

    /**
     * Method called to initialize a player module. Required task for Part 1.
     * Note that for tournaments of multiple games, only one instance of each PlayerModule is created.
     * The initPlayer method is called at the beginning of each game,
     * and must be able to reset the player for the next game.
     *
     * @param dim size of the smaller dimension of the playing area for one player.
     *            The grid of nodes for that player is of size dim x (dim+1).
     * @param id  id (1 or 2) for this player
     */
    @Override
    public void initPlayer(int dim, int id) {
        this.playerID = id;
        this.gameBoard = new int[dim * 2 + 1][dim * 2 + 1];
    }

    /**
     * Method called after every move of the game. Used to keep internal game state current. Required task for Part 1.
     * Note that the engine will only call this method after verifying the validity of the current move.
     * Thus, you do not need to verify the move provided to this method. It is guaranteed to be a valid move.
     *
     * @param m PlayerMove representing the most recent move
     */
    @Override
    public void lastMove(PlayerMove m) {
        int row = m.getCoordinate().getRow();
        int col = m.getCoordinate().getCol();
        int player = m.getPlayerId();
        this.gameBoard[row][col] = player;
    }

    /**
     * Indicates that the other player has been invalidated. Required task for Part 2.
     */
    @Override
    public void otherPlayerInvalidated() {

    }

    /**
     * Generates the next move for this player. Note that it is recommended that updating internal game state does NOT
     * occur inside of this method. See lastMove. An initial, working version of this method is required for Part 2.
     * It may be refined subsequently
     *
     * @return a PlayerMove object representing the next move.
     */
    @Override
    public PlayerMove move() {

        int other_player;
        if (this.playerID == 1)
            other_player = 2;
        else
            other_player = 1;
        List<PlayerMove> legal_moves = allLegalMoves();
        PlayerMove offense_move = legal_moves.get(0);
        this.gameBoard[offense_move.getCoordinate().getRow()][offense_move.getCoordinate().getCol()] = this.playerID;
        int offense = fewestSegmentsToVictory(this.playerID);
        this.gameBoard[offense_move.getCoordinate().getRow()][offense_move.getCoordinate().getCol()] = 0;

        for (PlayerMove move : legal_moves) {
            this.gameBoard[move.getCoordinate().getRow()][move.getCoordinate().getCol()] = this.playerID;
            int temp_segments = fewestSegmentsToVictory(this.playerID);
            if (temp_segments < offense) {
                offense_move = move;
                offense = temp_segments;
            }
            this.gameBoard[move.getCoordinate().getRow()][move.getCoordinate().getCol()] = 0;
        }

        PlayerMove defense_move = legal_moves.get(0);
        this.gameBoard[defense_move.getCoordinate().getRow()][defense_move.getCoordinate().getCol()] = other_player;
        int defense = fewestSegmentsToVictory(other_player);
        this.gameBoard[defense_move.getCoordinate().getRow()][defense_move.getCoordinate().getCol()] = 0;

        for (PlayerMove move : legal_moves) {
            this.gameBoard[move.getCoordinate().getRow()][move.getCoordinate().getCol()] = other_player;
            int temp_segments = fewestSegmentsToVictory(other_player);
            if (temp_segments < defense) {
                defense_move = move;
                defense = temp_segments;
            }
            this.gameBoard[move.getCoordinate().getRow()][move.getCoordinate().getCol()] = 0;
        }

        this.turn++;
        if (defense<6)
            return defense_move;
        return offense_move;
    }

    /**
     * computes whether the given player is guaranteed with optimal strategy to have won the game
     * in no more than the given number of total moves, also given whose turn it is currently.
     * Precondition: numMoves is non-negative
     *
     * @param playerID  player to determine winnable status for
     * @param whoseTurn player whose turn it is currently
     * @param numMoves  num of total moves by which the player of interest must be able to guarantee victory
     *                  to satisfy the requirement to return a value of true
     * @return boolean indicating whether it is possible for the indicated player to guarantee a win after
     * the specified number of total moves
     */
    @Override
    public boolean isWinnable(int playerID, int whoseTurn, int numMoves) {
        int determinant = 0;
        int opposite = playerID;
        if (playerID == 1)
            opposite = 2;
        else
            opposite = 1;
        if (playerID == whoseTurn)
            determinant = minMax(this.gameBoard, numMoves*2, true, opposite);
        else
            determinant = minMax(this.gameBoard, numMoves*2, false, opposite);
        return determinant > 0;
    }

    /**
     * This function runs a min max algorithm to determine if the player given is guaranteed to win the game
     * in the number of moves given
     * @param board            board of the path builder game
     * @param numMoves         maximum number of moves to victory
     * @param maximizingPlayer player to determine if a victory is guaranteed given the above information
     * @return true if victory is guaranteed, false otherwise
     */
    private int minMax(int[][] board, int numMoves, boolean maximizingPlayer, int playerID) {

        //base cases
        if (!maximizingPlayer && hasWonGame(playerID, board))
            return 1;
        if (maximizingPlayer && hasWonGame(playerID, board))
            return -1;
        if (numMoves == 0)
            return -1;

        if (playerID == 1)
            playerID = 2;
        else
            playerID = 1;

        if (maximizingPlayer) {
            LinkedList<int [][]> children = successors(playerID, board);
            int [][] first_child = children.get(0);
            for (int [][] curr_board : children) {
                int max = minMax(curr_board, numMoves - 1, false, playerID);
                if (max > 0)
                    return max;
            }
            return -1;
        } else {
            LinkedList<int [][]> children = successors(playerID, board);
            int [][] first_child = children.get(0);
            for (int [][] curr_board : children) {
                int min = minMax(curr_board, numMoves - 1, true, playerID);
                if (min < 0)
                    return min;
            }
            return 1;
        }
    }

    /**
     * Part 1 task that tests if a player has won the game given a set of PREMOVEs
     * Adapted to Part 3 implementation
     * @param id - player to test for a winning path.
     * @param board board to use
     * @return boolean value indicating if the player has a winning path.
     */
    public boolean hasWonGame(int id, int [][] board) {
        LinkedList<Coordinate> queue = new LinkedList<>();
        ArrayList<Coordinate> visited = new ArrayList<>();

        if (id == 1) {
            for (int i = 1; i < board.length; i += 2) {
                queue.add(new Coordinate(i, 0));
                visited.add(new Coordinate(i, 0));
            }
        } else {
            for (int i = 1; i < board.length; i += 2) {
                queue.add(new Coordinate(0, i));
                visited.add(new Coordinate(0, i));
            }
        }
        while (!queue.isEmpty()) {
            Coordinate dequeue = queue.pollFirst();
            if (dequeue.getCol() == board.length - 1 || dequeue.getRow() == board.length - 1)
                return true;
            ArrayList<Coordinate> neighbors = getNeighbors(dequeue, id, board);
            for (Coordinate neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * This function gets all neighbors of the given coordinate. Used in hasWonGame and NOT in fewestSegmentsToVictory.
     * Adapted to Part 3 implementation
     * @param coordinate coordinate object to find the neighbors of
     * @param playerID   player number of interest
     * @param board board to use
     * @return neighbors of the given node, null if no neighbors
     */
    private ArrayList<Coordinate> getNeighbors(Coordinate coordinate, int playerID, int [][] board) {
        Coordinate north = new Coordinate(coordinate.getRow() - 1, coordinate.getCol());
        Coordinate south = new Coordinate(coordinate.getRow() + 1, coordinate.getCol());
        Coordinate east = new Coordinate(coordinate.getRow(), coordinate.getCol() + 1);
        Coordinate west = new Coordinate(coordinate.getRow(), coordinate.getCol() - 1);

        ArrayList<Coordinate> neighbors = new ArrayList<>();

        if (isValid(north) && board[north.getRow()][north.getCol()] == playerID) {
            Coordinate northNeighbor = new Coordinate(coordinate.getRow() - 2, coordinate.getCol());
            neighbors.add(northNeighbor);
        }
        if (isValid(south) && board[south.getRow()][south.getCol()] == playerID) {
            Coordinate southNeighbor = new Coordinate(coordinate.getRow() + 2, coordinate.getCol());
            neighbors.add(southNeighbor);
        }
        if (isValid(east) && board[east.getRow()][east.getCol()] == playerID) {
            Coordinate eastNeighbor = new Coordinate(coordinate.getRow(), coordinate.getCol() + 2);
            neighbors.add(eastNeighbor);
        }
        if (isValid(west) && board[west.getRow()][west.getCol()] == playerID) {
            Coordinate westNeighbor = new Coordinate(coordinate.getRow(), coordinate.getCol() - 2);
            neighbors.add(westNeighbor);
        }
        return neighbors;
    }

    /**
     * Copies a given game board configuration
     * @param other game board to copy
     * @return copy of the original game board
     */
    private int [][] copyBoard(int [][] other) {

        int [][] copy = new int [other.length][other.length];
        for(int i = 0; i < other.length; i+=1) {
            for (int j = 0; j < other.length; j+=1) {
                copy[i][j] = other[i][j];
            }
        }
        return copy;
    }
    /**
     * Generates successors given a boolean value to represent the player id
     *
     * @param playerID represents the playerID to use while making successors
     * @param board current configuration of the game board
     * @return list of new board configurations
     */
    private LinkedList<int[][]> successors(int playerID, int [][] board) {
        List<PlayerMove> moves = allLegalMoves(playerID, board);
        LinkedList<int [][]> successors = new LinkedList<>();
        for (PlayerMove move : moves) {
            int [][] copyBoard = copyBoard(board);
            copyBoard[move.getCoordinate().getRow()][move.getCoordinate().getCol()] = playerID;
            successors.add(copyBoard);
        }
        return successors;
    }

    /**
     * Generates all legal moves and returns them as Coordinate objects in a list. This method is used over the
     * other when moves for a specific playerID is needed
     * @return list of PlayerMove objects of all legal moves
     */
    public List<PlayerMove> allLegalMoves(int playerID, int [][] board) {
        int row = 1;
        int col = 1;
        List<PlayerMove> moves = new ArrayList<>();
        PlayerMove move;
        int size = board[0].length;
        while (row < size - 1) {
            while (col < size - 1) {
                if ((board[row][col] == 0) && (row % 2 == 0 && col % 2 == 0) || (board[row][col] == 0) && (row % 2 == 1 && col % 2 == 1)) {
                    move = new PlayerMove(new Coordinate(row, col), playerID);
                    moves.add(move);
                }
                col++;
            }
            row++;
            col = 1;
        }
        return moves;
    }
}
