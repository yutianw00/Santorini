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
    private boolean canUse1 = false, canUse2 = false; // whether the player can use power
    private boolean usePower1 = false, usePower2 = false; // whether the player is using power

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
        if (god1.canDoAction(this)) {
            canUse1 = true;
        }
        if (god2.canDoAction(this)) {
            canUse2 = true;
        }

        if (god1.isUsing()) {
            usePower1 = true;
        }
        if (god2.isUsing()) {
            usePower2 = true;
        }

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

    public void inspect() {
        System.out.println(this.playerId);
        System.out.println(this.nextAction);
        System.out.println(this.getBoard().toString());
        System.out.println(this.god1);
        System.out.println(this.god2);
    }

    public void usePower(int powerId) {
        if (powerId == 1) {
            this.usePower1 = true;
        } else if (powerId == 2) {
            this.usePower2 = true;
        }
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
                    " \"god2\": \""  + god2 + "\"," +
                    " \"canUse1\": "  + canUse1 + "," +
                    " \"canUse2\": "  + canUse2 + "" +
                    "}";
        }
        return myjsonstr;
    }

}
