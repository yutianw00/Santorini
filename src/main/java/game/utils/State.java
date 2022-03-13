package game.utils;

import game.Board;
import game.Player;

public class State {
    Board board;
    Player player;
    int nextAction;

    public State(Board board, Player player, int nextAction) {
        this.board = board;
        this.player = player;
        this.nextAction = nextAction;
    }
}
