package game;

import game.utils.Pos;
import game.utils.State;

import java.util.List;

public interface Game {
    int WINTOWERLEVEL = 3;
    int NUMPLAYERS = 2;

    // action number constants
    int MOVE = 1;
    int BUILD = 2;
    int SETUP = 4;

    /**
     * Set the board of the game to be a new {@link Board}
     * @param board The new board to set
     */
    void setBoard(Board board);

    /**
     * Undo the last operation
     * @return true if the undo is successful, false if
     *  no previous board state is recorded in history
     */
    boolean undo();

    /**
     * get the current state (the state that is just stored into the history)
     * @return The current {@link State} or null if no state is stored
     */
    State getCurrState();

    /**
     * Get all the history of the game
     * @return A list of states representing all the history of the game
     */
    List<State> getHistory();

    /**
     * Set the next action to be done
     * Action could be move, build or flip
     *
     * @param action An int constant representing
     *  the next action to be set
     */
    void setNextAction(int action);

    /**
     * Get the next action as a string
     * Action could be move, build or flip
     *
     * @return the next action as a int constant
     */
    int getNextAction();

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
     * Get the next player who is taking the next action
     * @return The next {@link Player} who takes the next action
     */
    int getNextPlayerId();

    /**
     * Get the winner of the game (the player who has a worker standing on a
     * 3-level tower)
     *
     * Prerequisite: {@code isFinished()} returns {@code true}
     *
     * @return The winner {@link Player} of the game
     */
    int getWinner();

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
     * @param workerId the worker id
     * @param playerId the player id
     * @param pos The position to be moved
     * @return null if the move is invalid, otherwise a new Board that reflects the
     *  move is returned
     */
    State move(int playerId, int workerId, Pos pos);

    /**
     * The Worker performs the build operation at position pos.
     * Before the actual build, the validity is checked.
     * If the build is not valid, false is returned and no build is performed;
     * otherwise the build is performed and true is returned.
     *
     * @param workerId the worker id
     * @param playerId the player id
     * @param pos The position that the worker will build
     * @return null if the build is invalid, otherwise return a new Board which
     *  reflects the result of the successful build operation
     */
    State build(int playerId, int workerId, Pos pos);

    /**
     * Set up the worker positions for the player.
     * Will check the validity of the positions before actually setting up.
     * If the positions are not valid, nothing is done and false is returned
     * Otherwise the worker position is setup and true is returned.
     *
     * @param playerId the id of the player. 1 means player 1, 2 means player 2
     * @param pos the pos of the worker to be set
     * @param workerId the worker id. 1 means worker 1, 2 means worker 2
     *
     * @return true if set up is successful, false if the position is invalid
     */
    State setWorker(int playerId, int workerId, Pos pos);

    /**
     * In god mode, a player may have the ability to actively use their god powers
     * If the god power can be used, the action is performed and a new State is returned
     * If the god power cannot be used or the game is not in the god mode,
     * null is returned.
     * @param playerId the player ID
     * @param workerId the worker ID
     * @param pos the position of the action
     * @return new {@link State} if the god power is used successfully,
     *  null if no god power can be used
     */
    State usePower(int playerId, int workerId, Pos pos);

}
