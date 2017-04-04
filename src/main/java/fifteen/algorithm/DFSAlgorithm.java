package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.Node;
import fifteen.graphs.PuzzleNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;


public class DFSAlgorithm extends Algorithm
{
    private byte maximumDepth;

    public DFSAlgorithm(PuzzleNode rootNode, Directions[] directions, byte maximumDepth)
    {
        super(rootNode,directions);
        this.maximumDepth = maximumDepth;

    }


    @Override
    public PuzzleNode solvePuzzle()
    {
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        LinkedHashSet<PuzzleNode> nodesToProcess = new LinkedHashSet<>();

        nodesToProcess.add(rootNode);
        PuzzleNode solution = null;
        byte currentDepth = 0;

        while (!nodesToProcess.isEmpty())
        {

            if(this.maximumDepth < currentDepth)
            {
                nodesToProcess.remove(getLastElementOfHashSet(nodesToProcess));
                currentDepth--;
            }else{
                PuzzleNode currentNode = getLastElementOfHashSet(nodesToProcess);

                if(currentNode.equals(expectedSolution)) {
                    return currentNode;
                }
                boolean goBack=true;
                for (PuzzleNode neighbour : currentNode.getNeighbours(directions)){
                    if (!visitedNodes.contains(neighbour)){
                        if(neighbour.equals(expectedSolution)){
                            return neighbour;

                        }
                        visitedNodes.add(neighbour);
                        nodesToProcess.add(neighbour);
                        currentDepth++;
                        goBack = false;
                        break;
                    }
                }
                if(goBack){
                    nodesToProcess.remove(getLastElementOfHashSet(nodesToProcess));
                    currentDepth--;
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
