package fifteen.app;


import fifteen.algorithm.*;
import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args ) throws IOException {
        String puzzleFilePath = "";
        String algorithmName = "";
        String strategy = "";
        String outputDirecory="";
        Algorithm algorithm = null;
        Statistics statistics = new Statistics();
        FileManager<ArrayList<Byte>> fileManager = new PuzzleFileManager();
        Directions [] defaultDirectionsForAstar = {Directions.Right, Directions.Down, Directions.Up, Directions.Left};


        try
        {
            puzzleFilePath = args[0];
            algorithmName = args[1];
            strategy = args[2];
            outputDirecory = args[3];

        }catch (Exception e)
        {
            e.getMessage();
        }
        PuzzleNode initialState = null;
        try{
            initialState = new PuzzleNode(fileManager.ReadFromFile(puzzleFilePath));
        }catch(IOException e){
            System.out.println(e.getMessage());
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
                if(strategy.equals("manh"))
                {
                    algorithm = new AStarAlgorithm(initialState, defaultDirectionsForAstar, statistics, new ManhatanHeuristic());
                }
                else if(strategy.equals("hamm"))
                {
                    algorithm = new AStarAlgorithm(initialState, defaultDirectionsForAstar, statistics, new HammingHeuristic());
                }
                break;
        }
        algorithm.solvePuzzle();

        StringBuilder solutionFileBuilder = new StringBuilder();
        solutionFileBuilder.append(statistics.getSolutionLength()+"\n");
        solutionFileBuilder.append(statistics.getMoves());

        StringBuilder statisticsFileBuilder = new StringBuilder();
        statisticsFileBuilder.append(statistics.getSolutionLength()+"\n");
        statisticsFileBuilder.append(statistics.getVisitedNodes()+"\n");
        statisticsFileBuilder.append(statistics.getProcessedNodes()+"\n");
        statisticsFileBuilder.append(statistics.getMaxDepth()+"\n");
        statisticsFileBuilder.append(statistics.getSolvingTime()+"\n");



        String statisticsFileName = fileNameBuilder(puzzleFilePath,"stats");
        String solutionFileName = fileNameBuilder(puzzleFilePath,"sol");

        System.out.println( "FilePath : "+puzzleFilePath);
        System.out.println( "StatisticsFileName : "+statisticsFileName);
        System.out.println( "Algorithm Name : "+algorithmName);
        System.out.println( "Strategy/Heuristic : "+strategy);


        try{
            fileManager.writeFile(outputDirecory+algorithmName+"\\"+strategy+"\\"+statisticsFileName, statisticsFileBuilder.toString());
            fileManager.writeFile(outputDirecory+algorithmName+"\\"+strategy+"\\"+solutionFileName,solutionFileBuilder.toString());
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
        String fileName = filePath.substring(filePath.length() - 16,filePath.length() - 4);
        fileName+="_"+fileSuffix;

        return fileName+".txt";
    }
}
