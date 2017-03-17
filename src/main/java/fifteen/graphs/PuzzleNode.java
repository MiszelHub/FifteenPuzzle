package fifteen.graphs;

import java.util.ArrayList;

/**
 * Created by user on 17.03.2017.
 */
public class PuzzleNode implements Node<Byte> {

    private ArrayList<Byte> nodeContents;
    private byte height;
    private byte width;

    public PuzzleNode(ArrayList<Byte> fileContents) {
        this.nodeContents = fileContents;
        height = fileContents.get(0);
        width = fileContents.get(1);

        nodeContents.remove(0);
        nodeContents.remove(1);

    }

    @Override
    public ArrayList<Byte> getValue() {
        return this.nodeContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PuzzleNode that = (PuzzleNode) o;

        return nodeContents.equals(that.nodeContents);
    }

    @Override
    public int hashCode() {
        return nodeContents.hashCode();
    }
}
