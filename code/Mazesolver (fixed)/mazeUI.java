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
                "Select a search Algorithm then click 'Draw Path' or 'Real Time' ",
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
                + "Dijkstra's algorithm where all the weights are equal to 1.</p></html>";
    
                                    
        ArrayList<Node> openSet   = new ArrayList();// the OPEN SET
        ArrayList<Node> closedSet = new ArrayList();// the CLOSED SET
        ArrayList<Node> graph     = new ArrayList();// the set of nodes of the graph
                                                    
         
        Node robotStart; // the initial position of the robot
        Node goalLocation;  // the position of the goal
      
        //Prints message to the user
        JLabel message, dfsMessage, speedLabel; 
        

        
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

            CPUTimeButton = new JButton("Real Time(CPU Time)");
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

            speedLabel = new JLabel("Speed(0-100 msec)", JLabel.CENTER);
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
            message.setBounds(160, 1140, 500, 75);
            dfsMessage.setBounds(30,805, 800, 230);
            mazeButton.setBounds(50, 850, 340, 50);
            clearButton.setBounds(50,905, 340, 50);
            CPUTimeButton.setBounds(50, 965,340, 50);   
            drawPathButton.setBounds(50, 1020, 340, 50);
            speedLabel.setBounds(120, 1100, 190, 40);
            speedController.setBounds(120, 1070, 200, 40);
            dfs.setBounds(510, 905, 70, 25);
            bfs.setBounds(510, 950, 70, 25);
            aStar.setBounds(650, 950, 70, 25);
            greedy.setBounds(650, 905, 85, 25);
            dijkstra.setBounds(510, 990, 85,25);
            algoPanel.setLocation(500,880);
            algoPanel.setSize(255, 150);
            resetUI.setBounds(10,905, 50, 50);
        

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
           
           
         int rows    = 20,           // the number of rows of the grid
             columns = 20,           // the number of columns of the grid
             gridSize = 600/rows;  //size of grid in pixels. Should coorelate with dimensions of the Jframe
         
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