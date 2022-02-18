package game.impl;

import game.Player;
import game.Worker;
import game.utils.Pos;

public class PlayerImpl implements Player {
    Worker w1;
    Worker w2;

    public PlayerImpl() {
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
