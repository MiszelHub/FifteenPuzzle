package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marcinn on 2017-04-03.
 */
@RunWith(JUnitParamsRunner.class)
public class AStarAlgorithmTest
{
    private static final PuzzleNode EXPECTED_SOLUTION =
            new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0})));
    private AStarAlgorithm algorithm;
    private Directions[] directions = {Directions.Right, Directions.Down, Directions.Up, Directions.Left};
    private final static Byte [] FILE_CONTENTS = {4,4,1,2,3,4,5,10,6,8,9,0,7,12,13,14,11,15};
    @Before
    public void setUp()
    {
        algorithm = new AStarAlgorithm(new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS))), directions,new ManhatanHeuristic());

    }

    @Test
    @Parameters(method = "getPuzzles")
    public void solvePuzzleManhatanMetric(ArrayList<Byte> fileContents) throws Exception {
        PuzzleNode node = new PuzzleNode(fileContents);
        algorithm = new AStarAlgorithm(node, directions ,new ManhatanHeuristic());
        assertThat(algorithm.solvePuzzle()).isEqualTo(EXPECTED_SOLUTION);
    }
    @Test
    @Parameters(method = "getPuzzles")
    public void solvePuzzleHammingMetric(ArrayList<Byte> fileContents) throws Exception {
        PuzzleNode node = new PuzzleNode(fileContents);
        algorithm = new AStarAlgorithm(node, directions,new HammingHeuristic());
        assertThat(algorithm.solvePuzzle()).isEqualTo(EXPECTED_SOLUTION);
    }

    private Object [] getPuzzles(){
        return  $(
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4, 1, 2, 3, 4,5, 6, 7, 8,9, 10, 11, 0,13, 14, 15, 12}))),
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4,4,1,2,3,4,5,10,6,8,9,0,7,12,13,14,11,15}))),
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4,1, 2, 3, 4,9, 5, 6, 7,0, 13, 11, 8,14, 10, 15, 12}))),
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4,0, 1, 2, 4,5, 6, 3, 7,9, 10, 11, 8,13, 14, 15, 12}))),
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4,1, 2, 3, 4,6, 0, 11, 7,5, 9, 10, 8,13, 14, 15, 12}))),
                $(new ArrayList<Byte>(Arrays.asList(new Byte[]{4,4,1,6,2,3,5,10,7,4,9,0,11,8,13,14,15,12})))
        );
    }
}
