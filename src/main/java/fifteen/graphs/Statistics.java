package fifteen.graphs;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcinn on 2017-04-04.
 */
public class Statistics
{
    private int visitedNodes;
    private int processedNodes;
    private List<PuzzleNode> solution;
    private int maxDepth;
    private long solvingTime;
    private Date beforeSolving;
    private Date afterSolving;

    public Statistics()
    {
        visitedNodes = 0;
        processedNodes = 0;
        maxDepth = 0;
        solvingTime = 0;
    }

    public void startSolvingTime()
    {
        beforeSolving = new Date();
    }

    public void stopSolvingTime()
    {
        afterSolving = new Date();
        solvingTime = afterSolving.getTime() - beforeSolving.getTime();
    }

    public void increaseVisitedNodes()
    {
        visitedNodes++;
    }

    public void increaseProcessedNodes()
    {
        processedNodes++;
    }

    public int calculateMaxDepth(PuzzleNode node)
    {
        int tempDepth = 0;
        while (node.getParent() != null)
        {
            node = node.getParent();
            tempDepth++;
        }

        return tempDepth;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(int visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    public int getProcessedNodes() {
        return processedNodes;
    }

    public void setProcessedNodes(int processedNodes) {
        this.processedNodes = processedNodes;
    }

    public List<PuzzleNode> getSolution() {
        return solution;
    }

    public void setSolution(List<PuzzleNode> solution) {
        this.solution = solution;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public long getSolvingTime() {
        return solvingTime;
    }

    public void setSolvingTime(long solvingTime) {
        this.solvingTime = solvingTime;
    }
}
