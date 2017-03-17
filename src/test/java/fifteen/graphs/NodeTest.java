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

    private final static ArrayList<Byte> NODE = new ArrayList<Byte>(Arrays.asList(nodeContents));

    Node puzzleelement;

    @Before
    public void setUp(){
        puzzleelement = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS)));
    }

    @Test
    public void getNodeValue() {
        puzzleelement.getValue();

        assertThat(puzzleelement.getValue()).isEqualTo(NODE);
    }

    @Test
    public void areTwoNodesEqual() {
        Node anotherElement = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS)));

        assertThat(puzzleelement).isEqualTo(anotherElement);
    }
}
