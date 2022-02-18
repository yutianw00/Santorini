package game;

import game.utils.Pos;

public interface Grid {
    Pos getPos();
    void build();
    boolean hasDome();
    int getLevels();
    boolean isComplete();
}
