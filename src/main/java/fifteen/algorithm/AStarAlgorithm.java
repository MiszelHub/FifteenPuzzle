package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.PuzzleNodeComparator;
import fifteen.graphs.Statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStarAlgorithm extends Algorithm
{

    private Heuristic heuristic;


    public AStarAlgorithm(PuzzleNode rootNode, Directions [] directions,Statistics statistics,Heuristic heuristic)
    {
        super(rootNode,directions,statistics);

        this.heuristic = heuristic;
        heuristic.setExpectedSolution(expectedSolution);

    }

    @Override
    PuzzleNode solvePuzzle() {

        statistics.startSolvingTime();

        PriorityQueue<PuzzleNode> nodesToProcess = new PriorityQueue<>(new PuzzleNodeComparator());
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        HashMap<PuzzleNode,PuzzleNode> nodeMap = new HashMap<>();

        nodesToProcess.add(rootNode);
        nodeMap.put(rootNode, rootNode);

        while (!nodesToProcess.isEmpty())
        {
            PuzzleNode currentNode = nodesToProcess.poll();
            statistics.increaseProcessedNodes();
            nodeMap.remove(currentNode);
            if(statistics.getMaxDepth() < statistics.calculateMaxDepth(currentNode))
                statistics.setMaxDepth(statistics.calculateMaxDepth(currentNode));
            if(currentNode.equals(expectedSolution))
            {
                statistics.stopSolvingTime();
                System.out.println("------------------------------------");

                statistics.calculatePathLength(currentNode);
                System.out.println("Solving time: "+statistics.getSolvingTime()+"ms");
                System.out.println("Max depth: "+statistics.getMaxDepth());
                System.out.println("Visited nodes: "+statistics.getVisitedNodes());
                System.out.println("Processed nodes: "+statistics.getProcessedNodes());
                System.out.println("Path Length: "+statistics.getSolutionLength());
                statistics.setMoves(currentNode);
                return currentNode;
            }
            visitedNodes.add(currentNode);
            statistics.increaseVisitedNodes();
            for(PuzzleNode neighbour : currentNode.getNeighbours(directions))
            {
                if(!visitedNodes.contains(neighbour))
                {
                    int distance = currentNode.getPreviousHeuristicValue()+1;
                    if(!nodesToProcess.contains(neighbour) || distance < nodeMap.get(neighbour).getPreviousHeuristicValue())
                    {
                        neighbour.setPreviousHeuristicValue(distance);
                        neighbour.setCurrentHeuristicValue(heuristic.getHeuristicsValue(neighbour));
                        neighbour.SumDistance();
                        nodesToProcess.add(neighbour);
                        if(!nodeMap.containsKey(neighbour))
                        {
                            nodeMap.put(neighbour, neighbour);
                        }
                        else
                        {
                            nodeMap.replace(neighbour, neighbour);
                        }
                    }
                }
            }
        }

        statistics.stopSolvingTime();

        return null;
    }


}
