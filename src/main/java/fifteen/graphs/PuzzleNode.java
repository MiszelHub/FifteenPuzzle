package fifteen.graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 17.03.2017.
 */
public class PuzzleNode {

    private byte [][] nodeContents;
    private byte height;
    private byte width;
    private BlankPosition blankPosition;
    private PuzzleNode parent;


    public PuzzleNode(Object node)
    {

        if(node instanceof PuzzleNode)
        {

            this.width = ((PuzzleNode) node).width;
            this.height = ((PuzzleNode) node).height;
            this.nodeContents = deepCopy(((PuzzleNode) node).nodeContents);
            this.blankPosition = new BlankPosition();
            this.blankPosition.column = ((PuzzleNode) node).blankPosition.column;
            this.blankPosition.row = ((PuzzleNode) node).blankPosition.row;
        }
    }
    byte[][] deepCopy(byte[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public PuzzleNode(ArrayList<Byte> fileContents) {
       blankPosition = new BlankPosition();
        height = fileContents.get(0);
        width = fileContents.get(1);

        fileContents.remove(0);
        fileContents.remove(0);

        nodeContents = new byte[height][width];

        fillNodeContents(fileContents);

    }

    private void fillNodeContents(ArrayList<Byte> fileContents)
    {
        int k =0;
        for(int i=0;i<height;i++)
        {
            for (int j = 0; j < width; j++) {
                nodeContents[i][j] = fileContents.get(k);
                if(nodeContents[i][j] == 0)
                {
                    blankPosition.column =(byte) j;
                    blankPosition.row =(byte) i;
                }
                k++;
            }
        }
    }


    public byte [][] getNodeContents() {
        return this.nodeContents;
    }


    public void setNodeContents(byte[][] nodeContents) {
        this.nodeContents = nodeContents;
    }

    public byte getHeight() {
        return height;
    }

    public byte getWidth() {
        return width;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public BlankPosition getBlankPosition() {
        return blankPosition;
    }
    public ArrayList<PuzzleNode> getNeighbours(Directions [] directions)
    {
        ArrayList<PuzzleNode> neighbours = new ArrayList<>();
        for (Directions direction : directions)
        {
            PuzzleNode node = null;
            try{
                node = getNeighbour(direction);
                neighbours.add(node);
            }catch(IndexOutOfBoundsException e){

            }

        }

        return  neighbours;
    }

    private PuzzleNode getNeighbour(Directions direction) {
        PuzzleNode node = new PuzzleNode(this);

        if(direction == Directions.Down)
        {
            node.nodeContents[blankPosition.row][blankPosition.column] = node.nodeContents[blankPosition.row + 1][blankPosition.column];
            node.nodeContents[blankPosition.row + 1][blankPosition.column] = 0;

            node.blankPosition.row = (byte) (blankPosition.row + 1);
        }
        else if(direction == Directions.Up)
        {
            node.nodeContents[blankPosition.row][blankPosition.column] = node.nodeContents[blankPosition.row - 1][blankPosition.column];
            node.nodeContents[blankPosition.row - 1][blankPosition.column] = 0;

            node.blankPosition.row = (byte) (blankPosition.row - 1);
        }
        else if(direction == Directions.Left)
        {
            node.nodeContents[blankPosition.row][blankPosition.column] = node.nodeContents[blankPosition.row][blankPosition.column - 1];
            node.nodeContents[blankPosition.row][blankPosition.column - 1] = 0;

            node.blankPosition.column = (byte) (blankPosition.column - 1);
        }
        else if(direction == Directions.Right)
        {
            node.nodeContents[blankPosition.row][blankPosition.column] = node.nodeContents[blankPosition.row][blankPosition.column + 1];
            node.nodeContents[blankPosition.row][blankPosition.column + 1] = 0;

            node.blankPosition.column = (byte) (blankPosition.column + 1);
        }
        node.parent = this;

        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PuzzleNode that = (PuzzleNode) o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        if (!Arrays.deepEquals(nodeContents, that.nodeContents)) return false;
        return blankPosition.equals(that.blankPosition);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(nodeContents);
        result = 31 * result + (int) height;
        result = 31 * result + (int) width;
        result = 31 * result + blankPosition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return nodeContentsToString();
    }
    private String nodeContentsToString()
    {
        StringBuilder stb = new StringBuilder();
        for (byte [] array : nodeContents)
        {
            stb.append(Arrays.toString(array));
            stb.append("\n");
        }
        return stb.toString();
    }
}
