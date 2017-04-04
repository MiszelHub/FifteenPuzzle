package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;


public class ManhatanHeuristic extends Heuristic {


    @Override
    public int getHeuristicsValue(PuzzleNode node) {
        int distance = 0;
        for (int i = 0; i < node.getHeight(); i++) {
            for (int j = 0; j < node.getWidth(); j++) {
                byte element = node.getNodeContents()[i][j];
                distance += Math.sqrt(Math.pow(i - getCorrectElementPosition(element).row,2)+Math.pow(j- getCorrectElementPosition(element).column,2));
            }
        }

        return distance;
    }

}
