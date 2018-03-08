package mazesolver;


import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class mazeUIIT {
    
    public mazeUIIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //Methods from mazeUI are being made Public when we need to test in order to access the methods from here.
    //We convert them back to private when completed.
    
    

    /**
     * Test of distBetween method, of class mazeUI.
     * It should produce the manhatten distance between two nodes
     */
    @Test
    public void testDistBetweene() {
        //the size of the mazeUI does'nt matter as we aren't concered about what's printed to the user here
        //it just has be instantiated 
        mazeUI test = new mazeUI(0, 0);
        Node node1 = new Node(0, 0);
        Node node2 = new Node(0, 0);
        
        Node node3 = new Node(-2, 4);
        Node node4 = new Node(10, 90);
        
        
       // int result1 = (int) test.distBetween(node1, node2);
       //int result2 = (int) test.distBetween(node3, node4);
       // assertEquals(0, result1);
       // assertEquals(98, result2);      
    }
    
     /**
     * Test of getNodeIndex method, of class mazeUI.
     * It should produce the index of a Node in a list of Nodes
     */
    @Test
    public void getNodeIndex(){
       mazeUI test = new mazeUI(0, 0);
       Node node1 = new Node(5, 5);
       Node node2 = new Node(9, 3);
       Node node3 = new Node(3, 2);
       Node node4 = new Node(1, 9);
       Node node5 = new Node(9, 3);
       Node node6 = new Node(3, 5);
       Node node7 = new Node(3, 8);
       Node node8 = new Node(1, 8);
       Node node9 = new Node(3, 7);
       Node node10 = new Node(3, 1);
 
        ArrayList<Node> list = new ArrayList();
           list.add(node1);
           list.add(node2);
           list.add(node3);
           list.add(node4);
           list.add(node5);
           list.add(node6);
           list.add(node7);
           list.add(node8);
           list.add(node9);
           list.add(node10);

      // int result1 = (int) test.getNodeIndex(list, node7);
       // assertEquals(6, result1);
       
    }
    
    /**
     * Test of createSuccessors method, of class mazeUI.
     * It should produce the index of a Node in a list of Nodes
     */
    @Test
    public void testCreateSuccessor(){
        mazeUI test = new mazeUI(500, 500);
        Node node = new Node(1, 1);
        Node node1 = new Node(1,0);
        Node node2 = new Node(2, 1);
        Node node3 = new Node(1,2);
        Node node4 = new Node(0,1);

        
        ArrayList<Node> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
   
        
        ArrayList<Node> result = test.createSuccesors(node, true);
        
        assertEquals(list, result);
        
    }
    
    
    
    
    
    
    
}
