package game.utils;

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
}
