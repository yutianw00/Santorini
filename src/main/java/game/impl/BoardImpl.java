package game.impl;

import game.Board;
import game.Grid;
import game.Worker;
import game.utils.Pos;

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

    public BoardImpl() {
        Worker worker1A = null;
        Worker worker2A = null;
        Worker worker1B = null;
        Worker worker2B = null;
        workers = Arrays.asList(worker1A, worker1B, worker2A, worker2B);

        grids = new Grid[NUMROWS][NUMCOLS];
        for (int i = 0; i < NUMROWS; i++) {
            for (int j = 0; j < NUMCOLS; j++) {
                grids[i][j] = new GridImpl();
            }
        }
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

    private boolean isInBound(Pos pos) {
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
        if (grid.isValidLevel(currLevel)) {
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
    public boolean setWorker(int playerId, int workerId, Worker worker) {
        if (hasWorker(worker.getPos())) {
            return false;
        }

        if (playerId == 1) {
            if (workerId == 1) {
                workers.set(idx1A, worker);
            } else if (workerId == 2) {
                workers.set(idx1B, worker);
            }
        } else if (playerId == 2) {
            if (workerId == 1) {
                workers.set(idx2A, worker);
            } else if (workerId == 2) {
                workers.set(idx2B, worker);
            }
        }
        return true;
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
