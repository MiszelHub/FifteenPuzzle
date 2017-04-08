package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;

import java.util.ArrayList;


public abstract class Algorithm {

    protected PuzzleNode expectedSolution;
    protected PuzzleNode rootNode;
    protected Directions [] directions;
    protected Statistics statistics;

    public Algorithm(PuzzleNode rootNode, Directions[] directions, Statistics statistics) {
        this.rootNode = rootNode;
        this.directions = directions;
        expectedSolution = getSolution(rootNode.getWidth(),rootNode.getHeight());
        this.statistics = statistics;
    }

    public PuzzleNode getSolution(byte width, byte height) {
        ArrayList<Byte> node = new ArrayList<>();
        node.add(width);
        node.add(height);

        for (byte i = 1; i < height*width; i++) {
            node.add(i);
        }
        node.add((byte)0);


        return new PuzzleNode(node);
    }
    public abstract PuzzleNode solvePuzzle();


}
