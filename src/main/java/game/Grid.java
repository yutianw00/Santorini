package game;

public interface Grid {
    int DOMELEVEL = 4;

    /**
     * Build a tower at the current grid.
     *
     * Effect: the level of the current {@link Grid} is incremented by 1.
     */
    void build();

    /**
     * Get the current levels of the grid.
     * A grid with no tower has level 0, and with a dome on three blocks has level 4.
     * Otherwise the level is the number of blocks on this grid.
     *
     * @return The {@code level} of the current {@link Grid}.
     */
    int getLevels();

    /**
     * Decide whether the grid's construction is complete and no worker can stand
     * (no more build can perform on the grid and no worker can stand on that grid).
     * If the grid has a dome on three blocks then its construction is complete.
     *
     * @return whether the grid's construction is complete.
     */
    boolean isComplete();
}
