package game.impl;

import game.Player;
import game.Worker;
import game.utils.Pos;

public class PlayerImpl implements Player {
    @Override
    public Worker getWorkerA() {
        return null;
    }

    @Override
    public Worker getWorkerB() {
        return null;
    }

    @Override
    public boolean placeWorkerA(Pos pos) {
        return false;
    }

    @Override
    public boolean placeWorkerB(Pos pos) {
        return false;
    }
}
