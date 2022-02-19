package cli;

import game.Player;


/**
 * An Control interface to performs control of the whole game
 * make the game can be played on the terminal CLI directly
 *
 * (Since performing a CLI game and a Main function is optional,
 * no specific documentation is written for Classes/Interfaces in cli package)
 */
public interface Control {
    void play();
    void setup();
    void playerPickPos(Player player);
    void playerTurn(Player player);
    void endGame(Player player);
}
