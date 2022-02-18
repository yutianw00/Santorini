package game;

import game.utils.Pos;

public interface Game {
    boolean setupWorker(int playerId, int workerId, Worker worker);
    boolean isFinished();
    Player getPlayer();
    Player getWinner();
    void flipPlayer();
}
