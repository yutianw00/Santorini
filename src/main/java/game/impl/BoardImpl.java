package game.impl;

import game.Board;
import game.Grid;
import game.Worker;
import game.utils.Pos;

import java.util.Arrays;
import java.util.List;

public class BoardImpl implements Board {

    private final int numCols = 5;
    private final int numRows = 5;
    List<Worker> workers; // 4 workers in workers, with order P1-A, P1-B, P2-A, P2-B
    Grid grids[][];

    public BoardImpl() {
        Worker worker1A = null;
        Worker worker1B = null;
        Worker worker2A = null;
        Worker worker2B = null;
        workers = Arrays.asList(worker1A, worker1B, worker2A, worker2B);

        grids = new Grid[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
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
            if (worker.getPos() == pos) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBound(Pos pos) {
        int r = pos.getRow();
        int c = pos.getCol();

        if (r < 0 || r >= numRows) {
            return false;
        }
        if (c < 0 || c >= numCols) {
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

        // cannot move to grids more than 1 level higher
        int currLevel = getGrid(currPos).getLevels();
        if (getGrid(pos).getLevels() > currLevel + 1) {
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
    public Worker getWorker(int playerId, int workerId) {
        if (playerId == 1) {
            if (workerId == 1) {
                return workers.get(0);
            } else if (workerId == 2) {
                return workers.get(1);
            }
        } else if (playerId == 2) {
            if (workerId == 1) {
                return workers.get(2);
            } else if (workerId == 2) {
                return workers.get(3);
            }
        }
        return null;
    }

    @Override
    public void setWorker(int playerId, int workerId, Worker worker) {
        if (playerId == 1) {
            if (workerId == 1) {
                workers.set(0, worker);
            } else if (workerId == 2) {
                workers.set(1, worker);
            }
        } else if (playerId == 2) {
            if (workerId == 1) {
                workers.set(2, worker);
            } else if (workerId == 2) {
                workers.set(3, worker);
            }
        }
    }
}
