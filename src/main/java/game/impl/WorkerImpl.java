package game.impl;

import game.Worker;
import game.utils.Pos;

public class WorkerImpl implements Worker {
    private Pos pos;

    public WorkerImpl(Pos pos) {

        this.pos = pos;
    }

    @Override
    public void move(Pos pos) {
        this.pos = pos;
    }

    @Override
    public Pos getPos() {
        return pos;
    }
}
