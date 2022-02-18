package game;

import game.utils.Pos;

public interface Player {
    Worker getWorkerA();
    Worker getWorkerB();
    boolean placeWorkerA(Pos pos);
    boolean placeWorkerB(Pos pos);
}
