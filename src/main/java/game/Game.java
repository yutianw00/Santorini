package game;

import game.utils.Pos;

public interface Game {
    boolean setupWorker(int playerId, int workerId, Worker worker);

    /* precondition: setupWorker is called for all four workers of two players */
    boolean isFinished();
    Player getPlayer();
    Player getWinner();
    void flipPlayer();
    public Board getBoard();
}
