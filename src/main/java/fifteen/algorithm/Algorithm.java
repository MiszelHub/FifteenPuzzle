package fifteen.algorithm;

import fifteen.graphs.PuzzleNode;

/**
 * Created by Marcinn on 2017-03-27.
 */
public interface Algorithm {
    PuzzleNode getSolution(byte width, byte height);
    PuzzleNode solvePuzzle();


}
