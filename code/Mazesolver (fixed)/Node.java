
package mazesolver;


public class Node {
            int row;     // the row number of the grid(row 0 is the top)
            int col;     // the column number of the grid (Column 0 is the left)
            
            // f(n) = g(n)+h(n). Used for the A* and greedy algorithms.
            double g;    
            double h;    
            double f;    
            // f(n) = g(n)+h(n). Used for the A* and greedy algorithms.
            
            double dist; // the distance of the node from the initial position of the robot
                        
            Node prev;   //variable storing the previous Node
            public Node(int row, int col){
               this.row = row;
               this.col = col;
             //  System.out.println("row: " + row);
              // System.out.println("col: " + col);
              
            }
            
         //The below code is used for testing   
         //   @Override 
         //   public String toString() {
         //      return "successor row: " + row + " " + "successor col: " + col;
              
                
          //  }
            
        } 