package fifteen.graphs;

import java.util.ArrayList;


public interface Graph {
    Node getRoot();
    ArrayList<Node> findNeighbours(Node node);
}
