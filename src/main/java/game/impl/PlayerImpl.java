package game.impl;

import game.Board;
import game.Player;
import game.Worker;
import game.utils.Pos;

public class PlayerImpl implements Player {
    private Worker w1;
    private Worker w2;

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
