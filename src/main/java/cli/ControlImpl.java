package cli;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.impl.GameImpl;
import game.utils.Pos;

public class ControlImpl implements Control{
    Game game;
    IO io;


    public ControlImpl() {
        game = new GameImpl();
        io = new IOImpl();
    }

    @Override
    public void play() {
        setup();

        playerPickPos(game.getPlayer());
        game.flipPlayer();
        playerPickPos(game.getPlayer());
        boolean gameEnds = false;
        while (!gameEnds) {
            Player player = game.getPlayer();
            playerTurn(player);
            gameEnds = game.isFinished();
            game.flipPlayer();
        }
        endGame(game.getWinner());
    }

    @Override
    public void setup() {
        io.printWelcome();
    }

    @Override
    public void playerPickPos(Player currPlayer) {
        boolean valid = false;
        while (!valid) {
            io.printPlayerPick(currPlayer, 1);
            Pos pos = io.getPos();
            currPlayer.setWorkerA(pos);
            Worker worker1 = currPlayer.getWorkerA();
            valid = game.setupWorker(1, 1, worker1);
            if (!valid) {
                io.printPosErr();
            }
        }
        io.printBoard(game.getBoard());
    }

    @Override
    public void playerTurn(Player player) {
        Board board = game.getBoard();
        while (true) {
            Worker worker = null;
            Pos pos = io.playerMove(worker);
            if (!board.checkMove(worker, pos)) {
                io.printMoveErr();
                continue;
            }
            worker.move(pos);
            break;
        }
        io.printBoard(board);
    }

    @Override
    public void endGame(Player player) {
        io.printWinner(player);
    }
}
