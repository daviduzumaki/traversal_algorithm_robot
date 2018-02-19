package mazesolver;


import javax.swing.JFrame;




public class Main {

    public static JFrame mazeFrame;  // The main form of the program
  
    
    public static void main(String[] args) {
        int width  = 800;
        int height = 1200;
        mazeFrame = new JFrame("Maze Search by David And Ore");
        //The area where the grid is located
        mazeFrame.setContentPane(new MazePanel(width,height));
        mazeFrame.pack();
        mazeFrame.setResizable(false);
        //it loads on the centre of the screen
        mazeFrame.setLocationRelativeTo(null);
        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeFrame.setVisible(true);
        
    } // end main()
    
    
 
  
    
    
} // end class mazesolver