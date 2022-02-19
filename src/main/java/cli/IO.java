package cli;

import game.Board;
import game.Player;
import game.Worker;
import game.utils.Pos;

/**
 * An IO interface to achieve the IO interactions
 * with the user to make the game a CLI game
 *
 * (Since performing a CLI game and a Main function is optional,
 * no specific documentation is written for Classes/Interfaces in cli package)
 */
public interface IO {
    void printWelcome();
    Pos playerPick(Player player, int workerId);
    void printBoard(Board board);
    void printWinner(Player player);
    void printPosErr();
    void printMoveErr();
    void printBuildErr();
    Pos playerMove(Worker[] workers); // prompt the player to move and choose move position, and assign worker
    Pos playerBuild(Worker worker);

}
