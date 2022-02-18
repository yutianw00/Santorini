package game.impl;

import game.Grid;
import game.utils.Pos;

public class GridImpl implements Grid {

    @Override
    public void build() {

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

    @Override
    public boolean isComplete() {
        return false;
    }
}
