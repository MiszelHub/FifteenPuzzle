package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Marcinn on 2017-03-27.
 */
public class BFSAlgorithm implements Algorithm{

    private PuzzleNode rootNode;

    private PuzzleNode expectedSolution;

    private Directions[] directions;

    public BFSAlgorithm(PuzzleNode rootNode, Directions[] directions) {
        this.rootNode = rootNode;
        this.directions = directions;
        expectedSolution = getSolution(rootNode.getWidth(),rootNode.getHeight());
    }

    @Override
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

    @Override
    public PuzzleNode solvePuzzle() {

        Queue<PuzzleNode> openNodes = new LinkedList<>();

        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        openNodes.add(rootNode);

        PuzzleNode solution = null;


        while(!openNodes.isEmpty())
        {
            PuzzleNode currentNode = openNodes.remove();

            if(currentNode.equals(expectedSolution))
            {
                solution = currentNode;
                break;
            }
            visitedNodes.add(currentNode);
            for(PuzzleNode neighbour : currentNode.getNeighbours(directions))
            {
                System.out.println(neighbour.toString());
                System.out.println();
                if(!(visitedNodes.contains(neighbour) && openNodes.contains(neighbour)))
                {
                    openNodes.add(neighbour);
                }

            }
        System.out.println("------------------------------------");
        }

        return solution;
    }

    public PuzzleNode getRootNode() {
        return rootNode;
    }

    public PuzzleNode getExpectedSolution() {
        return expectedSolution;
    }

}
