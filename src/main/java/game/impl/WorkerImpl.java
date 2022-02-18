package game.impl;

import game.Worker;
import game.utils.Pos;

public class WorkerImpl implements Worker {
    @Override
    public boolean move(Pos pos) {
        return false;
    }

    @Override
    public boolean build(Pos pos) {
        return false;
    }

    @Override
    public Pos getPos() {
        return null;
    }
}
