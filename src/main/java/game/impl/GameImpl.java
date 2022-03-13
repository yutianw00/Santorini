package game.impl;

import game.*;
import game.utils.Pos;
import game.utils.State;

import java.util.Arrays;
import java.util.List;

public class GameImpl implements Game {

    private int nextAction;

    History<State> history = new HistoryImpl<>();

    private Board board;
    private Player p1;
    private Player p2;
    private Player winner;
    private Player currPlayer;

    @Override
    public boolean undo() {
        return history.undo();
    }

    @Override
    public List<State> getHistory() {
        return history.getHistory();
    }

    @Override
    public void setNextAction(int action) {
        this.nextAction = action;
    }

    @Override
    public int getNextAction() {
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
    public int getCurrPlayer() {
        return (currPlayer == p1) ? 1 : 2;
    }

    @Override
    public void flipPlayer() {
        if (currPlayer == p1) {
            currPlayer = p2;
        } else {
            currPlayer = p1;
        }
        this.setNextAction(MOVE);
    }

    @Override
    public int getWinner() {
        if (winner == null) {
            return -1;
        } else {
            return (winner == p1) ? 1 : 2;
        }
    }

    Worker getWorker(int playerId, int workerId) {
        if (playerId == 1) {
            if (workerId == 1) {
                return p1.getWorkerA();
            } else {
                return p1.getWorkerB();
            }
        } else {
            if (workerId == 1) {
                return p2.getWorkerA();
            } else {
                return p2.getWorkerB();
            }
        }
    }

    @Override
    public Board move(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkMove(worker, pos)) {
            return null;
        }
        Board newBoard = board.copyBoard();
        newBoard.setBoardWorker(playerId, workerId, pos); // delegation
        this.setNextAction(BUILD);
        return newBoard;
    }

    @Override
    public Board build(int playerId, int workerId, Pos pos) {
        Worker worker = getWorker(playerId, workerId);
        if (!board.checkBuild(worker, pos)) {
            return null;
        }
        Board newBoard = board.copyBoard();
        newBoard.build(pos);
        this.setNextAction(FLIP);
        return newBoard;
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
