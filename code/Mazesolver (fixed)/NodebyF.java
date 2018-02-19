
package mazesolver;

import java.util.Comparator;


     // by default the compare method compares integers. We're overriding
     // it so it compares the F value returned by two nodes instead which are doubles.
     // even if it is not overridden it will still execute however this improves accuracy. 


public class NodebyF implements Comparator<Node>{
       
            @Override
            public int compare(Node node1, Node node2){
                return Double.compare(node1.f,node2.f);
            }
        } 
