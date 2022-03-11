package game.impl;

import game.Board;
import game.Player;
import game.Worker;
import game.utils.Pos;

public class PlayerImpl implements Player {
    private Worker w1;
    private Worker w2;

    public PlayerImpl() {
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
    public void setWorkerA(Pos pos) {
//        if (!posIsValid(pos)) {
//            return false;
//        }
        w1 = new WorkerImpl(pos);
//        return true;
    }

    @Override
    public void setWorkerB(Pos pos) {
//        if (!posIsValid(pos)) {
//            return false;
//        }
        w2 = new WorkerImpl(pos);
//        return true;
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
