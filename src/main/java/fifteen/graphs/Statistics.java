package fifteen.graphs;

import com.google.common.base.Stopwatch;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.objects.NativeMath.round;


/**
 * Created by Marcinn on 2017-04-04.
 */
public class Statistics
{
    private int visitedNodes;
    private int processedNodes;
    private List<PuzzleNode> solution;
    private int maxDepth;
    double  solvingTime;
    private Date beforeSolving;
    private Date afterSolving;
    private Stopwatch stopwatch;
    private int solutionLength;
    private String moves="";

    public Statistics()
    {
        visitedNodes = 0;
        processedNodes = 0;
        maxDepth = 0;
        solvingTime = 0;
    }

    public void startSolvingTime()
    {
       // beforeSolving = new Date();
        stopwatch = Stopwatch.createStarted();
    }

    public void stopSolvingTime()
    {
       // afterSolving = new Date();
        stopwatch.stop();
        //solvingTime = afterSolving.getTime() - beforeSolving.getTime();
        solvingTime = (stopwatch.elapsed(TimeUnit.MICROSECONDS))/1000.0;
        round(solvingTime,3);
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
    public void calculatePathLength(PuzzleNode node){
        int length =0;


        while(node != null){
            length++;
            System.out.println(node.toString());
            moves+=node.getMoves();
            node = node.getParent();
        }
        moves = new StringBuffer(moves).reverse().toString();
        this.solutionLength = length;
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

    public double getSolvingTime() {
        return solvingTime;
    }

    public void setSolvingTime(double solvingTime) {
        this.solvingTime = solvingTime;
    }

    public int getSolutionLength() {
        return solutionLength;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(PuzzleNode node) {
        this.moves += node.getMoves();
    }
}
