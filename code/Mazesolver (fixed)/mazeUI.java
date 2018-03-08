

package mazesolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.Timer;


public class mazeUI extends JPanel {
     
       
        
        // Messages to the user
        private final static String
            SELECT_MESSAGE =
                "<html><font size = \"4\">Select a search Algorithm then click 'Draw Path' or 'Real Time'</font></html> ",
            MSG_NO_SOLUTION =
                "No path to goal exists",
                
            DFS_MESSAGE = 
                "<html>Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.<BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores as far as possible along each branch before backtracking.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path.</p></html>",
        
        
            BFS_MESSAGE = 
                "<html>Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures. <BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores the neighbor nodes first, before moving to the next level neighbours.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path. BFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
                
                
            ASTAR_MESSAGE = 
                "<html>The A* algorithm combines features of uniform-cost search and pure heuristic search to efficiently compute optimal solutions.<BR>"
                + "The A*algorithm is a best-first search algorithm in which the cost associated with a node is f(n) = g(n) + h(n)<BR>"
                + "<p>where g(n) is the cost of the path from the initial state to node n and h(n) is the heuristic estimate or the cost or a path from node n to a goal.</p>"
                + " Thus, f(n) estimates the lowest total cost of any solution path going through node n."
                + "At each point a node with lowest f value is chosen for expansion."
                + "Ties among nodes of equal f value should be broken in favor of nodes with lower h values<BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "Below is shown how many nodes(squares)<BR>"
                + "A* had to search in order to determine the shortest path. A* is essentially<BR>"
                + "Dijkstra's algorithm with a heuristic</p></html>",
        
            
            GREEDY_MESSAGE = 
                "<html>A greedy algorithm is an algorithmic paradigm that follows the problem solving heuristic of making the locally optimal choice at each stage"
                + "with the hope of finding a global optimum.<BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "Greedy had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
                
                
            DIJKSTRA_MESSAGE = 
                "<html>Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks.<BR>"
                + "In this case, a maze.<BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "Dijkstra had to search in order to determine the shortest path.<BR>"
                + "Dijkstra's algorithm uses a heuristic of 0.</p></html>";
                
            
                

        
                                     
        ArrayList<Node> openSet   = new ArrayList();// the OPEN SET
        ArrayList<Node> closedSet = new ArrayList();// the CLOSED SET
        ArrayList<Node> graph     = new ArrayList();// the set of nodes of the graph
                                                    
         
        Node robotStart; // the initial position of the robot
        Node goalLocation;  // the position of the goal
      
        //Prints message to the user
        JLabel message, dfsMessage, bfsMessage, astarMessage, greedyMessage, dijkstraMessage, speedLabel; 
        

        
        // basic buttons
        JButton resetButton, mazeButton, clearButton, CPUTimeButton, drawPathButton, resetUI;
        
        
        // alogrithm button selectors
        JRadioButton dfs, bfs, aStar, greedy, dijkstra;
        
        // the slider for adjusting the speed of the animation
        JSlider speedController;
        
        JPanel algoPanel;
    

        int[][] grid;        // the grid
        boolean found;       // notify that the goal was found
        boolean searching;   // notify that the search is in progress
        boolean endOfSearch; // notify that the search is over
        boolean drawnPath;   // notifify that path is currently being drawn
        int speedControl;    // determines how fast the path will be drawn(in milliseconds)
        int expanded;        // the number of nodes that have been expanded
        
        // the object that controls the animation
        drawPath action = new drawPath();
        
        // the Timer which governs the execution speed of the animation
        Timer timer;
      
