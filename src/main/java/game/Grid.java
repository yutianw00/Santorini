package game;

import game.utils.Pos;

public interface Grid {
    Pos getPos();
    boolean buildBlock();
    boolean buildDome();
    boolean hasDome();
    int getLevels();
}
