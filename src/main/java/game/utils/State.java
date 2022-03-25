package game.utils;

import game.Board;

import java.util.Arrays;


/**
 * An object that passes to the frontend incidating what is the next action,
 * who is the next player, and what is the current board situation
 *
 * The State is also stored in the game history for implementing the undo and
 * replay functionalities
 */
public class State {
    private Board board;
    private int playerId;
    private int nextAction;
    private int status;

    public State(Board board, int playerId, int nextAction) {
        this.board = board;
        this.playerId = playerId;
        this.nextAction = nextAction;
        this.status = 0;
    }

    public State(int status) {
        this.status = status;
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

    @Override
    public String toString() {
        String myjsonstr;
        if (status == 0) {
            myjsonstr = "{ " + "\"status\": \"" + status + "\"," +
                    "\"playerId\": \"" + playerId + "\"," +
                    " \"nextAction\": \"" + nextAction + "\"," +
                    " \"board\": "  + board.toString() + "}";
        } else {
            myjsonstr = "{ " + "\"status\": \"" + -1 + "\"" + "}";
        }


        return myjsonstr;
    }

}
