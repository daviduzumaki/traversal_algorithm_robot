package mazesolver;

import java.util.Comparator;
       
        public class NodebyDist implements Comparator<Node>{
            @Override
            public int compare(Node node1, Node node2){
                return Double.compare(node1.dist,node2.dist);
            }
        } 