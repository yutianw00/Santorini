package game.impl;

import game.Worker;
import game.utils.Pos;

public class WorkerImpl implements Worker {
    private Pos pos;

    public WorkerImpl(Pos pos) {
        this.pos = pos;
    }

    public WorkerImpl() {
        this.pos = null;
    }

    @Override
    public void setPos(Pos pos) {
        this.pos = pos;
    }

    @Override
    public Pos getPos() {
        return pos;
    }

    @Override
    public Worker copy() {
        return new WorkerImpl(this.pos);
    }
}