        /**
         * The Java Swing UI elements
         */
        public mazeUI(int width, int height) {
      
            //layout managed by us not the default bult in layout manager. Absolute positioning 
           super.setLayout(null);
           
           
           super.setPreferredSize( new Dimension(width,height) );
       

            grid = new int[rows][columns];

            message = new JLabel(SELECT_MESSAGE, JLabel.CENTER);
            message.setForeground(Color.WHITE);
            message.setFont(new Font("Arial",Font.PLAIN,16));
            
            
           
            dfsMessage = new JLabel(DFS_MESSAGE, JLabel.CENTER);
            dfsMessage.setForeground(Color.WHITE);
            dfsMessage.setFont(new Font("Arial",Font.PLAIN,16));
            
            bfsMessage = new JLabel(BFS_MESSAGE, JLabel.CENTER);
            bfsMessage.setForeground(Color.WHITE);
            bfsMessage.setFont(new Font("Arial",Font.PLAIN,16));
            
            astarMessage = new JLabel(ASTAR_MESSAGE, JLabel.CENTER);
            astarMessage.setForeground(Color.WHITE);
            astarMessage.setFont(new Font("Arial",Font.PLAIN,16));
            
            greedyMessage = new JLabel(GREEDY_MESSAGE, JLabel.CENTER);
            greedyMessage.setForeground(Color.WHITE);
            greedyMessage.setFont(new Font("Arial",Font.PLAIN,16));
            
            dijkstraMessage = new JLabel(DIJKSTRA_MESSAGE, JLabel.CENTER);
            dijkstraMessage.setForeground(Color.WHITE);
            dijkstraMessage.setFont(new Font("Arial",Font.PLAIN,16));


           //Color design choice dervies from Google's material design: https://material.io/guidelines/style/color.html#color-color-palette
            mazeButton = new JButton("Create Maze");
           // mazeButton.setOpaque(true);
            mazeButton.setFocusPainted(false);
            mazeButton.setBorderPainted(false);
            mazeButton.setForeground(Color.white);
            mazeButton.setBackground(Color.decode("#448AFF"));
            //calling this on actionlListener because the button contains the event handler so we're just passing the reference
            mazeButton.addActionListener(this::createMazeButtonAction);

            clearButton = new JButton("Clear");
            clearButton.setFocusPainted(false);
            clearButton.setBorderPainted(false);
            clearButton.setForeground(Color.white);
            clearButton.setBackground(Color.decode("#448AFF"));
            clearButton.addActionListener(this::clearButtonAction);

            CPUTimeButton = new JButton("<html><font size = \"2\"> Real Time(CPU Time)</font>");
            CPUTimeButton.setFocusPainted(false);
            CPUTimeButton.setBorderPainted(false);
            CPUTimeButton.setForeground(Color.white);
            CPUTimeButton.setBackground(Color.decode("#448AFF"));
            
            CPUTimeButton.addActionListener(this::CPUtimeButtonAction);
          
          
            drawPathButton = new JButton("Draw Path");
            drawPathButton.setFocusPainted(false);
            drawPathButton.setBorderPainted(false);
            drawPathButton.setForeground(Color.white);
            drawPathButton.setBackground(Color.decode("#448AFF"));
            drawPathButton.addActionListener(this::drawPathButtonActionPerformed);

            speedLabel = new JLabel("Speed(0-1 second)", JLabel.CENTER);
            speedLabel.setForeground(Color.white);
            speedLabel.setFont(new Font("Arial",Font.PLAIN,15));
            
            speedController = new JSlider(0,1000,20); // initial value of delay 1000 msec
            speedController.setForeground(Color.white);
            speedController.setBackground(Color.decode("#448AFF"));
            speedControl = speedController.getValue();
            
            // algoGroup links the radio buttons
            // so only one can be sleected at a time
            ButtonGroup algoGroup = new ButtonGroup();

            dfs = new JRadioButton("DFS");
            dfs.setFocusPainted(false);
            dfs.setBorderPainted(false);
            dfs.setForeground(Color.white);
            dfs.setBackground(Color.decode("#448AFF"));
            algoGroup.add(dfs);

            bfs = new JRadioButton("BFS");
            bfs.setFocusPainted(false);
            bfs.setBorderPainted(false);
            bfs.setForeground(Color.white);
            bfs.setBackground(Color.decode("#448AFF"));
            algoGroup.add(bfs);

            aStar = new JRadioButton("A*");
            aStar.setFocusPainted(false);
            aStar.setBorderPainted(false);
            aStar.setForeground(Color.white);
            aStar.setBackground(Color.decode("#448AFF"));
            algoGroup.add(aStar);

            greedy = new JRadioButton("Greedy");
            greedy.setFocusPainted(false);
            greedy.setBorderPainted(false);
            greedy.setForeground(Color.white);
            greedy.setBackground(Color.decode("#448AFF"));
            algoGroup.add(greedy);

            dijkstra = new JRadioButton("Dijkstra");
            dijkstra.setFocusPainted(false);
            dijkstra.setBorderPainted(false);
            dijkstra.setForeground(Color.white);
            dijkstra.setBackground(Color.decode("#448AFF"));
            algoGroup.add(dijkstra);

            algoPanel = new JPanel();
            algoPanel.setBorder(javax.swing.BorderFactory.
                    createTitledBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#448AFF")),
                    "<html><font color = \"White\">Search Algorithms</font></html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 14)));
            algoPanel.setForeground(Color.white);
            algoPanel.setBackground(Color.decode("#448AFF"));
             algoPanel.setOpaque(true);
            
          
            resetUI = new JButton("Reset");
            resetUI.setFocusPainted(false);
            resetUI.setBorderPainted(false);
            resetUI.setForeground(Color.white);
            resetUI.setBackground(Color.decode("#448AFF"));
            resetUI.addActionListener(this::resetUIButtonActionPerformed);
             
             
            dfs.setSelected(true);  // DFS is initially selected 
            
          
            /** super is calling the Jpanel class. 
             * Here we're addng all our Jbuttons and Jabels to be printed to screen.
             */
            super.add(message);
            super.add(mazeButton);
            super.add(clearButton);
            super.add(CPUTimeButton);        
            super.add(drawPathButton);
            super.add(speedLabel);
            super.add(speedController);
            super.add(dfs);
            super.add(bfs);
            super.add(aStar);
            super.add(greedy);
            super.add(dijkstra);
            super.add(algoPanel);  
           // end Jpanel element printing.
           
            //(dimensionX in Pixels, dimensionY in Pixels, width of actual Jelement, height of actual Jelement)
            message.setBounds(58, 850, 400, 75);
            dfsMessage.setBounds(40,450, 400, 500);
            bfsMessage.setBounds(40,450, 400, 500);
            astarMessage.setBounds(40,429, 400, 500);
            greedyMessage.setBounds(40,450, 400, 500);
            dijkstraMessage.setBounds(40,450, 400, 500);
            mazeButton.setBounds(10, 520, 140, 50); 
            CPUTimeButton.setBounds(10,580, 140, 50);   
            drawPathButton.setBounds(10, 640, 140, 50);
            speedController.setBounds(10, 730, 200, 40);
            speedLabel.setBounds(15, 760, 190, 40);
            clearButton.setBounds(10,820, 140, 50);           
            algoPanel.setLocation(250,520);
            algoPanel.setSize(220, 150);
            dfs.setBounds(255, 545, 50, 20);
            bfs.setBounds(255, 575, 70, 25);
            aStar.setBounds(255, 610, 70, 25);
            greedy.setBounds(360, 545, 85, 25);
            dijkstra.setBounds(360, 575, 85,25);
            resetUI.setBounds(10,510, 25, 25);
        

            // we create the timer
           timer = new Timer(speedControl, action);
            
            /** The maze is not initialized at the start as it should only be done so
              * when the user clicks "create Maze"
              */
            initializeMaze(false);

        } // end constructor
           private final static int
            INFINITY = Integer.MAX_VALUE, // Infinity
            EMPTY    = 0,  // empty cell
            OBST     = 1,  // cell with obstacle
            ROBOT    = 4,  // the position of the robot
            TARGET   = 3,  // the position of the target
            OPEN     = 7,  // open set
            CLOSED   = 5,  // closed set
            ROUTE    = 6;  // cells that form the robot-to-target path
           
         /**
          * We built this project on a 3440x1440 basis. We later realized the monitors in the lab are 1920x1080
          * We omitted the responsive lines of code as they were unfinished. However, we intend to implement them after the 
          * projet has been demonstrated. As of writing this it is 28/2/18 and do not have the time to fully implement responsiveness.
          * 
          *  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
          *  int gridResponsiveSizer  = (int) screenSize.getWidth();
          */
           
         int rows    = 70,           // the number of rows of the grid
             columns = 70,           // the number of columns of the grid
             gridSize = 500/rows;    //size of grid in pixels. Should coorelate with dimensions of the Jframe
         
         
        private void initializeMaze(Boolean makeMaze) {                                           
            //Boolean is acting like a flag
            // if the rows or columns are even make them odd. 
            //This gives space for the maze to be placed in the grid.
            if (makeMaze && rows % 2 == 0)
                rows -= 1;
            if (makeMaze && columns % 2 == 0)
                columns -= 1;
   
            grid = new int[rows][columns];
           
            robotStart = new Node(rows+4,5);
            goalLocation = new Node(1,columns-2);
            initializeGrid();
            if (makeMaze) {
                createMaze maze = new createMaze(rows/2,columns/2);
                for (int x = 0; x < maze.gridRow; x++)
                    for (int y = 0; y < maze.gridColumn; y++)
                        if (maze.mazeGrid[x][y] == 'X')  // wallChar from the createMaze class
                            grid[x][y] = OBST;
            }
        } // end initializeGrid()
   
        /**
         * Initial values ​​for the Nodes in the grid.
         */
        private void initializeGrid() {
            /**
             * With the first click on button 'Clear' clears the data
             * of any search that performed and leaves the obstacles
             * ready for another search to take place within the same generated Maze.
             */
            
            if (searching || endOfSearch){ 
                for (int r = 0; r < rows; r++)
                    for (int c = 0; c < columns; c++) {
                        if (grid[r][c] == OPEN || grid[r][c] == CLOSED || grid[r][c] == ROUTE)
                            grid[r][c] = EMPTY;
                        if (grid[r][c] == ROBOT)
                            robotStart = new Node(r,c);
                        if (grid[r][c] == TARGET)
                            goalLocation = new Node(r,c);
                    }
                searching = false;
            } else {
                for (int r = 0; r < rows; r++)
                    for (int c = 0; c < columns; c++)
                        grid[r][c] = EMPTY;
                robotStart = new Node(rows-2,1);
                goalLocation = new Node(1,columns-2);
            }
            if (aStar.isSelected() || greedy.isSelected()){
                robotStart.g = 0;
                robotStart.h = 0;
                robotStart.f = 0;
            }
            expanded = 0;
            found = false;
            searching = false;
            endOfSearch = false;
         
            // The first step of the other four algorithms is here
            // 1. OPEN SET: = [Initial State of Robot], CLOSED SET: = []
            openSet.removeAll(openSet);
            openSet.add(robotStart);
            closedSet.removeAll(closedSet);
         
            grid[goalLocation.row][goalLocation.col] = TARGET; 
            grid[robotStart.row][robotStart.col] = ROBOT;
            message.setText(SELECT_MESSAGE);
            timer.stop();
            repaint();
            
        } // end fillGrid()

        /**
         * Enables radio buttons and checkboxes
         */
        private void enableRadiosAndChecks() {                                           
            speedController.setEnabled(true);
            dfs.setEnabled(true);
            bfs.setEnabled(true);
            aStar.setEnabled(true);
            greedy.setEnabled(true);
            dijkstra.setEnabled(true);
          
        } // end enableRadiosAndChecks()
    
        /**
         * By default the search algorithm radio buttons are not enabled
         * And will only be enabled individually when the respective radio button is clicked
         */
        private void algoRadioButtonDisabling() {                                           
            speedController.setEnabled(false);
            dfs.setEnabled(false);
            bfs.setEnabled(false);
            aStar.setEnabled(false);
            greedy.setEnabled(false);
            dijkstra.setEnabled(false);
          
        } // end disableRadiosAndChecks()
    
        /**
         * When "Create Maze" is clicked
         */
        private void createMazeButtonAction(java.awt.event.ActionEvent evt) {
            drawnPath = false;
        
            CPUTimeButton.setEnabled(true);
            CPUTimeButton.setForeground(Color.black);
            
            drawPathButton.setEnabled(true);
            enableRadiosAndChecks();
            initializeMaze(true);
        } // end mazeButtonAction()
    
        /**
         * When "Clear" is clicked
         */
        private void clearButtonAction(java.awt.event.ActionEvent evt) {
            drawnPath = false;
       
            CPUTimeButton.setEnabled(true);
            CPUTimeButton.setForeground(Color.black);
            
            drawPathButton.setEnabled(true);
            enableRadiosAndChecks();
            initializeGrid();
        } // end clearButtonAction()
    
        /**
         *  When "Real Time" is clicked
         */
        private void CPUtimeButtonAction(java.awt.event.ActionEvent evt) {
            // The Dijkstra's initialization should be done just before the
            // start of search, because obstacles must be in place.
            if (dijkstra.isSelected())
               initializeDijkstra();
            algoRadioButtonDisabling();
            CPUtimeAction();
        } // end CPUtimeButtonAction()
    
        /**
         * Action performed during CPU-time search
         */
        public void CPUtimeAction() {
            do
                checkTermination();
            while (!endOfSearch);
        } // end of realTimeAction()

    
        /**
         * When the user clicks "Draw Path". 
         */
        private void drawPathButtonActionPerformed(java.awt.event.ActionEvent evt) {
            drawnPath = true;
            if (!searching && dijkstra.isSelected())
                initializeDijkstra();
            searching = true;
           
            CPUTimeButton.setEnabled(false);
            algoRadioButtonDisabling();
            speedController.setEnabled(true);
            speedControl = speedController.getValue();
            timer.setDelay(speedControl);
            timer.start();
        } // end drawPathButtonActionPerformed()
    
        /**
         * Class that handles animation. 
         */
        private class drawPath implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTermination();
                repaint();
                if (endOfSearch)
                {
                    drawnPath = false;
                    timer.stop();
                }
            }
        } // end drawPath
          
          
        /**
         * 
         * When the user clicks "Reset"
         */
        private void resetUIButtonActionPerformed(java.awt.event.ActionEvent evt) {
                super.add(mazeButton);
                super.add(clearButton);
                super.add(CPUTimeButton);
                super.add(drawPathButton);
                super.add(speedController);
                super.add(speedLabel);
                super.add(dfs);
                super.add(bfs);
                super.add(dijkstra);
                super.add(greedy);
                super.add(aStar);
                super.add(algoPanel); 
                super.repaint();
                super.remove(resetUI);
                if (dfs.isSelected()){
                    super.remove(dfsMessage);
                } else if (bfs.isSelected()){
                    super.remove(bfsMessage);
                } else if (aStar.isSelected()){
                  super.remove(astarMessage);
                } else if (greedy.isSelected()){
                  super.remove(greedyMessage);
                } else if (dijkstra.isSelected()){
                  super.remove(dijkstraMessage);
                }
           
        } // end resetUIButtonActionPerformed()
          
        /**
         * Checks if we have reached the end of search
         */
        public void checkTermination() {
            // Here we decide whether we can continue the search or not.
            
            // In the case of Dijkstra's algorithm
            // here we check condition of step 11:
            // 11. while Q is not empty.
            
            // In the case of DFS, BFS, A* and Greedy algorithms
            // here we have the second step:
            // 2. If OPEN SET = [], then terminate. There is no solution.
            if ((dijkstra.isSelected() && graph.isEmpty()) ||
                          (!dijkstra.isSelected() && openSet.isEmpty()) ) {
                endOfSearch = true;
                grid[robotStart.row][robotStart.col]=ROBOT;
                message.setText(MSG_NO_SOLUTION);
                
                
                drawPathButton.setEnabled(false);
                repaint();
            } else {
                expandNode();
                if (found) {
                    endOfSearch = true;
                    plotRoute();
                    
                    
                    drawPathButton.setEnabled(false);
                    speedController.setEnabled(false);
                    
                    repaint();
                    if (endOfSearch = true & dfs.isSelected()){
                        disableUIElements();
                        super.add(dfsMessage);       
                    } else if (endOfSearch = true & bfs.isSelected()){
                        disableUIElements();
                        super.add(bfsMessage);
                    } else if (endOfSearch = true & aStar.isSelected()){
                        disableUIElements();
                        super.add(astarMessage);
                    } else if (endOfSearch = true & greedy.isSelected()){
                        disableUIElements();
                        super.add(greedyMessage);
                    } else if (endOfSearch = true & dijkstra.isSelected()){
                        disableUIElements();
                        super.add(dijkstraMessage);
                    }                   
                }
            }
        }
       
        /**
         * Hide the J-elements below the maze
         */
       
        private void disableUIElements(){
            super.remove(mazeButton);
            super.remove(clearButton);
            super.remove(CPUTimeButton);
            super.remove(drawPathButton);
            super.remove(speedController);
            super.remove(speedLabel);
            super.remove(dfs);
            super.remove(bfs);
            super.remove(dijkstra);
            super.remove(greedy);
            super.remove(aStar);
            super.remove(algoPanel);  
            super.add(resetUI);
            super.revalidate();
            super.repaint();
           
        } // end disableUIElements()
        

        /**
         * Expands a node and creates his successors
         * n: = Pseudo code from Wikipedia  (https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
         * and pseudo code mentioned in our blog.(https://davidproject.tumblr.com/post/171475569178/the-rest-of-the-algorithms)
         * Here we're changing the name of u with nodeLeastDist, v to prevNode
         * and alt to altNode. altNode isn't an actual Node object but just a convenient name.
         * 
         */
        private void expandNode(){
            // Dijkstra's algorithm is based off distance between nodes so that is handled seperately.
            if (dijkstra.isSelected()){
                Node nodeLeastDist;
                // 12: while Q is not empty:
                if (graph.isEmpty())
                    return;
                // 13:  u := Node in Q (graph) with smallest distance in dist[] ;
                // 15:  remove u from Q (graph);
                nodeLeastDist = graph.remove(0);
                // Add Node u in closed set
                closedSet.add(nodeLeastDist);
                // If target has been found ...
                if (nodeLeastDist.row == goalLocation.row && nodeLeastDist.col == goalLocation.col){
                    found = true;
                    return;
                }
                // Counts Nodes that have been expanded.
                expanded++;
                // Update the color of the Node
                grid[nodeLeastDist.row][nodeLeastDist.col] = CLOSED;
                // 6: if dist[u] = infinity:
                if (nodeLeastDist.dist == INFINITY){
                    // ... then there is no solution.
                    // 15: break;
                    return;
                } 
                // Create the neighbors of u
                ArrayList<Node> neighbors = createSuccesors(nodeLeastDist, false);
                // 17: for each neighbor v of u:
                for (Node prevNode: neighbors) {
                    // 18: alt := dist[u] + dist_between(u, v) ;
                    double altNode = nodeLeastDist.dist + distBetween(nodeLeastDist,prevNode);
                    // 19: if alt < dist[v]:
                    if (altNode < prevNode.dist) {
                        // 22: dist[v] := alt ;
                        prevNode.dist = altNode;
                        // 23: previous[v] := u ;
                        prevNode.prev = nodeLeastDist;
                        // Update the color of the node
                        grid[prevNode.row][prevNode.col] = OPEN;
                        // (sort list of nodes with respect to dist)
                        Collections.sort(graph, new NodebyDist());
                    }
                }
            } else { // The handling of the other four algorithms
                Node current;
                if (dfs.isSelected() || bfs.isSelected()) {
                    //2: Remove the first state from the OPEN SET ...
                    current = openSet.remove(0);
                } else {
                    // Here is the 2nd step of the A* and Greedy
                    // 2: Remove the first state from the OPEN SET,
                    // for which f(Node) ≤ f(Node) for all Open Nodes.
                    // (sort first OPEN SET list with respect to 'f')
                    Collections.sort(openSet, new NodebyF());
                    current = openSet.remove(0);
                }
                // ... and add it to the CLOSED SET.
                closedSet.add(0,current);
                // Update the color of the node
                grid[current.row][current.col] = CLOSED;
                // If the selected node is the target ...
                if (current.row == goalLocation.row && current.col == goalLocation.col) {
                    // ... then terminate.
                    Node last = goalLocation;
                    last.prev = current.prev;
                    closedSet.add(last);
                    found = true;
                    return;
                }
                // Count nodes that have been expanded.
                expanded++;
                // 3: Create the successors of the current Node.
                //    Each successor has a pointer to the Node, as its predecessor.
                //    In the case of DFS and BFS algorithms, successors should not
                //    belong to the OPEN SET or the CLOSED SET.
                ArrayList<Node> succesors;
                succesors = createSuccesors(current, false);
                // step 3(last part) For each successor of Node...
                for (Node node: succesors){
                    // ... if we are running DFS ...
                    if (dfs.isSelected()) {
                        // ... add the successor to the start of the OPEN SET
                        openSet.add(0, node);
                        // Update the color of the node
                        grid[node.row][node.col] = OPEN;
                        // ... if we are runnig BFS ...
                    } else if (bfs.isSelected()){
                        // ... add the successor at the end of the list OPEN SET
                        openSet.add(node);
                        // Update the color of the cell
                        grid[node.row][node.col] = OPEN;
                        // ... if we are running A* or Greedy algorithms (step 5 of A* algorithm) ...
                    } else if (aStar.isSelected() || greedy.isSelected()){
                        // ... calculate the value f(Node) ...
                        // dxg = real cost of x position for g(n)
                        // dyg = real cost of y position for g(n)
                        // dxh = heuristic estimate of x position for h(n)
                        // dxh = heuristic estimate of y position for h(n)
                        int dxg = current.col-node.col;
                        int dyg = current.row-node.row;
                        int dxh = goalLocation.col-node.col;
                        int dyh = goalLocation.row-node.row;
                      
                       
                            // calculate the Manhattan distance which is used for the heuristic estimate
                            if (greedy.isSelected()) {
                                // Greedy is A* without g(n)
                                node.g = 0;
                            } else {
                                node.g = current.g + Math.abs(dxg)+Math.abs(dyg);
                            }
                            node.h = Math.abs(dxh)+Math.abs(dyh);
                        
                        node.f = node.g+node.h;
                        // If the Node is not in the Open or Close set...
                        int openIndex   = getNodeIndex(openSet,node);
                        int closedIndex = getNodeIndex(closedSet,node);
                        if (openIndex == -1 && closedIndex == -1) {
                            // then add the Node to the Open Set..
                            // ... evaluated as f(nodeSucessor)
                            openSet.add(node);
                            // Update the color of the node
                            grid[node.row][node.col] = OPEN;
                            // Else ...
                        } else {
                            // ... if the Node already belongs to the OPEN SET, then ...
                            if (openIndex > -1){
                                // ... compare the new value assessment with the old one. 
                                // If old <= new ...
                                if (openSet.get(openIndex).f <= node.f) {
                                    // ... then replace the new Node successor with the old one
                                    // Else...
                                } else {
                                    // ... remove the old Node sucessor from the list
                                    openSet.remove(openIndex);
                                    // ... and add the new Node successor to the OPEN SET.
                                    openSet.add(node);
                                    // Update the color of the node
                                    grid[node.row][node.col] = OPEN;
                                }
                                // If the Node already belongs to the Closed Set then...
                            } else {
                                // ... compare the new value assessment with the old one. 
                                // If old <= new ...
                                if (closedSet.get(closedIndex).f <= node.f) {
                                     // ... then replace the new Node successor with the old one
                                    // Else...
                                } else {
                                    // ... remove the old Node sucessor from the list
                                    // to which it belongs ...
                                    closedSet.remove(closedIndex);
                                    // ... and add the new Node successor to the OPEN SET.
                                    openSet.add(node);
                                    // Update the color of the node
                                    grid[node.row][node.col] = OPEN;
                                }
                            }
                        }
                    }
                }
            }
        } //end expandNode()
        
        /**
         * Creates the successors of a Node.
         * 
         * @param current       The Node we're asking successors from
         * @param makeConnected flag that indicates that we are interested only in the coordinates
         *                      of Nodes and not the 'dist' value (concerns only Dijkstra's)
         * 
         * @return              the successors of the Node as a list
         */
        public ArrayList<Node> createSuccesors(Node current, boolean makeConnected){
            int r = current.row;
            int c = current.col;
            // Empty list for the successors of the current Node
            ArrayList<Node> temp = new ArrayList<>();
         
            
            //  Movement priority is:
            // 1: Up 2: Right 3: Down 4: Left
            
            // If not at the topmost limit of the grid
            // and the immediate Node above is not an obstacle...
            if (r > 0 && grid[r-1][c] != OBST &&
                    // ... and (only in the case are not running the A* or Greedy)
                    // not already belongs neither to the OPEN SET nor to the CLOSED SET ...
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          getNodeIndex(openSet,new Node(r-1,c)) == -1 &&
                          getNodeIndex(closedSet,new Node(r-1,c)) == -1)) {
                Node node = new Node(r-1,c);
                /*
                * In the case of Dijkstra you can't just append a Node to the
                * list of successors without the "dist" value. So we track this down
                * through the "graph" list and copy it into the list of successors.
                *
                * makeConnected is acting as a flag (if connected then...) to allow
                * createSuccessors() to work with findConnectedNode()
                * which creates the connected Node when Dijkstra's intiliazes.
                */
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(node);
                    else {
                        int graphIndex = getNodeIndex(graph,node);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                    // ... update the pointer of the above node so it points the current one ...
                    node.prev = current;
                    // ... and add the above node to the successors of the current one. 
                    temp.add(node);
                 }
            }
          
            // If not at the rightmost limit of the grid
            // and the right-side node is not an obstacle ...
            if (c < columns-1 && grid[r][c+1] != OBST &&
                    // ... and (only in the case A* or Greedy isn't running because A* and Greedy update Nodes in the Open and closed set)
                    // the node doesn't belong to the Open or Closed Set
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected())? true :
                          getNodeIndex(openSet,new Node(r,c+1)) == -1 &&
                          getNodeIndex(closedSet,new Node(r,c+1)) == -1)) {
                Node node = new Node(r,c+1);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(node);
                    else {
                        int graphIndex = getNodeIndex(graph,node);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                    // ... update the pointer of the right-side node so it points the current one ...
                    node.prev = current;
                    // ... and add the right-side node to the successors of the current one. 
                    temp.add(node);
                }
            }
           
            // If not at the lowermost limit of the grid
            // and the node below is not an obstacle ...
            if (r < rows-1 && grid[r+1][c] != OBST &&
                    // ... and (only in the case A* or Greedy isn't running because A* and Greedy update Nodes in the Open and closed set)
                    // the node doesn't belong to the Open or Closed Set
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          getNodeIndex(openSet,new Node(r+1,c)) == -1 &&
                          getNodeIndex(closedSet,new Node(r+1,c)) == -1)) {
                Node node = new Node(r+1,c);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(node);
                    else {
                        int graphIndex = getNodeIndex(graph,node);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                   // ... update the pointer of the Node below so it points the current one ...
                    node.prev = current;
                    // ... and add the Node below to the successors of the current one. 
                    temp.add(node);
                }
            }
         
            // If not at the leftmost limit of the grid
            // and the left-side Node is not an obstacle ...
            if (c > 0 && grid[r][c-1] != OBST && 
                    // ... and (only in the case A* or Greedy isn't running because A* and Greedy update Nodes in the Open and closed set)
                    // the node doesn't belong to the Open or Closed Set
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          getNodeIndex(openSet,new Node(r,c-1)) == -1 &&
                          getNodeIndex(closedSet,new Node(r,c-1)) == -1)) {
                Node node = new Node(r,c-1);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(node);
                    else {
                        int graphIndex = getNodeIndex(graph,node);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                   // ... update the pointer of the left-side Node so it points the current one ...
                    node.prev = current;
                    // ... and add the left-side Node to the successors of the current one. 
                    temp.add(node);
                }
            }
          
            // When DFS algorithm is in use, Nodes are added one by one at the beginning of the
            // OPEN SET list. Because of this, we must reverse the order of successors formed,
            // so the successor corresponding to the highest priority, to be placed
            // the first in the list.
            // For the Greedy, A* and Dijkstra's no issue, because the list is sorted
            // according to 'f' or 'dist'.
            if (dfs.isSelected())
                Collections.reverse(temp);
            
            for (int i = 0; i < temp.size(); i++){
                System.out.println(temp.get(i));
            }
            
            return temp;
            
        } // end createSuccesors()
        
        /**
         * Returns the distance between two Nodes
         */
        private double distBetween(Node u, Node v){
            double dist;
            int dx = u.col-v.col;
            int dy = u.row-v.row;
           
              
                // calculate the Manhattan distance
                dist = Math.abs(dx)+Math.abs(dy);
            
            return dist;
        } // end distBetween()
        
        /**
         * Returns the index of the "current" Node in the "list"
         */
        private int getNodeIndex(ArrayList<Node> list, Node current){
            int index = -1;
            for (int i = 0 ; i < list.size(); i++) {
                Node node = list.get(i);
                if (current.row == node.row && current.col == node.col) {
                    index = i;
                    break;
                }
            }
            return index;
        } // end isInList()
        
        /**
         * Calculates the path from goal to the robot.
         * Counts the corresponding steps
         * and measures the distance traveled.
         */
        private void plotRoute(){
            int steps = 0;
            double distance = 0;
            int index = getNodeIndex(closedSet,goalLocation);
            Node current = closedSet.get(index);
            grid[current.row][current.col]= TARGET;
            do {
                steps++;
                
                    distance++;
                current = current.prev;
                grid[current.row][current.col] = ROUTE;
            } while (!(current.row == robotStart.row && current.col == robotStart.col));
            grid[robotStart.row][robotStart.col]=ROBOT;
            String msg;
            String colorMsg = "<html><font color = \"#448AFF\">Nodes expanded: %d, Shortest Path: %d </font></html>";
            msg = String.format(colorMsg,
                     expanded,steps,distance); 
            message.setText(msg);
          
        } // end plotRoute()
        
        /**
         * Appends to the list containing the Nodes of the graph only
         * the Nodes belonging to the same vicinity with Node current.
         * This is a Breadth First Search of the graph starting from node current.
         */
        private void findConnectedNode(Node current){
            Stack<Node> stack;
            stack = new Stack();
            ArrayList<Node> succesors;
            stack.push(current);
            graph.add(current);
            while(!stack.isEmpty()){
                current = stack.pop();
                succesors = createSuccesors(current, true);
                for (Node i: succesors) {
                    if (getNodeIndex(graph, i) == -1){
                        stack.push(i);
                        graph.add(i);
                    }
                }
            }
        } // end findConnectedComponent()
        
        /**
         * Initialization of Dijkstra's algorithm.
         * 
         * Robot is still looking for the target while there are still nodes in the open set.
         * Only when there's nothing left in open set and the target hasn't been found after
         * the search has ended will there be no solution
         * The program acts as a connected graph search method.
         * Meaning, it will only work if the robot is searching Nodes that are in the
         * same vicinity as the robot.
         * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
         */
       
        private void initializeDijkstra() {
     
            // 1: Create the connected Node from the position of the 
            // initial position of the robot
            graph.removeAll(graph);
            findConnectedNode(robotStart);
            // Here is the initialization of Dijkstra's algorithm 
            
            // 5: for each Node n in Graph;
            for (Node n: graph) {
                // 6: dist[n] := infinity ; This is because the path to the goal is unknown
                // but potentially exists.
                n.dist = INFINITY;
                // Step 7: previous[n] := undefined ;
                n.prev = null;
            }
            // 10: dist[robotStart] := 0; Distance from robotStart position to itself.
            graph.get(getNodeIndex(graph,robotStart)).dist = 0;
            // 12: Q := the set of all nodes in Graph;
            // Instead of the variable Q we will use the list
            // 'graph' itself, which has already been initialised.            

            // Sorts the list of nodes with respect to distance.
            Collections.sort(graph, new NodebyDist());
            
            // Then we set all Nodes in the closed set to not visited.
            // In case anything is carried over from the last time the program was ran.
            closedSet.removeAll(closedSet);
        } // end initializeDijkstra()
        

        /**
         * Draw and colourize grid
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
             // Fills the background color.
            g.setColor(Color.DARK_GRAY);
           
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if (grid[r][c] == EMPTY) {
                        g.setColor(Color.WHITE);
                    } else if (grid[r][c] == ROBOT) {
                        g.setColor(Color.RED);
                    } else if (grid[r][c] == TARGET) {
                        g.setColor(Color.GREEN);
                    } else if (grid[r][c] == OBST) {
                        g.setColor(Color.BLACK);
                    } else if (grid[r][c] == OPEN) {
                        g.setColor(Color.BLUE);
                    } else if (grid[r][c] == CLOSED) {
                        g.setColor(Color.MAGENTA);
                    } else if (grid[r][c] == ROUTE) {
                        g.setColor(Color.YELLOW);
                    }
                    // fillRect(position x,position y, size width, size height)
                    //gridSize to eliminate lines. -1 is used in order to offset the color position.
                    g.fillRect(11 + c*gridSize, 11 + r*gridSize, gridSize-1 , gridSize-1 );
                }
            }
           
            
           
        } // end paintComponent()
        
      
    } // end MazuUI Panel
  
    
    
