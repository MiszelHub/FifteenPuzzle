package fifteen.graphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 17.03.2017.
 */
public class PuzzleNode implements Node<Byte> {

    private Byte [][] nodeContents;
    private byte height;
    private byte width;
    private BlankPosition blankPosition;

    public PuzzleNode(Object node)
    {
        if(node instanceof PuzzleNode)
        {

            this.width = ((PuzzleNode) node).width;
            this.height = ((PuzzleNode) node).height;
            this.nodeContents = ((PuzzleNode) node).nodeContents.clone();
        }
    }

    public PuzzleNode(ArrayList<Byte> fileContents) {
        height = fileContents.get(0);
        width = fileContents.get(1);

        fileContents.remove(0);
        fileContents.remove(1);

        nodeContents = new Byte[height][width];

        fillNodeContents(fileContents);

    }

    private void fillNodeContents(ArrayList<Byte> fileContents)
    {
        int k =0;
        for(int i=0;i<width;i++)
        {
            for (int j = 0; j < height; j++) {
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

    @Override
    public Byte [][] getNodeContents() {
        return this.nodeContents;
    }


    public void setNodeContents(Byte[][] nodeContents) {
        this.nodeContents = nodeContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PuzzleNode that = (PuzzleNode) o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        return Arrays.deepEquals(nodeContents, that.nodeContents);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(nodeContents);
        result = 31 * result + (int) height;
        result = 31 * result + (int) width;
        return result;
    }

}
