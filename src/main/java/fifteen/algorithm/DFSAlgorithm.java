package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;

import java.util.HashSet;
import java.util.LinkedHashSet;


public class DFSAlgorithm extends Algorithm
{
    private byte maximumDepth;
    Statistics statistics;

    public DFSAlgorithm(PuzzleNode rootNode, Directions[] directions, byte maximumDepth)
    {
        super(rootNode,directions);
        this.maximumDepth = maximumDepth;
        statistics = new Statistics();
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
            statistics.startSolvingTime();
            if(currentDepth > statistics.getMaxDepth())
                statistics.setMaxDepth(currentDepth);

            if(this.maximumDepth < currentDepth)
            {
                nodesToProcess.remove(getLastElementOfHashSet(nodesToProcess));
                currentDepth--;
                statistics.increaseProcessedNodes();
            }
            else
            {
                PuzzleNode currentNode = getLastElementOfHashSet(nodesToProcess);

                if(currentNode.equals(expectedSolution)) {
                    statistics.stopSolvingTime();
                    return currentNode;
                }
                boolean goBack=true;
                for (PuzzleNode neighbour : currentNode.getNeighbours(directions)){
                    if (!visitedNodes.contains(neighbour)){
                        if(neighbour.equals(expectedSolution)){
                            statistics.stopSolvingTime();
                            return neighbour;

                        }
                        visitedNodes.add(neighbour);
                        nodesToProcess.add(neighbour);
                        statistics.increaseVisitedNodes();
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

        statistics.stopSolvingTime();
        return solution;
    }

    public PuzzleNode getLastElementOfHashSet(LinkedHashSet<PuzzleNode> openNodes)
    {
       return openNodes.stream().skip(openNodes.size()-1).findFirst().get();
    }
}
