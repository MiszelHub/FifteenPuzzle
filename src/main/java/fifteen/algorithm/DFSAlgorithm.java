package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class DFSAlgorithm extends Algorithm
{
    private byte maximumDepth;

    public DFSAlgorithm(PuzzleNode rootNode, Directions[] directions, Statistics statistics, byte maximumDepth)
    {
        super(rootNode,directions, statistics);
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

        while (!nodesToProcess.isEmpty()) {
            statistics.startSolvingTime();
            if (currentDepth > statistics.getMaxDepth())
                statistics.setMaxDepth(currentDepth);

            PuzzleNode currentNode = getLastElementOfHashSet(nodesToProcess);

            nodesToProcess.remove(currentNode);

            if(statistics.calculateMaxDepth(currentNode) == 1)
                visitedNodes.clear();

            if (currentNode.equals(expectedSolution)) {
                statistics.stopSolvingTime();
                return currentNode;
            }

            if (statistics.calculateMaxDepth(currentNode) > maximumDepth){
                continue;
            }


            visitedNodes.add(currentNode);
            statistics.increaseVisitedNodes();

            boolean goBack = true;
            ArrayList<PuzzleNode> neighbours = currentNode.getNeighbours(directions);
            Collections.reverse(neighbours);

            for (PuzzleNode neighbour : neighbours) {

                if (neighbour.equals(expectedSolution)) {
                    statistics.stopSolvingTime();
                    return neighbour;
                } else if (!visitedNodes.contains(neighbour) && !nodesToProcess.contains(neighbour)) {
                    nodesToProcess.add(neighbour);
                    statistics.increaseProcessedNodes();
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
