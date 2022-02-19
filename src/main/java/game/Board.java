package game;

import game.utils.Pos;

public interface Board {
    final int numCols = 5;
    final int numRows = 5;

    /**
     * Checks if a position already has a {@link Worker} on it already
     *
     * @param pos The position to check if there's a {@link Worker}
     * @return whether there's a {@link Worker} on the position
     */
    boolean hasWorker(Pos pos);

    /**
     * Check if a move is valid (meaning moving to an adjacent empty blocks without workers,
     * and the worker is going up at most by 1 level during the move
     * Note that this function only checks for validity but not perform the move operation
     *
     * @param worker The {@link Worker} that performs the move
     * @param pos The position {@link Pos} that the {@link Worker} is moving to
     * @return Whether this move is valid
     */
    boolean checkMove(Worker worker, Pos pos);

    /**
     * Check if a build is valid (meaning building blocks to an adjacent empty blocks
     * without workers on it, and that grid is not occupied by a Dome already)
     * Note that this function only check validity but not perform the build operation
     *
     * @param worker The {@link Worker} that performs the build
     * @param pos The position {@link Pos} that the build will be performed on
     * @return Whether the build is valid
     */
    boolean checkBuild(Worker worker, Pos pos);

    /**
     * Build a level at a certain position.
     * Prerequisite: this function should only be performed when
     * Effect: The levels of the {@link Grid} at the corresponding
     *      position is incremented accordingly
     * {@code checkBuild()} returns true
     *
     * @param pos The position to build the block
     */
    void build(Pos pos);

    /**
     * Get the Grid object based on the position given
     * @param pos The position of the grid
     * @return the {@link Grid} object on that position
     */
    Grid getGrid(Pos pos);

    /** Put a worker on to the board (performed at the beginning of the game when players
     * choose positions to put their workers)
     * Effect: the worker of the board is updated by this {@code worker}
     *
     * @param playerId Specifying which {@link Player} (1 or 2) the {@code worker} belongs to
     * @param workerId Specifying which {@link Worker} of the player (the 1 or the 2 one)
     *                 the {@code worker} represents
     * @param worker The {@link Worker} to be placed on the board
     *
     */
    void setWorker(int playerId, int workerId, Worker worker);

}
