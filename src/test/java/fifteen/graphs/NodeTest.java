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

    PuzzleNode puzzleElement;

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
        PuzzleNode anotherElement = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(FILE_CONTENTS)));

        assertThat(puzzleElement).isEqualTo(anotherElement);
    }

    @Test
    public void CreateCloneOfNode() {
        PuzzleNode newNode = new PuzzleNode(puzzleElement);

        assertThat(newNode)
                .isNotNull()
                .isEqualTo(puzzleElement)
                .isEqualToComparingFieldByField(puzzleElement);
    }

    @Test
    public void SetBlankPosition() throws Exception {

        Position pos2 = new Position();
        pos2.column = 1;
        pos2.row =1;
        assertThat(puzzleElement.getPosition()).isEqualTo(pos2);
    }

    @Test
    public void getNeighbours() throws Exception {
        PuzzleNode upperNeighbour = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{2,2,2,0,4,3})));
        PuzzleNode leftNeighbour = new PuzzleNode(new ArrayList<Byte>(Arrays.asList(new Byte[]{2,2,2,3,0,4})));

        Directions [] directions = {Directions.Down,Directions.Up,Directions.Left,Directions.Right};
        assertThat(puzzleElement.getNeighbours(directions))
                .isNotNull()
                .hasSize(2)
                .contains(upperNeighbour,leftNeighbour);

    }

    @Test
    public void WeAreNotParentsOfOutGrandChildren() throws Exception {
        Directions [] directions = {Directions.Down,Directions.Up,Directions.Left,Directions.Right};
        ArrayList<PuzzleNode> children = puzzleElement.getNeighbours(directions);
        ArrayList<PuzzleNode> grandChildren = children.get(0).getNeighbours(directions);

        assertThat(grandChildren.get(0).getParent()).isNotEqualTo(children.get(0).getParent());

    }
}
