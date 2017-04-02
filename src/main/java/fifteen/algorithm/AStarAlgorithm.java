package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;

/**
 * Created by Marcinn on 2017-04-02.
 */
public class AStarAlgorithm extends Algorithm
{
    private PuzzleNode rootNode;

    private PuzzleNode expectedSolution;

    private Heuristic heuristic;

    public AStarAlgorithm(PuzzleNode rootNode, Heuristic heuristic)
    {
        this.rootNode = rootNode;
        expectedSolution = getSolution(rootNode.getWidth(),rootNode.getHeight());
        this.heuristic = heuristic;
    }

    @Override
    PuzzleNode solvePuzzle() {

        return null;
    }
}
