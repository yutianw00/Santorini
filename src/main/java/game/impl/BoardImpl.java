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
    public Worker getWorker1A() {
        return null;
    }

    @Override
    public Worker getWorker1B() {
        return null;
    }

    @Override
    public Worker getWorker2A() {
        return null;
    }

    @Override
    public Worker getWorker2B() {
        return null;
    }
}
