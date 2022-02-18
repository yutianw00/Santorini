package game.impl;

import game.Player;
import game.Worker;

public class PlayerImpl implements Player {
    Worker w1;
    Worker w2;

    public PlayerImpl() {
        w1 = new WorkerImpl();
        w2 = new WorkerImpl();
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
