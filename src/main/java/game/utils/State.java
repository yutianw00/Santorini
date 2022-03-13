package game.utils;

import game.Board;

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
