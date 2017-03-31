package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;

import java.util.ArrayList;

/**
 * Created by Marcinn on 2017-03-27.
 */
public abstract class Algorithm {

    public PuzzleNode getSolution(byte width, byte height) {
        ArrayList<Byte> node = new ArrayList<>();
        node.add(width);
        node.add(height);

        for (byte i = 1; i < height*width; i++) {
            node.add(i);
        }
        node.add((byte)0);


        return new PuzzleNode(node);
    }
    abstract PuzzleNode solvePuzzle();


}
