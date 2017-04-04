package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.PuzzleNodeComparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStarAlgorithm extends Algorithm
{

    private Heuristic heuristic;


    public AStarAlgorithm(PuzzleNode rootNode, Directions [] directions,Heuristic heuristic)
    {
        super(rootNode,directions);

        this.heuristic = heuristic;
        heuristic.setExpectedSolution(expectedSolution);
    }

    @Override
    PuzzleNode solvePuzzle() {

        PriorityQueue<PuzzleNode> nodesToProcess = new PriorityQueue<>(new PuzzleNodeComparator());
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        HashMap<PuzzleNode,PuzzleNode> nodeMap = new HashMap<>();

        nodesToProcess.add(rootNode);
        nodeMap.put(rootNode, rootNode);

        while (!nodesToProcess.isEmpty())
        {
            PuzzleNode currentNode = nodesToProcess.poll();
            nodeMap.remove(currentNode);
            if(currentNode.equals(expectedSolution))
            {
                return currentNode;
            }
            visitedNodes.add(currentNode);
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
        return null;
    }


}
