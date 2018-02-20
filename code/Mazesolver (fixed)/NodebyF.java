package mazesolver;

import java.util.Comparator;


   /*
   * Comparator aid provided by: https://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java-specific-situation
   */

public class NodebyF implements Comparator<Node>{
       
            @Override
            public int compare(Node node1, Node node2){
                return Double.compare(node1.f,node2.f);
            }
        } 
