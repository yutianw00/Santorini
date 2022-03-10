package game;

import game.utils.Pos;

public interface Game {
    int WINTOWERLEVEL = 3;

    /**
     * Check whether the game is finished (whether there's a worker standing
     * on a tower of level 3
     *
     * Precondition: setupWorker is called for all four workers of two players
     *
     * @return {@code true} is the game is finished, {@code false} otherwise
     */
    boolean isFinished();

    /**
     * Get the current player who takes the turn
     * @return The current {@link Player} who takes the turn
     */
    Player getCurrPlayer();

    /**
     * Get the winner of the game (the player who has a worker standing on a
     * 3-level tower)
     *
     * Prerequisite: {@code isFinished()} returns {@code true}
     *
     * @return The winner {@link Player} of the game
     */
    Player getWinner();

    /**
     * Flip the turn of the players
     *
     * Effect: player's turn is flipped, the {@code getPlayer()} will return
     * different players before and after this function is executed
     */
    void flipPlayer();

    /**
     * Get the board of this game
     * @return The {@link Board} of this game
     */
    Board getBoard();

    /**
     * Move a worker to a new position, checking the validity of such move
     * before the actual action.
     * If the move is valid, the actual move is performed, and true is returned;
     * otherwise no operation is done and false is returned.
     *
     * @param worker The worker to be moved
     * @param pos The position to be moved
     * @return true if move is successful, false if move is invalid
     */
    boolean move(Worker worker, Pos pos);

    /**
     * The Worker performs the build operation at position pos.
     * Before the actual build, the validity is checked.
     * If the build is not valid, false is returned and no build is performed;
     * otherwise the build is performed and true is returned.
     *
     * @param worker The worker that performs the build operation
     * @param pos The position that the worker will build
     * @return true if the operation is valid and succeeds, false otherwise and no
     *  operation is performed
     */
    boolean build(Worker worker, Pos pos);

}
