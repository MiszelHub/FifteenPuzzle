package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;

/**
 * Created by Marcinn on 2017-04-02.
 */
public class ManhatanHeuristic extends Heuristic {


    @Override
    public int getDistance(PuzzleNode node) {
        int distance = 0;
        for (int i = 0; i < node.getHeight(); i++) {
            for (int j = 0; j < node.getWidth(); j++) {
                byte element = node.getNodeContents()[i][j];
                distance += Math.sqrt(Math.pow(i - getCorrectPosition(element).row,2)+Math.pow(j-getCorrectPosition(element).column,2));
            }
        }

        return distance;
    }

}
