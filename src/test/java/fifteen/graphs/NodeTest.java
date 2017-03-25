package fifteen.graphs;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by user on 17.03.2017.
 */
public class NodeTest {
    private final static Byte [] FILE_CONTENTS = {2,2,2,3,4,0};
    private final static Byte [] nodeContents = {2,3,4,0};

    private final static Byte [][] NODE = {{2,3},{4,0}};

    Node puzzleElement;

    @Before
    public void setUp(){
        puzzleElement = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS)));
    }

    @Test
    public void getNodeValue() {
        puzzleElement.getNodeContents();

        assertThat(puzzleElement.getNodeContents()).isEqualTo(NODE);
    }

    @Test
    public void areTwoNodesEqual() {
        Node anotherElement = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS)));

        assertThat(puzzleElement).isEqualTo(anotherElement);
    }

    @Test
    public void CreateCloneOfNode() {
        Node newNode = new PuzzleNode(puzzleElement);

        assertThat(newNode)
                .isNotNull()
                .isEqualTo(puzzleElement)
                .isEqualToComparingFieldByField(puzzleElement);
    }

}
