package game.impl;

import game.Grid;
import game.utils.Pos;

public class GridImpl implements Grid {

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

    @Override
    public Pos getPos() {
        return null;
    }
}
