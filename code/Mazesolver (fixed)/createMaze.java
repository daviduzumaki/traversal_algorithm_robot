package mazesolver;

import java.util.ArrayList;
import java.util.Random;
  /**
         * Creates a random maze without any possibility of loops.
         * code is based off stackoverflow user 'DoubleMx2': http://stackoverflow.com/questions/18396364/maze-generation-arrayindexoutofboundsexception
         * It derives heavily from it with difference only being in variable name changes
         * to stay coherent to our already established naming scheme and the exclusion of
         * the toString and main methods in the original code.
        */

public class createMaze {
            private int row, column;         // dimension of maze
            int gridRow; // dimension of output grid
            int gridColumn; // dimension of output grid
            char[][] mazeGrid;                  // output grid
            private Node[][] nodes;                     // 2d array of Cells
            private Random random = new Random();       // The random object

            // initialize with x and y the same
            public createMaze(int n) {
                this(n, n);
            }
            // constructor
            public createMaze(int x, int y) {
                row = x;
                column = y;
                gridRow = x * 2 + 1;
                gridColumn = y * 2 + 1;
                mazeGrid = new char[gridRow][gridColumn];
                init();
                generateMaze();
            }

            private void init() {
                // create nodes
                nodes = new Node[row][column];
                for (int x = 0; x < row; x++)
                    for (int y = 0; y < column; y++)
                        nodes[x][y] = new Node(x, y, false); 
            }

            // inner class to represent a cell
            private class Node {
                int x, y; // coordinates
                // cells this cell is connected to
                ArrayList<Node> neighbors = new ArrayList<>();
                // impassable cell
                boolean wall = true;
                // if true, has yet to be used in generation
                boolean open = true;
                // construct Cell at x, y
                Node(int x, int y) {
                    this(x, y, true);
                }
                // construct Cell at x, y and with whether it isWall
                Node(int x, int y, boolean isWall) {
                    this.x = x;
                    this.y = y;
                    this.wall = isWall;
                }
                // add a neighbor to this cell, and this cell as a neighbor to the other
                void addNeighbor(Node other) {
                    if (!this.neighbors.contains(other)) // avoid duplicates
                        this.neighbors.add(other);
                    if (!other.neighbors.contains(this)) // avoid duplicates
                        other.neighbors.add(this);
                }
                // used in updateGrid()
                boolean isNodeBelowNeighbor() {
                    return this.neighbors.contains(new Node(this.x, this.y + 1));
                }
                // used in updateGrid()
                boolean isNodeRightNeighbor() {
                    return this.neighbors.contains(new Node(this.x + 1, this.y));
                }
                // useful Cell equivalence
                @Override
                public boolean equals(Object other) {
                    if (!(other instanceof Node)) return false;
                    Node otherCell = (Node) other;
                    return (this.x == otherCell.x && this.y == otherCell.y);
                }

                // should be overridden with equals
                @Override
                public int hashCode() {
                    // random hash code method designed to be usually unique
                    return this.x + this.y * 256;
                }

            }
            // generate from upper left (In computing the y increases down often)
            private void generateMaze() {
                generateMaze(0, 0);
            }
            // generate the maze from coordinates x, y
            private void generateMaze(int x, int y) {
                generateMaze(getNode(x, y)); // generate from Cell
            }
            private void generateMaze(Node startAt) {
                // don't generate from cell not there
                if (startAt == null) return;
                startAt.open = false; // indicate cell closed for generation
                ArrayList<Node> cellsList = new ArrayList<>();
                cellsList.add(startAt);

                while (!cellsList.isEmpty()) {
                    Node cell;
                    // this is to reduce but not completely eliminate the number
                    // of long twisting halls with short easy to detect branches
                    // which results in easy mazes
                    if (random.nextInt(10)==0)
                        cell = cellsList.remove(random.nextInt(cellsList.size()));
                    else cell = cellsList.remove(cellsList.size() - 1);
                    // for collection
                    ArrayList<Node> neighbors = new ArrayList<>();
                    // cells that could potentially be neighbors
                    Node[] potentialNeighbors = new Node[]{
                        getNode(cell.x + 1, cell.y),
                        getNode(cell.x, cell.y + 1),
                        getNode(cell.x - 1, cell.y),
                        getNode(cell.x, cell.y - 1)
                    };
                    for (Node other : potentialNeighbors) {
                        // skip if outside, is a wall or is not opened
                        if (other==null || other.wall || !other.open)
                            continue;
                        neighbors.add(other);
                    }
                    if (neighbors.isEmpty()) continue;
                    // get random cell
                    Node selected = neighbors.get(random.nextInt(neighbors.size()));
                    // add as neighbor
                    selected.open = false; // indicate cell closed for generation
                    cell.addNeighbor(selected);
                    cellsList.add(cell);
                    cellsList.add(selected);
                }
                updateGrid();
            }
            // used to get a Cell at x, y; returns null out of bounds
            public Node getNode(int x, int y) {
                try {
                    return nodes[x][y];
                } catch (ArrayIndexOutOfBoundsException e) { // catch out of bounds
                    return null;
                }
            }
            // draw the maze
            public void updateGrid() {
                char backChar = ' ', wallChar = 'X', cellChar = ' ';
                // fill background
                for (int x = 0; x < gridRow; x ++)
                    for (int y = 0; y < gridColumn; y ++)
                        mazeGrid[x][y] = backChar;
                // build walls
                for (int x = 0; x < gridRow; x ++)
                    for (int y = 0; y < gridColumn; y ++)
                        if (x % 2 == 0 || y % 2 == 0)
                            mazeGrid[x][y] = wallChar;
                // make meaningful representation
                for (int x = 0; x < row; x++)
                    for (int y = 0; y < column; y++) {
                        Node current = getNode(x, y);
                        int gridX = x * 2 + 1, gridY = y * 2 + 1;
                        mazeGrid[gridX][gridY] = cellChar;
                        if (current.isNodeBelowNeighbor())
                            mazeGrid[gridX][gridY + 1] = cellChar;
                        if (current.isNodeRightNeighbor())
                            mazeGrid[gridX + 1][gridY] = cellChar;
                    }
            }
        }