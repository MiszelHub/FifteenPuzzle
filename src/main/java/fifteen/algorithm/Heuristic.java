package fifteen.algorithm;

import fifteen.graphs.Position;
import fifteen.graphs.PuzzleNode;


public abstract class Heuristic {
    private PuzzleNode expectedSolution;

    public abstract int getHeuristicsValue(PuzzleNode node);

    public Position getCorrectElementPosition(byte puzzleElement){
        Position correctPosition = new Position();
        for (int i = 0; i < expectedSolution.getHeight(); i++) {
            for (int j = 0; j < expectedSolution.getWidth(); j++) {
                if(expectedSolution.getNodeContents()[i][j] == puzzleElement)
                {
                    correctPosition.row = (byte) i;
                    correctPosition.column = (byte) j;
                    break;
                }
            }
        }
        return correctPosition;
    }

    public PuzzleNode getExpectedSolution() {
        return expectedSolution;
    }

    public void setExpectedSolution(PuzzleNode expectedSolution) {
        this.expectedSolution = expectedSolution;
    }
}
