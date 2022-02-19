package game;

import game.utils.Pos;

public interface Game {


    /* precondition: setupWorker is called for all four workers of two players */
    boolean isFinished();
    Player getPlayer();
    Player getWinner();
    void flipPlayer();
    Board getBoard();
}
