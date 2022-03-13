package game.utils;

import game.Board;
import game.Player;

public class State {
    Board board;
    int playerId;
    int nextAction;

    public State(Board board, int playerId, int nextAction) {
        this.board = board;
        this.playerId = playerId;
        this.nextAction = nextAction;
    }
}
