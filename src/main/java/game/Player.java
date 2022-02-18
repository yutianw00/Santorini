package game;

import game.utils.Pos;

public interface Player {
    void setWorkerA(Pos pos);
    void setWorkerB(Pos pos);
    Worker getWorkerA();
    Worker getWorkerB();
}
