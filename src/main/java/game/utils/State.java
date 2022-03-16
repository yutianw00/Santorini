package game.utils;

import game.Board;


/**
 * An object that passes to the frontend incidating what is the next action,
 * who is the next player, and what is the current board situation
 *
 * The State is also stored in the game history for implementing the undo and
 * replay functionalities
 */
public class State {
    Board board;
    int playerId;
    int nextAction;

    public State(Board board, int playerId, int nextAction) {
        this.board = board;
        this.playerId = playerId;
        this.nextAction = nextAction;
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getNextAction() {
        return nextAction;
    }

}
