package fifteen.graphs;

import java.util.Comparator;

/**
 * Created by Marcinn on 2017-04-03.
 */
public class PuzzleNodeComparator implements Comparator<PuzzleNode> {

    @Override
    public int compare(PuzzleNode o1, PuzzleNode o2) {
        return Integer.compare(o1.getTotalDistance(),o2.getTotalDistance());
    }
}
