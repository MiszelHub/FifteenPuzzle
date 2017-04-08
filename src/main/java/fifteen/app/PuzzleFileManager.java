package fifteen.app;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class PuzzleFileManager implements FileManager<ArrayList<Byte>> {
    private Scanner scanner;

    @Override
    public ArrayList<Byte> ReadFromFile(String fileName) throws IOException {

        scanner = new Scanner(new File(fileName));
        ArrayList<Byte> tmpArray = new ArrayList<>();

        while (scanner.hasNext()) {
            while (scanner.hasNextByte())
            {
                tmpArray.add(scanner.nextByte());

            }
            scanner.nextLine();

        }

        return tmpArray;
    }

    @Override
    public void writeFile(String fileName, String destinationDirectory, String fileContents) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter(destinationDirectory+fileName);
        fileWriter.print(fileContents);
    }

}
