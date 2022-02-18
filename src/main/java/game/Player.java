package game;

import game.utils.Pos;

public interface Player {
    boolean setWorkerA(Pos pos);
    boolean setWorkerB(Pos pos);
    Worker getWorkerA();
    Worker getWorkerB();
}
