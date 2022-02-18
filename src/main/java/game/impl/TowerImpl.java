package game.impl;

import game.Tower;

public class TowerImpl implements Tower {
    @Override
    public boolean buildBlock() {
        return false;
    }

    @Override
    public boolean buildDome() {
        return false;
    }

    @Override
    public boolean hasDome() {
        return false;
    }

    @Override
    public int getLevels() {
        return 0;
    }
}
