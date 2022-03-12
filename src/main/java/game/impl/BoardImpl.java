package game.impl;

import game.Board;
import game.Game;
import game.Grid;
import game.Worker;
import game.utils.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImpl implements Board {

    // 4 workers in workers, with order P1-A, P1-B, P2-A, P2-B
    private List<Worker> workers;
    private Grid[][] grids;

    private final int idx1A = 0;
    private final int idx1B = 1;
    private final int idx2A = 2;
    private final int idx2B = 3;

    private void initGrid() {
        Grid[][] grids = new Grid[NUMROWS][NUMCOLS];
        for (int i = 0; i < NUMROWS; i++) {
            for (int j = 0; j < NUMCOLS; j++) {
                grids[i][j] = new GridImpl();
            }
        }
        this.grids = grids;
    }

    public BoardImpl(Worker w11, Worker w12, Worker w21, Worker w22) {
        Worker worker1A = w11;
        Worker worker2A = w21;
        Worker worker1B = w12;
        Worker worker2B = w22;
        workers = Arrays.asList(worker1A, worker1B, worker2A, worker2B);
        initGrid();
    }

    public BoardImpl(List<Worker> workers) {
        this.workers = workers;
        initGrid();
    }

    @Override
    public boolean hasWorker(Pos pos) {
        for (Worker worker : workers) {
            if (worker == null) {
                continue; // worker is not initialized yet
            }
            if (worker.getPos().equals(pos)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInBound(Pos pos) {
        int r = pos.getRow();
        int c = pos.getCol();

        if (r < 0 || r >= NUMROWS) {
            return false;
        }
        if (c < 0 || c >= NUMCOLS) {
            return false;
        }

        return true;
    }

    private boolean isAdjacent(Pos pos1, Pos pos2) {
        int r1 = pos1.getRow();
        int c1 = pos1.getCol();

        int r2 = pos2.getRow();
        int c2 = pos2.getCol();

        if (Math.abs(c2 - c1) > 1 || Math.abs(r2 - r1) > 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkMove(Worker worker, Pos pos) {
        // cannot move out of bound
        if (!isInBound(pos)) {
            return false;
        }

        Pos currPos = worker.getPos();
        // cannot move to non-adjacent places
        if (!isAdjacent(currPos, pos)) {
            return false;
        }

        // place cannot be occupied already
        if (hasWorker(pos)) {
            return false;
        }

        // cannot move to grids more than 1 level higher,
        // and cannot move to a grid that is completed (with a dome)
        int currLevel = getGrid(currPos).getLevels();
        Grid grid = getGrid(pos);
        if (!grid.isValidLevel(currLevel)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkBuild(Worker worker, Pos pos) {
        // cannot build out of bound
        if (!isInBound(pos)) {
            return false;
        }

        Pos currPos = worker.getPos();
        // cannot build to non-adjacent places
        if (!isAdjacent(currPos, pos)) {
            return false;
        }

        // place cannot be occupied already
        if (hasWorker(pos)) {
            return false;
        }

        // the tower cannot be already completed (with a dome on it)
        if (getGrid(pos).isComplete()) {
            return false;
        }

        return true;
    }

    @Override
    public void build(Pos pos) {
        Grid grid = getGrid(pos);
        grid.build();
    }

    @Override
    public Grid getGrid(Pos pos) {
        int r = pos.getRow();
        int c = pos.getCol();
        return grids[r][c];
    }

    @Override
    public Board copyBoard() {
        List<Worker> newWorkerLst = new ArrayList<>();
        for (Worker w : this.workers) {
            newWorkerLst.add(w.copy());
        }
        return new BoardImpl(newWorkerLst);
    }

    @Override
    public List<Worker> getWorkers() {
        return this.workers;
    }

    @Override
    public Board setBoardWorker(int playerId, int workerId, Pos pos) {

        Board newBoard = copyBoard();
        int workerIdx = (playerId - 1) * Game.NUMPLAYERS + (workerId - 1);
        Worker worker = newBoard.getWorkers().get(workerIdx);
        worker.setPos(pos);

        return newBoard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUMROWS; i++) {
            for (int j = 0; j < NUMCOLS; j++) {

                Pos pos = new Pos(i, j);

                // figure out levels
                String levels = Integer.toString(getGrid(pos).getLevels());

                // figure out workers
                char leftSign = ' ';
                char rightSign = ' ';
                if (hasWorker(pos)) {
                    if (workers.get(0).getPos().equals(pos)
                    || workers.get(1).getPos().equals(pos)) {
                        leftSign = '(';
                        rightSign = ')';
                    } else {
                        leftSign = '[';
                        rightSign = ']';
                    }
                }
                sb.append(" " + leftSign + levels + rightSign + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
