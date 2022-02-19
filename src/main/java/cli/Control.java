package cli;

import game.Player;

public interface Control {
    void play();
    void setup();
    void playerPickPos(int playerId);
    void playerTurn(Player player);
    void endGame(Player player);
}
