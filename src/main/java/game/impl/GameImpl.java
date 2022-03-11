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
        if (!board.checkMove(worker, pos)) {
            return false;
        }
        worker.move(pos);
        return true;
    }

    @Override
    public boolean build(Worker worker, Pos pos) {
        if (!board.checkBuild(worker, pos)) {
            return false;
        }
        board.build(pos);
        return true;
    }

    @Override
    public boolean setWorker(Player player, Pos pos1, Pos pos2) {
        if (!board.isInBound(pos1) || !board.isInBound(pos2)) {
            return false;
        }
        if (board.hasWorker(pos1) || board.hasWorker(pos2)) {
            return false;
        }

        currPlayer.setWorkerA(pos1);
        currPlayer.setWorkerB(pos2);
        Worker worker1 = currPlayer.getWorkerA();
        Worker worker2 = currPlayer.getWorkerB();
        int playerId = (currPlayer == p1) ? 1 : 2;
        board.setWorker(playerId, 1, worker1);
        board.setWorker(playerId, 2, worker2);
        return true;


    }
}
