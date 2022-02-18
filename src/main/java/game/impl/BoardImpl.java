package game.impl;

import game.Board;
import game.Grid;
import game.Worker;
import game.utils.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImpl implements Board {

    List<Worker> workers; // 4 workers in workers, with order P1-A, P1-B, P2-A, P2-B

    public BoardImpl() {
        Worker worker1A = null;
        Worker worker1B = null;
        Worker worker2A = null;
        Worker worker2B = null;
        workers = Arrays.asList(worker1A, worker1B, worker2A, worker2B);
    }

    @Override
    public boolean hasWorker(Pos pos) {
        for (Worker worker : workers) {
            if (worker.getPos() == pos) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkMove(Worker worker, Pos pos) {
        return false;
    }

    @Override
    public boolean checkBuild(Worker worker, Pos pos) {
        return false;
    }

    @Override
    public void build(Pos pos) {

    }

    @Override
    public Grid getGrid(Pos pos) {
        return null;
    }

    @Override
    public Worker getWorker(int playerId, int workerId) {
        return null;
    }

    @Override
    public void setWorker(int playerId, int workerId, Worker worker) {

    }
}
