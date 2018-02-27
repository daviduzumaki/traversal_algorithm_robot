package mazesolver;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/** 
 * 
 * @author 8fact
 */


public class Main {

    public static JFrame mazeFrame;  // The main form of the program
  
    
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int) screenSize.getWidth()/4;
        int height = (int) ((int) screenSize.getHeight()/1.2);
        
        mazeFrame = new JFrame("Maze Search by David And Ore");
        //The area where the grid is located
        mazeFrame.setContentPane(new mazeUI(width,height));
        mazeFrame.getContentPane().setBackground(Color.DARK_GRAY.darker());
        mazeFrame.pack(); 
       // mazeFrame.setResizable(false);
        //it loads on the centre of the screen
        mazeFrame.setLocationRelativeTo(null);
        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeFrame.setVisible(true);
        
    } // end main()
    
    
 
  
    
    
} // end class mazesolver