package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.Position;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.PuzzleNodeComparator;
import javafx.scene.layout.Priority;

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

        PriorityQueue<PuzzleNode> openNodes = new PriorityQueue<>(new PuzzleNodeComparator());
        HashSet<PuzzleNode> visitedNodes = new HashSet<>();
        HashMap<PuzzleNode,PuzzleNode> nodeMap = new HashMap<>();

        openNodes.add(rootNode);
        nodeMap.put(rootNode, rootNode);

        while (!openNodes.isEmpty())
        {
            PuzzleNode currentNode = openNodes.poll();
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
                    int distance = currentNode.getPreviousDistance()+1;
                    if(!openNodes.contains(neighbour) || distance < nodeMap.get(neighbour).getPreviousDistance())
                    {
                        neighbour.setPreviousDistance(distance);
                        neighbour.setCurrentDistance(heuristic.getDistance(neighbour));
                        neighbour.SumDistance();
                        openNodes.add(neighbour);
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
