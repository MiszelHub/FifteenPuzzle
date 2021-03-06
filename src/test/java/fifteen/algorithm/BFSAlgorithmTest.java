package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Marcinn on 2017-03-27.
 */
public class BFSAlgorithmTest {

    private final static Byte [] FILE_CONTENTS = {4, 4,1, 2, 3, 4,5, 7, 8, 11,9, 6, 10, 12,13, 14, 15, 0};

    private final static PuzzleNode EXPECTED_SOLUTION =
            new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{4,4,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0})));
    private BFSAlgorithm algorithm;
    private Directions[] directions = {Directions.Down, Directions.Left, Directions.Up, Directions.Right};

    @Before
    public void setUp() throws Exception {
        algorithm = new BFSAlgorithm(new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS))), directions, new Statistics());

    }

    @Test
    public void getCorrectSolution(){
        assertThat(algorithm.getExpectedSolution()).isEqualTo(EXPECTED_SOLUTION);
    }

    @Test
    public void solvePuzzle() throws Exception {
        assertThat(algorithm.solvePuzzle()).isEqualTo(EXPECTED_SOLUTION);
    }
}
