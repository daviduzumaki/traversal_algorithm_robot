package mazesolver;

import java.util.Comparator;
       
        public class NodebyDist implements Comparator<Node>{
            @Override
            public int compare(Node cell1, Node cell2){
                return Double.compare(cell1.dist,cell2.dist);
            }
        } 