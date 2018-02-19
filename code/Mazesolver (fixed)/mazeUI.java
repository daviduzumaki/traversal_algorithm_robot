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



        private final static int
            INFINITY = Integer.MAX_VALUE, // Infinity
            EMPTY    = 0,  // empty cell
            OBST     = 1,  // cell with obstacle
            ROBOT    = 2,  // the position of the robot
            TARGET   = 3,  // the position of the target
            OPEN     = 4,  // open set
            CLOSED   = 5,  // closed set
            ROUTE    = 6;  // cells that form the robot-to-target path

        // Messages to the user
        private final static String
            SELECT_MESSAGE =
                "Select a search Algorithm then click 'Draw Path' or 'Real Time' ",
            MSG_NO_SOLUTION =
                "No path to goal exists";


         // the size of the tip of the arrow
                                      // pointing the predecessor cell
        ArrayList<Node> openSet   = new ArrayList();// the OPEN SET
        ArrayList<Node> closedSet = new ArrayList();// the CLOSED SET
        ArrayList<Node> graph     = new ArrayList();// the set of vertices of the graph
                                                    // to be explored by Dijkstra's algorithm

        Node robotStart; // the initial position of the robot
        Node goalLocation;  // the position of the goal

        JLabel message;  // prints to user appropriate message

        // basic buttons
        JButton resetButton, mazeButton, clearButton, CPUTimeButton, drawPathButton;


        // alogrithm button selectors
        JRadioButton dfs, bfs, aStar, greedy, dijkstra;

        // the slider for adjusting the speed of the animation
        JSlider speedController;


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

           //Color design choice dervies from Google's material design: https://material.io/guidelines/style/color.html#color-color-palette
            mazeButton = new JButton("Create Maze");
            Border margin = new EmptyBorder(5,15,5,15);
           // mazeButton.setOpaque(true);
            mazeButton.setFocusPainted(false);
            mazeButton.setBorderPainted(false);
            mazeButton.setForeground(Color.white);
            mazeButton.setBackground(Color.decode("#448AFF"));
            //calling this on actionlListener because the button contains the event handler so we're just passing the reference
            mazeButton.addActionListener(this::mazeButtonActionPerformed);

            clearButton = new JButton("Clear");
            clearButton.setFocusPainted(false);
            clearButton.setBorderPainted(false);
            clearButton.setForeground(Color.white);
            clearButton.setBackground(Color.decode("#448AFF"));
            clearButton.addActionListener(this::clearButtonActionPerformed);

            CPUTimeButton = new JButton("Real Time(CPU Time)");
            //realTimeButton.addActionListener(new ActionHandler());
            CPUTimeButton.setFocusPainted(false);
            CPUTimeButton.setBorderPainted(false);
            CPUTimeButton.setForeground(Color.white);
            CPUTimeButton.setBackground(Color.decode("#448AFF"));

            CPUTimeButton.addActionListener(this::realTimeButtonActionPerformed);


            drawPathButton = new JButton("Draw Path");
            drawPathButton.setFocusPainted(false);
            drawPathButton.setBorderPainted(false);
            drawPathButton.setForeground(Color.white);
            drawPathButton.setBackground(Color.decode("#448AFF"));
            drawPathButton.addActionListener(this::animationButtonActionPerformed);

            JLabel speedLabel = new JLabel("Speed(0-100 msec)", JLabel.CENTER);
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

            JPanel algoPanel = new JPanel();
            algoPanel.setBorder(javax.swing.BorderFactory.
                    createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(),
                    "Search Algorithms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 14)));
            algoPanel.setForeground(Color.white);
            algoPanel.setBackground(Color.decode("#448AFF"));
             algoPanel.setOpaque(true);

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


            // we regulate the sizes and positions
            //(dimensionX in Pixels, dimensionY in PIxels, width of actual Jelement, height of actual Jelement)
            message.setBounds(160, 1140, 500, 75);
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


            // we create the timer
            timer = new Timer(speedControl, action);

            // We attach to cells in the grid initial values.
            // Here is the first step of the algorithms
            initializeGrid(false);

        } // end constructor

        /**
         * Creates a new clean grid or a new maze
         */

         int rows    = 20,           // the number of rows of the grid
             columns = 20,           // the number of columns of the grid
             gridSize = 800/rows;  //size of grid in pixels. Should coorelate with dimensions of the Jframe
