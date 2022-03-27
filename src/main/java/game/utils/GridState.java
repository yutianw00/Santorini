package game.utils;

public class GridState {
    private int levels;
    private int player;
    private int chosen;

    public GridState(int levels, int player, int chosen) {
        this.levels = levels;
        this.player = player;
        this.chosen = chosen;
    }

    public GridState(int levels) {
        this.levels = levels;
        this.player = 0;
        this.chosen = 0;
    }

    @Override
    public String toString() {
        return "{ \"levels\": " + levels + "," +
                " \"player\": " + player + "," +
                " \"chosen\": " + chosen +"}";
    }
}
