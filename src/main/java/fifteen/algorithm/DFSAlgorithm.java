package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.Node;
import fifteen.graphs.PuzzleNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Created by Marcinn on 2017-03-31.
 */
public class DFSAlgorithm extends Algorithm
{
    private PuzzleNode rootNode;

    private PuzzleNode expectedSolution;

    private Directions[] directions;

    private byte maximumDepth;

    public DFSAlgorithm(PuzzleNode rootNode, Directions[] directions, byte maximumDepth)
    {
        this.maximumDepth = maximumDepth;
        this.rootNode = rootNode;
        this.directions = directions;
        expectedSolution = getSolution(rootNode.getWidth(),rootNode.getHeight());
    }


    @Override
    public PuzzleNode solvePuzzle()
    {
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        LinkedHashSet<PuzzleNode> openNodes = new LinkedHashSet<>();

        openNodes.add(rootNode);
        PuzzleNode solution = null;
        byte currentDepth = 0;

        while (!openNodes.isEmpty())
        {

            if(this.maximumDepth < currentDepth)
            {
                openNodes.remove(getLastElementOfHashSet(openNodes));
                currentDepth--;
            }else{
                PuzzleNode currentNode = getLastElementOfHashSet(openNodes);
                for (PuzzleNode neighbour : currentNode.getNeighbours(directions)){
                    if (!visitedNodes.contains(neighbour)){
                        if(neighbour.equals(expectedSolution)){
                            return neighbour;

                        }
                        visitedNodes.add(neighbour);
                        openNodes.add(neighbour);
                        currentDepth++;
                    }
                }
            }
        }
        return solution;
    }

    public PuzzleNode getLastElementOfHashSet(LinkedHashSet<PuzzleNode> openNodes)
    {
       return openNodes.stream().skip(openNodes.size()-1).findFirst().get();
    }
}
