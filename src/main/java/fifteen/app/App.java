package fifteen.app;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import fifteen.algorithm.*;
import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args ) throws IOException {
        String filePath = "";
        String algorithmName = "";
        String strategy = "";
        Algorithm algorithm = null;
        Statistics statistics = new Statistics();
        FileManager<ArrayList<Byte>> fileManager = new PuzzleFileManager();

        PuzzleNode initialState = null;
        try{
            initialState = new PuzzleNode(fileManager.ReadFromFile(filePath));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        try
        {
            filePath = args[0];
            algorithmName = args[1];
            strategy = args[2];

        }catch (Exception e)
        {
            e.getMessage();
        }
        switch (algorithmName)
        {
            case "bfs":
                algorithm = new BFSAlgorithm(initialState, parseDirections(strategy), statistics);
                break;
            case "dfs":
                algorithm = new DFSAlgorithm(initialState, parseDirections(strategy), statistics, (byte)20);
                break;
            case "astr":
                if(strategy == "manh")
                {
                    algorithm = new AStarAlgorithm(initialState, parseDirections(strategy), statistics, new ManhatanHeuristic());
                }
                else if(strategy == "hamm")
                {
                    algorithm = new AStarAlgorithm(initialState, parseDirections(strategy), statistics, new HammingHeuristic());
                }
                break;
        }

        StringBuilder solutionFileBuilder = new StringBuilder();
        solutionFileBuilder.append(statistics.getSolutionLength()+"\n");
        solutionFileBuilder.append(statistics.getMoves());

        StringBuilder statisticsFileBuilder = new StringBuilder();
        statisticsFileBuilder.append(statistics.getSolutionLength()+"\n");
        statisticsFileBuilder.append(statistics.getVisitedNodes()+"\n");
        statisticsFileBuilder.append(statistics.getProcessedNodes()+"\n");
        statisticsFileBuilder.append(statistics.getMaxDepth()+"\n");
        statisticsFileBuilder.append(statistics.getSolvingTime()+"\n");


        filePath = "aaaaaaaaaaaaaaaaaaaaa4x4_01_0001_dfs_ludr.txt";

        String statisticsFileName = fileNameBuilder(filePath,"stats");
        String solutionFileName = fileNameBuilder(filePath,"sol");

        System.out.println( "FilePath : "+filePath);
        System.out.println( "StatisticsFileName : "+statisticsFileName);
        System.out.println( "Algorithm Name : "+algorithmName);
        System.out.println( "Strategy/Heuristic : "+strategy);

        try{
            fileManager.writeFile(statisticsFileName,"Statistics/", solutionFileBuilder.toString());
            fileManager.writeFile(solutionFileName,"Solutions/",statisticsFileBuilder.toString());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    public static Directions[] parseDirections(String strategy)
    {
        Directions[] directions = new Directions[4];
        for(int i=0; i<strategy.length(); i++)
        {
            if(strategy.charAt(i) == 'L')
            {
                directions[i] = Directions.Left;
            }
            else if(strategy.charAt(i) == 'R')
            {
                directions[i] = Directions.Right;
            }
            else if(strategy.charAt(i) == 'U')
            {
                directions[i] = Directions.Up;
            }
            else if(strategy.charAt(i) == 'D')
            {
                directions[i] = Directions.Down;
            }
        }

        return directions;
    }
    public static String fileNameBuilder(String filePath, String fileSuffix){
        String fileName = filePath.substring(filePath.length() - 24,filePath.length() - 4);
        fileName+="_"+fileSuffix;

        return fileName+".txt";
    }
}
