package fifteen.graphs;

import java.util.ArrayList;

/**
 * Created by user on 21.03.2017.
 */
public interface Graph {
    Node getRoot();
    ArrayList<Node> findNeighbours(Node node);
}
