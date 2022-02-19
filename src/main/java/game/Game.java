package game;

public interface Game {
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
    Player getPlayer();

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
}
