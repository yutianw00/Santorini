package game.impl;

import game.Board;
import game.Game;
import game.Player;

public class GameImpl implements Game {
    private Board board;

    public GameImpl() {
        Board board = new BoardImpl();
    }

    @Override
    public void setup() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Player getNextPlayer() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }
}
