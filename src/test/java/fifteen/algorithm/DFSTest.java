package fifteen.algorithm;

import fifteen.graphs.Directions;
import fifteen.graphs.PuzzleNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Marcinn on 2017-03-31.
 */
public class DFSTest {
    private DFSAlgorithm algorithm;
    private Directions[] directions = {Directions.Down, Directions.Left, Directions.Up, Directions.Right};
    private final static Byte [] FILE_CONTENTS = {4,4,1,2,3,4,5,10,6,8,9,0,7,12,13,14,11,15};
    @Before
    public void setUp()
    {
        algorithm = new DFSAlgorithm(new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS))), directions, (byte) 20);

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
}
