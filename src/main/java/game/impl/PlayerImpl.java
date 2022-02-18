package game.impl;

import game.Board;
import game.Player;
import game.Worker;
import game.utils.Pos;

public class PlayerImpl implements Player {
    Worker w1;
    Worker w2;

    public PlayerImpl() {
    }

    private boolean posIsValid(Pos pos) {
        int r = pos.getRow();
        int c = pos.getCol();
        int numRows = Board.numRows;
        int numCols = Board.numCols;
        if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
            return false;
        }
        return true;
    }

    @Override
    public void setWorkerA(Pos pos) {
        w1 = new WorkerImpl(pos);
    }

    @Override
    public void setWorkerB(Pos pos) {
        w2 = new WorkerImpl(pos);
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
