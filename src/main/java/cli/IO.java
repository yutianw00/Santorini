package cli;

import game.Board;
import game.Player;
import game.Worker;
import game.utils.Pos;

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
