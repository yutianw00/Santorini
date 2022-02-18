package game;

import game.utils.Pos;

public interface Worker {
    boolean move(Pos pos);
    boolean build(Pos pos);
}
