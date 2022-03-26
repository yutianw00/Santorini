package game.utils;

import game.Board;
import game.godCards.GodGame;
import game.godCards.GodPower;

import java.util.Arrays;


/**
 * An object that passes to the frontend incidating what is the next action,
 * who is the next player, and what is the current board situation
 *
 * The State is also stored in the game history for implementing the undo and
 * replay functionalities
 */
public class State {

    public static final int STOK = 0;
    public static final int STERR = -1;

    private Board board;
    private int playerId;
    private int nextAction;
    private int status; // 0 means ok, -1 means error,
    private String god1 = "", god2 = "";

    public State(Board board, int playerId, int nextAction) {
        this.board = board;
        this.playerId = playerId;
        this.nextAction = nextAction;
        this.status = STOK;
    }

    public State(State otherState, int status) {
        this.board = otherState.getBoard();
        this.playerId = otherState.getPlayerId();
        this.nextAction = otherState.getNextAction();
        this.status = status;
    }

    public void addGod(GodPower god1, GodPower god2) {
        this.god1 = god1.description();
        this.god2 = god2.description();
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
        if (god1.equals("") || god2.equals("")) {
            myjsonstr = "{ " + "\"status\": " + status + "," +
                    "\"playerId\": " + playerId + "," +
                    " \"nextAction\": " + nextAction + "," +
                    " \"board\": "  + board.toString() + "}";
        } else {
            myjsonstr = "{ " + "\"status\": " + status + "," +
                    "\"playerId\": " + playerId + "," +
                    " \"nextAction\": " + nextAction + "," +
                    " \"board\": "  + board.toString() + "," +
                    " \"god1\": \""  + god1 + "\"," +
                    " \"god2\": \""  + god2 + "\"" + "}";
        }


        return myjsonstr;
    }

}
