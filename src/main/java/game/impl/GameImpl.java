package game.impl;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.utils.Pos;

import java.util.Arrays;

public class GameImpl implements Game {


    private Board board;
    private Player p1;
    private Player p2;
    private Player winner;
    private Player currPlayer;

    @Override
    public Board getBoard() {
        return this.board;
    }

    public GameImpl() {
        board = new BoardImpl();
        p1 = new PlayerImpl();
        p2 = new PlayerImpl();
        winner = null;
        currPlayer = p1;
    }

    @Override
    public boolean isFinished() {
        Pos p1APos = p1.getWorkerA().getPos();
        Pos p1BPos = p1.getWorkerB().getPos();
        Pos p2APos = p2.getWorkerA().getPos();
        Pos p2BPos = p2.getWorkerB().getPos();

        for (Pos pos : Arrays.asList(p1APos, p1BPos)) {
            if (board.getGrid(pos).getLevels() == WINTOWERLEVEL) {
                winner = p1;
                return true;
            }
        }

        for (Pos pos : Arrays.asList(p2APos, p2BPos)) {
            if (board.getGrid(pos).getLevels() == WINTOWERLEVEL) {
                winner = p2;
                return true;
            }
        }

        return false;

    }

    @Override
    public Player getCurrPlayer() {
        return currPlayer;
    }

    @Override
    public void flipPlayer() {
        if (currPlayer == p1) {
            currPlayer = p2;
        } else {
            currPlayer = p1;
        }
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public boolean move(Worker worker, Pos pos) {
        return false;
    }
}
