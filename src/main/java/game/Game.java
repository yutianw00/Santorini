package game;

import game.utils.Pos;

public interface Game {
    boolean setupWorker(int playerId, int workerId, Pos pos);
    boolean isFinished();
    Player getPlayer();
    Player getWinner();
    void flipPlayer();
}
