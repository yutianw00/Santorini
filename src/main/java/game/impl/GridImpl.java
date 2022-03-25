package game.impl;

import game.Grid;

public class GridImpl implements Grid {


    private int levels;
    private boolean isChoosen;

    public GridImpl() {
        this.levels = 0;
    }

    public GridImpl(int levels) {
        this.levels = levels;
    }

    @Override
    public Grid copyGrid() {
        return new GridImpl(this.levels);
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
        return (levels == DOMELEVEL);
    }

    @Override
    public boolean isValidLevel(int fromLevel) {
        if (this.isComplete()) {
            return false;
        }
        int currLevel = getLevels();
        if (currLevel > fromLevel + 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GridImpl{" +
                "levels=" + levels +
                '}';
    }

    @Override
    public boolean isChosen() {
        return this.isChoosen;
    }

    @Override
    public void choose() {
        this.isChoosen = true;
    }

    @Override
    public void unChoose() {
        this.isChoosen = false;
    }
}
