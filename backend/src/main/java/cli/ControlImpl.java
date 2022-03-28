package cli;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.impl.GameImpl;
import game.utils.Pos;

public class ControlImpl implements Control{
    private Game game;
    private IO io;
    private Player player1;
    private Player player2;

    public ControlImpl() {
//        game = new GameImpl();
//        io = new IOImpl(game);
//        player1 = game.getCurrPlayer();
//        game.flipPlayer();
//        player2 = game.getCurrPlayer();
//        game.flipPlayer();
    }

    @Override
    public void play() {
        setup();

//        // player 1 pick pos for workers
//        playerPickPos(game.getCurrPlayer());
//        game.flipPlayer();
//
//        // player 2 pick pos for workers
//        playerPickPos(game.getCurrPlayer());
//        game.flipPlayer();
//
//        boolean gameEnds = false;
//        while (!gameEnds) {
//            Player player = game.getCurrPlayer();
//            playerTurn(player);
//            gameEnds = game.isFinished();
//            game.flipPlayer();
//        }
//        endGame(game.getWinner());
    }

    @Override
    public void setup() {
        io.printWelcome();
    }

    @Override
    public void playerPickPos(Player currPlayer) {
        //TODO
//        boolean valid = false;
//        while (!valid) {
//            Pos pos1 = io.playerPick(currPlayer, 1);
//            Pos pos2 = io.playerPick(currPlayer, 2);
//            valid = game.setWorker(currPlayer, pos1, pos2);
//            if (!valid) {
//                io.printPosErr();
//                continue;
//            }
//        }

        io.printBoard(game.getBoard());
    }

    @Override
    public void playerTurn(Player player) {
        Board board = game.getBoard();
        Worker[] workers = new Worker[1];
        while (true) {
            Pos pos = io.playerMove(workers); // worker will be filled with the correct worker for the player
            Worker worker = workers[0];
            System.out.println("here!" + worker.toString() + " " + pos.toString());
            if (!board.checkMove(worker, pos)) {
                io.printMoveErr();
                continue;
            }
            worker.setPos(pos);
            break;
        }
        io.printBoard(board);

        if (game.isFinished()) {
            endGame(player);
        }

        while (true) {
            Worker worker = workers[0];
            Pos pos = io.playerBuild(worker); // worker will be filled with the correct worker for the player
            if (!board.checkBuild(worker, pos)) {
                io.printBuildErr();
                continue;
            }
            board.build(pos);
            System.out.println("Build success!");
            break;
        }
        io.printBoard(board);
    }

    @Override
    public void endGame(Player player) {
        io.printWinner(player);
        System.exit(0);
    }
}