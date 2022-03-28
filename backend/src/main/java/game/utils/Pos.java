package game.utils;

/**
 * A utilization class to represent the 2D positions on the board,
 * with basic getters and setters, and also a function to decide whether
 * two {@link Pos}es are equals
 */
public class Pos {
    private int row;
    private int col;
    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        return this.col * this.row;
    }

    /* reference: https://www.geeksforgeeks.org/overriding-equals-method-in-java/ */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pos)) {
            return false;
        }

        Pos pos = (Pos) obj;
        return (pos.getCol() == this.col && pos.getRow() == this.row);
    }

    @Override
    public String toString() {
        return "Pos{" + row + ", " + col + '}';
    }
}
