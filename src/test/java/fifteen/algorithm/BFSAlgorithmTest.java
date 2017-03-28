package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Marcinn on 2017-03-27.
 */
public class BFSAlgorithmTest {

    private final static Byte [] FILE_CONTENTS = {2,2,1,2,0,3};
    private final static PuzzleNode EXPECTED_SOLUTION = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{2,2,1,2,3,0})));
    private BFSAlgorithm algorithm;
    private Directions[] directions = {Directions.Down, Directions.Left, Directions.Up, Directions.Right};

    @Before
    public void setUp() throws Exception {
        algorithm = new BFSAlgorithm(new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS))), directions);

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
