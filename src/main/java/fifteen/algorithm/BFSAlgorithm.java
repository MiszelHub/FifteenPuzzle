package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;

import java.util.*;


public class BFSAlgorithm extends Algorithm{

    Statistics statistics;


    public BFSAlgorithm(PuzzleNode rootNode, Directions[] directions) {
        super(rootNode,directions);
        statistics = new Statistics();
    }

    @Override
    public PuzzleNode solvePuzzle() {

        statistics.startSolvingTime();
        Queue<PuzzleNode> nodesToProcess = new LinkedList<>();
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        nodesToProcess.add(rootNode);

        PuzzleNode solution = null;


        while(!nodesToProcess.isEmpty())
        {
            PuzzleNode currentNode = nodesToProcess.remove();
            statistics.increaseProcessedNodes();

            if(currentNode.equals(expectedSolution))
            {
                solution = currentNode;
                break;
            }
            visitedNodes.add(currentNode);
            statistics.increaseVisitedNodes();
            for(PuzzleNode neighbour : currentNode.getNeighbours(directions))
            {
                if(statistics.getMaxDepth() < statistics.calculateMaxDepth(neighbour))
                    statistics.setMaxDepth(statistics.calculateMaxDepth(neighbour));
                System.out.println(neighbour.toString());
                System.out.println();
                if(!(visitedNodes.contains(neighbour) && nodesToProcess.contains(neighbour)))
                {
                    nodesToProcess.add(neighbour);
                }

            }
        System.out.println("------------------------------------");
        }
        statistics.stopSolvingTime();
        statistics.calculatePathLength(solution);
        System.out.println("Solving time: "+statistics.getSolvingTime()+"ms");
        System.out.println("Max depth: "+statistics.getMaxDepth());
        System.out.println("Visited nodes: "+statistics.getVisitedNodes());
        System.out.println("Processed nodes: "+statistics.getProcessedNodes());
        System.out.println("Path Length: "+statistics.getSolutionLength());
        return solution;
    }

    public PuzzleNode getRootNode() {
        return rootNode;
    }

    public PuzzleNode getExpectedSolution() {
        return expectedSolution;
    }

}
