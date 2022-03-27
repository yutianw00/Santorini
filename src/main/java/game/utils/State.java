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

    public int getStatus() {
        return status;
    }

    public String getGod1() {
        return god1;
    }

    public String getGod2() {
        return god2;
    }

    public boolean getCanUse1() {
        return canUse1;
    }

    public boolean getCanUse2() {
        return canUse2;
    }

    public boolean getUsePower1() {
        return usePower1;
    }

    public boolean getUsePower2() {
        return usePower2;
    }

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
        this.god1 = otherState.getGod1();
        this.god2 = otherState.getGod2();
        this.canUse1 = otherState.getCanUse1();
        this.canUse2 = otherState.getCanUse2();
        this.usePower1 = otherState.getUsePower1();
        this.usePower2 = otherState.getUsePower2();
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
