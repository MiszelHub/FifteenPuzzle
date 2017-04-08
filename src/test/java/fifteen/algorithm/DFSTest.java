package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import fifteen.graphs.Statistics;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(JUnitParamsRunner.class)
public class DFSTest {
    private static final PuzzleNode EXPECTED_SOLUTION =
            new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0})));
    private DFSAlgorithm algorithm;
    private Directions[] directions = {Directions.Right, Directions.Down, Directions.Up, Directions.Left};
    private Directions[] DRUL= {Directions.Down,Directions.Right,Directions.Up,Directions.Left};
    private final static Byte [] FILE_CONTENTS = {4,4,1,2,3,4,5,10,6,8,9,0,7,12,13,14,11,15};
    @Before
    public void setUp()
    {
        algorithm = new DFSAlgorithm(new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS))), directions, new Statistics(), (byte) 20);

    }

    @Test
    public void getLastElementOfHashSet() throws Exception {
        LinkedHashSet<PuzzleNode> set = new LinkedHashSet<>();
        PuzzleNode FIRST =
                new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{4,4,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0})));
        PuzzleNode LAST =
                new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{4,4,1,2,3,4,5,6,7,8,9,10,11,12,14,13,15,0})));

        set.add(FIRST);

        set.add(LAST);

        PuzzleNode actualNode = algorithm.getLastElementOfHashSet(set);

        assertThat(actualNode).isEqualTo(LAST);

    }

    @Test
    @Parameters(method = "getPuzzles")
    public void solvePuzzle(ArrayList<Byte> fileContents) throws Exception {
        PuzzleNode node = new PuzzleNode(fileContents);
        algorithm = new DFSAlgorithm(node,directions, new Statistics(), (byte) 20);
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



