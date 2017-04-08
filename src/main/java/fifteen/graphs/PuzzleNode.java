package fifteen.graphs;

import java.util.ArrayList;
import java.util.Arrays;


public class PuzzleNode {

    private byte [][] nodeContents;
    private byte height;
    private byte width;
    private Position position;
    private PuzzleNode parent;
    private int totalHeuristicValue;
    private int currentHeuristicValue;
    private int previousHeuristicValue;
    private String moves="";

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }





    public PuzzleNode(Object node)
    {

        if(node instanceof PuzzleNode)
        {

            this.width = ((PuzzleNode) node).width;
            this.height = ((PuzzleNode) node).height;
            this.nodeContents = deepCopy(((PuzzleNode) node).nodeContents);
            this.position = new Position();
            this.position.column = ((PuzzleNode) node).position.column;
            this.position.row = ((PuzzleNode) node).position.row;
        }
    }
    byte[][] deepCopy(byte[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    public PuzzleNode(ArrayList<Byte> fileContents) {
       position = new Position();
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
                    position.column =(byte) j;
                    position.row =(byte) i;
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

    public Position getPosition() {
        return position;
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
            node.nodeContents[position.row][position.column] = node.nodeContents[position.row + 1][position.column];
            node.nodeContents[position.row + 1][position.column] = 0;

            node.position.row = (byte) (position.row + 1);
        }
        else if(direction == Directions.Up)
        {
            node.nodeContents[position.row][position.column] = node.nodeContents[position.row - 1][position.column];
            node.nodeContents[position.row - 1][position.column] = 0;

            node.position.row = (byte) (position.row - 1);
        }
        else if(direction == Directions.Left)
        {
            node.nodeContents[position.row][position.column] = node.nodeContents[position.row][position.column - 1];
            node.nodeContents[position.row][position.column - 1] = 0;

            node.position.column = (byte) (position.column - 1);
        }
        else if(direction == Directions.Right)
        {
            node.nodeContents[position.row][position.column] = node.nodeContents[position.row][position.column + 1];
            node.nodeContents[position.row][position.column + 1] = 0;

            node.position.column = (byte) (position.column + 1);
        }
        node.parent = this;
        node.moves += direction.toString();
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
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(nodeContents);
        result = 31 * result + (int) height;
        result = 31 * result + (int) width;
        result = 31 * result + position.hashCode();
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

    public int getTotalHeuristicValue() {
        return totalHeuristicValue;
    }

    public void setTotalHeuristicValue(int totalHeuristicValue) {
        this.totalHeuristicValue = totalHeuristicValue;
    }

    public int getCurrentHeuristicValue() {
        return currentHeuristicValue;
    }

    public void setCurrentHeuristicValue(int currentHeuristicValue) {
        this.currentHeuristicValue = currentHeuristicValue;
    }

    public int getPreviousHeuristicValue() {
        return previousHeuristicValue;
    }

    public void setPreviousHeuristicValue(int previousHeuristicValue) {
        this.previousHeuristicValue = previousHeuristicValue;
    }

    public void SumDistance()
    {
        totalHeuristicValue = previousHeuristicValue + currentHeuristicValue;
    }
}
