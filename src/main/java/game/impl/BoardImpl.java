package game.impl;

import game.Board;
import game.Grid;
import game.Worker;
import game.utils.Pos;

public class BoardImpl implements Board {

    Worker worker1A;
    Worker worker1B;
    Worker worker2A;
    Worker worker2B;

    public BoardImpl() {
    }

    @Override
    public boolean hasWorker(Pos pos) {
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
