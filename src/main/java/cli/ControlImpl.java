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
    Player player1;
    Player player2;

    public ControlImpl() {
        game = new GameImpl();
        io = new IOImpl(game);
        player1 = game.getPlayer();
        game.flipPlayer();
        player2 = game.getPlayer();
        game.flipPlayer();
    }

    @Override
    public void play() {
        setup();

        // player 1 pick pos for workers
        playerPickPos(game.getPlayer());
        game.flipPlayer();

        // player 2 pick pos for workers
        playerPickPos(game.getPlayer());
        game.flipPlayer();

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
        int playerId = (currPlayer == player1) ? 1 : 2;
        Board board = game.getBoard();
        while (!valid) {
            Pos pos = io.playerPick(currPlayer, 1);
            valid = currPlayer.setWorkerA(pos);
            if (!valid) {
                io.printPosErr();
                continue;
            }
            Worker worker1 = currPlayer.getWorkerA();
            valid = board.setWorker(playerId, 1, worker1);
            if (!valid) {
                System.out.println("position is occupied!");
                continue;
            }
        }

        valid = false;
        while (!valid) {
            Pos pos = io.playerPick(currPlayer, 2);
            valid = currPlayer.setWorkerB(pos);
            if (!valid) {
                io.printPosErr();
                continue;
            }
            Worker worker2 = currPlayer.getWorkerB();
            valid = game.setupWorker(playerId, 2, worker2);
            if (!valid) {
                System.out.println("position is occupied!");
                continue;
            }
        }
        io.printBoard(game.getBoard());
    }

    @Override
    public void playerTurn(Player player) {
        Board board = game.getBoard();
        Worker[] workers = new Worker[1];
        while (true) {
            Pos pos = io.playerMove(workers); // worker will be filled with the correct worker for the player
            Worker worker = workers[0];
            if (!board.checkMove(worker, pos)) {
                io.printMoveErr();
                continue;
            }
            worker.move(pos);
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
