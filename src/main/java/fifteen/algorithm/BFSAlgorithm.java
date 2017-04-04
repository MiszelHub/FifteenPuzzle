package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class BFSAlgorithm extends Algorithm{


    public BFSAlgorithm(PuzzleNode rootNode, Directions[] directions) {
        super(rootNode,directions);

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
