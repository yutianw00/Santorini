package game.impl;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.utils.Pos;

import java.util.List;

public class PlayerImpl implements Player {
    private Worker w1;
    private Worker w2;


    private final int p1w1Idx = 0;
    private final int p1w2Idx = 1;
    private final int p2w1Idx = 2;
    private final int p2w2Idx = 3;

    @Override
    public void linkWorker(Board board, int playerId) {
        List<Worker> workerList = board.getWorkers();
        if (playerId == 1) {
            w1 = workerList.get(p1w1Idx);
            w2 = workerList.get(p1w2Idx);
        } else {
            w1 = workerList.get(p2w1Idx);
            w2 = workerList.get(p2w2Idx);
        }
    }

    public PlayerImpl(Worker w1, Worker w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    private boolean posIsValid(Pos pos) {
        int r = pos.getRow();
        int c = pos.getCol();
        int numRows = Board.NUMROWS;
        int numCols = Board.NUMCOLS;
        if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
            return false;
        }
        return true;
    }


    @Override
    public void setWorker(Pos pos, int workerId) {
        Worker worker = (workerId == 1) ? w1 : w2;
        worker.setPos(pos);
    }

    @Override
    public Worker getWorkerA() {
        return w1;
    }

    @Override
    public Worker getWorkerB() {
        return w2;
    }

}
