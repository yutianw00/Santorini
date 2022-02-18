package game.impl;

import game.Grid;
import game.utils.Pos;

public class GridImpl implements Grid {
    int levels;

    public GridImpl() {
        this.levels = 0;
    }

    @Override
    public void build() {
        levels++;
    }

    @Override
    public int getLevels() {
        return levels;
    }

    @Override
    public boolean isComplete() {
        return (levels == 4);
    }
}
