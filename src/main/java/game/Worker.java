package game;

import game.utils.Pos;

public interface Worker {
    void move(Pos pos);
    Pos getPos();
}
