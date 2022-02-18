package game;

public interface Game {
    void setup();
    boolean isFinished();
    Player getNextPlayer();
    Player getWinner();
}
