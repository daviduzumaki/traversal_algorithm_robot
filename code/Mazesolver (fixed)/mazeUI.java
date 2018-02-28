

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


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
                + "DFS had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
        
        
            BFS_MESSAGE = 
                "<html>Breadth (DFS) is an algorithm for traversing or searching tree or graph data structures.<BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores as far as possible along each branch before backtracking.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
                
                
            ASTAR_MESSAGE = 
                "<html>A star(DFS) is an algorithm for traversing or searching tree or graph data structures.<BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores as far as possible along each branch before backtracking.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
        
            
            GREEDY_MESSAGE = 
                "<html>greedy(DFS) is an algorithm for traversing or searching tree or graph data structures.<BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores as far as possible along each branch before backtracking.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>",
                
                
            DIJKSTRA_MESSAGE = 
                "<html>dihj is an algorithm for traversing or searching tree or graph data structures.<BR>"
                + "One starts at the root (The square in <font color =\"red\">red</font> is the root in this case)<BR>"
                + "<p>and explores as far as possible along each branch before backtracking.</p><BR>"
                + "Pay attention to the path highlighted in <font color =\"yellow\">yellow</font> which indicates the shortest path.<BR>"
                + "The <font color =\"#FF00FF\">magenta</font> coloured squares represent the closed set (visited nodes).<BR>"
                + "The <font color =\"#0080ff\">blue</font> squares represent the nodes in the Open set (to be searched).<BR>"
                + "The <font color =\"#24ae24\">green</font> square is the goal. <p>Below is shown how many nodes(squares)<BR>"
                + "DFS had to search in order to determine the shortest path. DFS is essentially<BR>"
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>";
                
            
                

        
                                      // pointing the predecessor cell
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
        boolean realTime;    // Solution is displayed instantly
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
            Border margin = new EmptyBorder(5,15,5,15);
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
            //realTimeButton.addActionListener(new ActionHandler());
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

            speedLabel = new JLabel("Speed(0-100 ms)", JLabel.CENTER);
            speedLabel.setForeground(Color.white);
            speedLabel.setFont(new Font("Arial",Font.PLAIN,15));
            
            speedController = new JSlider(0,100,20); // initial value of delay 100 msec
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
                    createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(),
                    "Search Algorithms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
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
            
          
            //* super is calling the Jpanel class. Here we're addng all our Jbuttons and Jabels to be printed to screen.
         
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
           
            
            // end Jpanel content printing.
           

           
            //(dimensionX in Pixels, dimensionY in Pixels, width of actual Jelement, height of actual Jelement)
            
   
            message.setBounds(58, 850, 400, 75);
            dfsMessage.setBounds(40,450, 400, 500);
            bfsMessage.setBounds(40,450, 400, 500);
            astarMessage.setBounds(40,450, 400, 500);
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
            
            /**The maze is not initialized at the start as it should only be done so
              * when the user clicks "create Maze"
              * */
            initializeMaze(false);

        } // end constructor

        /**
         * Creates a new clean grid or a new maze
         */
        
        
        
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
           
         int rows    = 40,           // the number of rows of the grid
             columns = 40,           // the number of columns of the grid
             gridSize = 500/rows;  //size of grid in pixels. Should coorelate with dimensions of the Jframe
         
        private void initializeMaze(Boolean makeMaze) {                                           
          
            // if the rows or columns are even make them odd. This gives space for the maze to be placed in the grid.
            if (makeMaze && rows % 2 == 0)
                rows -= 1;
            if (makeMaze && columns % 2 == 0)
                columns -= 1;
   
            grid = new int[rows][columns];
            //Node(y,x)
          //  robotStart = new Node(rows-2,1);
            //goalLocation = new Node(1,columns-2);
            fillGrid();
            if (makeMaze) {
                createMaze maze = new createMaze(rows/2,columns/2);
                for (int x = 0; x < maze.gridRow; x++)
                    for (int y = 0; y < maze.gridColumn; y++)
                        if (maze.mazeGrid[x][y] == 'X')  // wallChar
                            grid[x][y] = OBST;
            }
        } // end initializeGrid()
   
        /**
         * Gives initial values ​​for the cells in the grid.
         */
        private void fillGrid() {
            /**
             * With the first click on button 'Clear' clears the data
             * of any search was performed (Frontier, Closed Set, Route)
             * and leaves intact the obstacles and the robot and target positions
             * in order to be able to run another algorithm
             * with the same data.
             * We use logical or because if the program is searching (if it's true) it doesn't matter
             * if we reached the end of the search
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
            // 1. OPEN SET: = [So], CLOSED SET: = []
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
            realTime = false;
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
            realTime = false;
            CPUTimeButton.setEnabled(true);
            CPUTimeButton.setForeground(Color.black);
            
            drawPathButton.setEnabled(true);
            enableRadiosAndChecks();
            fillGrid();
        } // end clearButtonAction()
    
        /**
         * Executes when the user presses the button "Real-Time"
         */
        private void CPUtimeButtonAction(java.awt.event.ActionEvent evt) {
            if (realTime)
                return;
            realTime = true;
            searching = true;
            // The Dijkstra's initialization should be done just before the
            // start of search, because obstacles must be in place.
            if (dijkstra.isSelected())
               initializeDijkstra();
            CPUTimeButton.setForeground(Color.green);
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
         * Conditional and is used because if the program is searching the result will be false so it 
         * doesn't matter is dijkstra is selected or not
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
         * The class that is responsible for the animation
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
         */
        private void expandNode(){
            // Dijkstra's algorithm to handle separately
            if (dijkstra.isSelected()){
                Node nodeWithLeastDist;
                // 11: while Q is not empty:
                if (graph.isEmpty())
                    return;
                // 12:  u := vertex in Q (graph) with smallest distance in dist[] ;
                // 13:  remove u from Q (graph);
                nodeWithLeastDist = graph.remove(0);
                // Add vertex u in closed set
                closedSet.add(nodeWithLeastDist);
                // If target has been found ...
                if (nodeWithLeastDist.row == goalLocation.row && nodeWithLeastDist.col == goalLocation.col){
                    found = true;
                    return;
                }
                // Counts nodes that have expanded.
                expanded++;
                // Update the color of the cell
                grid[nodeWithLeastDist.row][nodeWithLeastDist.col] = CLOSED;
                // 14: if dist[u] = infinity:
                if (nodeWithLeastDist.dist == INFINITY){
                    // ... then there is no solution.
                    // 15: break;
                    return;
                } // 16: end if
                // Create the neighbors of u
                ArrayList<Node> neighbors = createSuccesors(nodeWithLeastDist, false);
                // 18: for each neighbor v of u:
                for (Node prevNodeInOptimalPath: neighbors) {
                    // 20: alt := dist[u] + dist_between(u, v) ;
                    double alternateNode = nodeWithLeastDist.dist + distBetween(nodeWithLeastDist,prevNodeInOptimalPath);
                    // 21: if alt < dist[v]:
                    if (alternateNode < prevNodeInOptimalPath.dist) {
                        // 22: dist[v] := alt ;
                        prevNodeInOptimalPath.dist = alternateNode;
                        // 23: previous[v] := u ;
                        prevNodeInOptimalPath.prev = nodeWithLeastDist;
                        // Update the color of the cell
                        grid[prevNodeInOptimalPath.row][prevNodeInOptimalPath.col] = OPEN;
                        // 24: decrease-key v in Q;
                        // (sort list of nodes with respect to dist)
                        Collections.sort(graph, new NodebyDist());
                    }
                }
            } else { // The handling of the other four algorithms
                Node current;
                if (dfs.isSelected() || bfs.isSelected()) {
                    // Here is the 3rd step of the algorithms DFS and BFS
                    // 3. Remove the first state, Si, from OPEN SET ...
                    current = openSet.remove(0);
                } else {
                    // Here is the 3rd step of the algorithms A* and Greedy
                    // 3. Remove the first state, Si, from OPEN SET,
                    // for which f(Si) ≤ f(Sj) for all other
                    // open states Sj  ...
                    // (sort first OPEN SET list with respect to 'f')
                    Collections.sort(openSet, new NodebyF());
                    current = openSet.remove(0);
                }
                // ... and add it to CLOSED SET.
                closedSet.add(0,current);
                // Update the color of the cell
                grid[current.row][current.col] = CLOSED;
                // If the selected node is the target ...
                if (current.row == goalLocation.row && current.col == goalLocation.col) {
                    // ... then terminate etc
                    Node last = goalLocation;
                    last.prev = current.prev;
                    closedSet.add(last);
                    found = true;
                    return;
                }
                // Count nodes that have been expanded.
                expanded++;
                // Here is the 4rd step of the algorithms
                // 4. Create the successors of Si, based on actions
                //    that can be implemented on Si.
                //    Each successor has a pointer to the Si, as its predecessor.
                //    In the case of DFS and BFS algorithms, successors should not
                //    belong neither to the OPEN SET nor the CLOSED SET.
                ArrayList<Node> succesors;
                succesors = createSuccesors(current, false);
                // Here is the 5th step of the algorithms
                // 5. For each successor of Si, ...
                for (Node cell: succesors){
                    // ... if we are running DFS ...
                    if (dfs.isSelected()) {
                        // ... add the successor at the beginning of the list OPEN SET
                        openSet.add(0, cell);
                        // Update the color of the cell
                        grid[cell.row][cell.col] = OPEN;
                        // ... if we are runnig BFS ...
                    } else if (bfs.isSelected()){
                        // ... add the successor at the end of the list OPEN SET
                        openSet.add(cell);
                        // Update the color of the cell
                        grid[cell.row][cell.col] = OPEN;
                        // ... if we are running A* or Greedy algorithms (step 5 of A* algorithm) ...
                    } else if (aStar.isSelected() || greedy.isSelected()){
                        // ... calculate the value f(Sj) ...
                        int dxg = current.col-cell.col;
                        int dyg = current.row-cell.row;
                        int dxh = goalLocation.col-cell.col;
                        int dyh = goalLocation.row-cell.row;
                      
                       
                            // calculate the Manhattan distance
                            if (greedy.isSelected()) {
                                // especially for the Greedy ...
                                cell.g = 0;
                            } else {
                                cell.g = current.g + Math.abs(dxg)+Math.abs(dyg);
                            }
                            cell.h = Math.abs(dxh)+Math.abs(dyh);
                        
                        cell.f = cell.g+cell.h;
                        // ... If Sj is neither in the OPEN SET nor in the CLOSED SET states ...
                        int openIndex   = isInList(openSet,cell);
                        int closedIndex = isInList(closedSet,cell);
                        if (openIndex == -1 && closedIndex == -1) {
                            // ... then add Sj in the OPEN SET ...
                            // ... evaluated as f(Sj)
                            openSet.add(cell);
                            // Update the color of the cell
                            grid[cell.row][cell.col] = OPEN;
                            // Else ...
                        } else {
                            // ... if already belongs to the OPEN SET, then ...
                            if (openIndex > -1){
                                // ... compare the new value assessment with the old one. 
                                // If old <= new ...
                                if (openSet.get(openIndex).f <= cell.f) {
                                    // ... then eject the new node with state Sj.
                                    // (ie do nothing for this node).
                                    // Else, ...
                                } else {
                                    // ... remove the element (Sj, old) from the list
                                    // to which it belongs ...
                                    openSet.remove(openIndex);
                                    // ... and add the item (Sj, new) to the OPEN SET.
                                    openSet.add(cell);
                                    // Update the color of the cell
                                    grid[cell.row][cell.col] = OPEN;
                                }
                                // ... if already belongs to the CLOSED SET, then ...
                            } else {
                                // ... compare the new value assessment with the old one. 
                                // If old <= new ...
                                if (closedSet.get(closedIndex).f <= cell.f) {
                                    // ... then eject the new node with state Sj.
                                    // (ie do nothing for this node).
                                    // Else, ...
                                } else {
                                    // ... remove the element (Sj, old) from the list
                                    // to which it belongs ...
                                    closedSet.remove(closedIndex);
                                    // ... and add the item (Sj, new) to the OPEN SET.
                                    openSet.add(cell);
                                    // Update the color of the cell
                                    grid[cell.row][cell.col] = OPEN;
                                }
                            }
                        }
                    }
                }
            }
        } //end expandNode()
        
        /**
         * Creates the successors of a state/cell
         * 
         * @param current       the cell for which we ask successors
         * @param makeConnected flag that indicates that we are interested only on the coordinates
         *                      of cells and not on the label 'dist' (concerns only Dijkstra's)
         * @return              the successors of the cell as a list
         */
        private ArrayList<Node> createSuccesors(Node current, boolean makeConnected){
            int r = current.row;
            int c = current.col;
            // We create an empty list for the successors of the current cell.
            ArrayList<Node> temp = new ArrayList<>();
         
            
            //  Movement priority is:
            // 1: Up 2: Right 3: Down 4: Left
            
            // If not at the topmost limit of the grid
            // and the up-side cell is not an obstacle ...
            if (r > 0 && grid[r-1][c] != OBST &&
                    // ... and (only in the case are not running the A* or Greedy)
                    // not already belongs neither to the OPEN SET nor to the CLOSED SET ...
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          isInList(openSet,new Node(r-1,c)) == -1 &&
                          isInList(closedSet,new Node(r-1,c)) == -1)) {
                Node cell = new Node(r-1,c);
                // In the case of Dijkstra's algorithm we can not append to
                // the list of successors the "naked" cell we have just created.
                // The cell must be accompanied by the label 'dist',
                // so we need to track it down through the list 'graph'
                // and then copy it back to the list of successors.
                // The flag makeConnected is necessary to be able
                // the present method createSuccesors() to collaborate
                // with the method findConnectedComponent(), which creates
                // the connected component when Dijkstra's initializes.
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(cell);
                    else {
                        int graphIndex = isInList(graph,cell);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                    // ... update the pointer of the up-side cell so it points the current one ...
                    cell.prev = current;
                    // ... and add the up-side cell to the successors of the current one. 
                    temp.add(cell);
                 }
            }
          
            // If not at the rightmost limit of the grid
            // and the right-side cell is not an obstacle ...
            if (c < columns-1 && grid[r][c+1] != OBST &&
                    // ... and (only in the case are not running the A* or Greedy)
                    // not already belongs neither to the OPEN SET nor to the CLOSED SET ...
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected())? true :
                          isInList(openSet,new Node(r,c+1)) == -1 &&
                          isInList(closedSet,new Node(r,c+1)) == -1)) {
                Node cell = new Node(r,c+1);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(cell);
                    else {
                        int graphIndex = isInList(graph,cell);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                    // ... update the pointer of the right-side cell so it points the current one ...
                    cell.prev = current;
                    // ... and add the right-side cell to the successors of the current one. 
                    temp.add(cell);
                }
            }
           
            // If not at the lowermost limit of the grid
            // and the down-side cell is not an obstacle ...
            if (r < rows-1 && grid[r+1][c] != OBST &&
                    // ... and (only in the case are not running the A* or Greedy)
                    // not already belongs neither to the OPEN SET nor to the CLOSED SET ...
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          isInList(openSet,new Node(r+1,c)) == -1 &&
                          isInList(closedSet,new Node(r+1,c)) == -1)) {
                Node cell = new Node(r+1,c);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(cell);
                    else {
                        int graphIndex = isInList(graph,cell);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                   // ... update the pointer of the down-side cell so it points the current one ...
                    cell.prev = current;
                    // ... and add the down-side cell to the successors of the current one. 
                    temp.add(cell);
                }
            }
         
            // If not at the leftmost limit of the grid
            // and the left-side cell is not an obstacle ...
            if (c > 0 && grid[r][c-1] != OBST && 
                    // ... and (only in the case are not running the A* or Greedy)
                    // not already belongs neither to the OPEN SET nor to the CLOSED SET ...
                    ((aStar.isSelected() || greedy.isSelected() || dijkstra.isSelected()) ? true :
                          isInList(openSet,new Node(r,c-1)) == -1 &&
                          isInList(closedSet,new Node(r,c-1)) == -1)) {
                Node cell = new Node(r,c-1);
                if (dijkstra.isSelected()){
                    if (makeConnected)
                        temp.add(cell);
                    else {
                        int graphIndex = isInList(graph,cell);
                        if (graphIndex > -1)
                            temp.add(graph.get(graphIndex));
                    }
                } else {
                   // ... update the pointer of the left-side cell so it points the current one ...
                    cell.prev = current;
                    // ... and add the left-side cell to the successors of the current one. 
                    temp.add(cell);
                }
            }
          
            // When DFS algorithm is in use, cells are added one by one at the beginning of the
            // OPEN SET list. Because of this, we must reverse the order of successors formed,
            // so the successor corresponding to the highest priority, to be placed
            // the first in the list.
            // For the Greedy, A* and Dijkstra's no issue, because the list is sorted
            // according to 'f' or 'dist' before extracting the first element of.
            if (dfs.isSelected())
                Collections.reverse(temp);
            
            return temp;
        } // end createSuccesors()
        
        /**
         * Returns the distance between two cells
         *
         * @param u the first cell
         * @param v the other cell
         * @return  the distance between the cells u and v
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
         * Returns the index of the cell 'current' in the list 'list'
         *
         * @param list    the list in which we seek
         * @param current the cell we are looking for
         * @return        the index of the cell in the list
         *                if the cell is not found returns -1
         */
        private int isInList(ArrayList<Node> list, Node current){
            int index = -1;
            for (int i = 0 ; i < list.size(); i++) {
                Node listItem = list.get(i);
                if (current.row == listItem.row && current.col == listItem.col) {
                    index = i;
                    break;
                }
            }
            return index;
        } // end isInList()
        
        /**
         * Returns the predecessor of cell 'current' in list 'list'
         *
         * @param list      the list in which we seek
         * @param current   the cell we are looking for
         * @return          the predecessor of cell 'current'
         */
        private Node findPrev(ArrayList<Node> list, Node current){
            int index = isInList(list, current);
            Node listItem = list.get(index);
            return listItem.prev;
        } // end findPrev()
        
        /**
         * Calculates the path from the target to the initial position
         * of the robot, counts the corresponding steps
         * and measures the distance traveled.
         */
        private void plotRoute(){
            int steps = 0;
            double distance = 0;
            int index = isInList(closedSet,goalLocation);
            Node cur = closedSet.get(index);
            grid[cur.row][cur.col]= TARGET;
            do {
                steps++;
                
                    distance++;
                cur = cur.prev;
                grid[cur.row][cur.col] = ROUTE;
            } while (!(cur.row == robotStart.row && cur.col == robotStart.col));
            grid[robotStart.row][robotStart.col]=ROBOT;
            String msg;
            String colorMsg = "<html><font color = \"#448AFF\">Nodes expanded: %d, Shortest Path: %d </font></html>";
            msg = String.format(colorMsg,
                     expanded,steps,distance); 
            message.setText(msg);
          
        } // end plotRoute()
        
        /**
         * Appends to the list containing the nodes of the graph only
         * the cells belonging to the same connected component with node v.
         * This is a Breadth First Search of the graph starting from node v.
         *
         * @param v    the starting node
         */
        private void findConnectedComponent(Node v){
            Stack<Node> stack;
            stack = new Stack();
            ArrayList<Node> succesors;
            stack.push(v);
            graph.add(v);
            while(!stack.isEmpty()){
                v = stack.pop();
                succesors = createSuccesors(v, true);
                for (Node c: succesors) {
                    if (isInList(graph, c) == -1){
                        stack.push(c);
                        graph.add(c);
                    }
                }
            }
        } // end findConnectedComponent()
        
        /**
         * Initialization of Dijkstra's algorithm
         */
        private void initializeDijkstra() {
            /**
             * When one thinks of Wikipedia pseudocode, observe that the
             * algorithm is still looking for his target while there are still
             * nodes in the queue Q.
             * Only when we run out of queue and the target has not been found,
             * can answer that there is no solution .
             * As is known, the algorithm models the problem as a connected graph.
             * It is obvious that no solution exists only when the graph is not
             * connected and the target is in a different connected component
             * of this initial position of the robot.
             * To be thus possible negative response from the algorithm,
             * should search be made ONLY in the coherent component to which the
             * initial position of the robot belongs.
             * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
             */
            
            // First create the connected component
            // to which the initial position of the robot belongs.
            graph.removeAll(graph);
            findConnectedComponent(robotStart);
            // Here is the initialization of Dijkstra's algorithm 
            // 2: for each vertex v in Graph;
            for (Node v: graph) {
                // 3: dist[v] := infinity ;
                v.dist = INFINITY;
                // 5: previous[v] := undefined ;
                v.prev = null;
            }
            // 8: dist[source] := 0;
            graph.get(isInList(graph,robotStart)).dist = 0;
            // 9: Q := the set of all nodes in Graph;
            // Instead of the variable Q we will use the list
            // 'graph' itself, which has already been initialised.            

            // Sorts the list of nodes with respect to 'dist'.
            Collections.sort(graph, new NodebyDist());
            /* Initializes the list of closed nodes to have none visisted. 
            * In case it some nodes were carried from the last time the program was ran
            */
            closedSet.removeAll(closedSet);
        } // end initializeDijkstra()

        /**
         * Repaints the grid
         */
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            // Fills the background color.
            g.fillRect(10, 10, columns*gridSize+1, rows*gridSize+1);

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
                    g.fillRect(11 + c*gridSize, 11 + r*gridSize, gridSize - 1, gridSize - 1);
                }
            }
           
            
           
        } // end paintComponent()
        
      
    } // end nested classs MazePanel
  
    
    
