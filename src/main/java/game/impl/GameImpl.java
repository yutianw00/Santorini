package game.impl;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.utils.Pos;

import java.util.Arrays;

public class GameImpl implements Game {

    private String nextAction;

    private Board board;
    private Player p1;
    private Player p2;
    private Player winner;
    private Player currPlayer;

    @Override
    public void setNextAction(String action) {
        this.nextAction = action;
    }

    @Override
    public String getNextAction() {
        return this.nextAction;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    public GameImpl() {
        Worker w11 = new WorkerImpl();
        Worker w12 = new WorkerImpl();
        p1 = new PlayerImpl(w11, w12);

        Worker w21 = new WorkerImpl();
        Worker w22 = new WorkerImpl();
        p2 = new PlayerImpl(w21, w22);

        board = new BoardImpl(w11, w12, w21, w22);

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
        worker.setPos(pos);
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
    public Board setWorker(int playerId, int workerId, Pos pos) {
        if (!board.isInBound(pos)) {
            System.out.println("Game.setWorker: position is out of bound.");
            return null;
        }
        if (board.hasWorker(pos)) {
            System.out.println("Game.setWorker: position is occupied.");
            return null;
        }

        Player currPlayer = (playerId == 1) ? p1 : p2;

        currPlayer.setWorker(pos, workerId);

        return board.setBoardWorker(playerId, workerId, pos);


    }
}
