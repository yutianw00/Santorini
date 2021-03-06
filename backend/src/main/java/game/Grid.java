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

    /**
     * Decide if moving from previous level to the current grid's level is valid operation
     * meaning the current grid doesn't have a doom, and the grid level is not 2 more levels than
     * the previous fromLevel
     *
     * @param fromLevel the level to check
     * @return true if moving from previous level to current level is valid,
     *  false otherwise
     */
    boolean isValidLevel(int fromLevel);

    /**
     * Copy the current grid to a new {@link Grid} object
     * @return the newly created copied {@link Grid}
     */
    Grid copyGrid();

    /**
     * Decide if a grid is choosen by player during a CHOOSEMOVE or CHOOSEBUILD
     * @return true if chosen, false otherwise
     */
    boolean isChosen();

    /**
     * choose the grid
     */
    void choose();

    /**
     * if the grid is chosen, un-choose the grid
     */
    void unChoose();
}
