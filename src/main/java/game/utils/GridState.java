package game.utils;

public class GridState {
    int levels;
    int player;

    public GridState(int levels, int player) {
        this.levels = levels;
        this.player = player;
    }

    public GridState(int levels) {
        this.levels = levels;
        this.player = 0;
    }

    @Override
    public String toString() {
        return "{ \"levels\": \"" + levels + "\"," +
                        " \"player\": \"" + player + "\"}";
    }
}
