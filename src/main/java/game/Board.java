package game;

import game.utils.Pos;
import game.Grid;

public interface Board {
    boolean hasWorker(Pos pos);
    boolean checkMove(Worker worker, Pos pos);
    boolean checkBuild(Worker worker, Pos pos);
    void build(Pos pos);
    Grid getGrid(Pos pos);
    Worker getWorker(int playerId, int workerId);
    void setWorker(int playerId, int workerId, Worker worker);
//    boolean checkBuild(Pos pos);

}
