package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;


public class HammingHeuristic extends Heuristic {


    @Override
    public int getHeuristicsValue(PuzzleNode node) {
        int heuristicValue=0;
        for (int i = 0; i < node.getHeight(); i++) {
            for (int j = 0; j < node.getWidth(); j++) {
                if(node.getNodeContents()[i][j] != getExpectedSolution().getNodeContents()[i][j]){
                    heuristicValue++;
                }
            }
        }
        return heuristicValue;
    }
}
