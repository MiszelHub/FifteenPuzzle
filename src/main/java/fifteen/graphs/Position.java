package fifteen.graphs;


public class Position {
    public byte column;
    public byte row;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position that = (Position) o;

        if (column != that.column) return false;
        return row == that.row;
    }

    @Override
    public int hashCode() {
        int result = (int) column;
        result = 31 * result + (int) row;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
