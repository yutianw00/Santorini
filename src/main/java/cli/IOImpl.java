package cli;

import game.Board;
import game.Game;
import game.Player;
import game.Worker;
import game.utils.Pos;

import java.util.Scanner;

public class IOImpl implements IO{

    Game game;
    Player player1;
    Player player2;
    Scanner scanner;

    public IOImpl(Game game) {
        this.game = game;
        player1 = game.getPlayer();
        game.flipPlayer();
        player2 = game.getPlayer();
        game.flipPlayer();
        scanner = new Scanner(System.in);  // Create a Scanner object
    }

    @Override
    public void printBuildErr() {
        System.out.println("Invalid build operation. You can only build at blocks adjacent to the selected " +
                "worker (including diagonal positions) within the board. Also, you cannot build on tower " +
                "that already has a doom (has 4 levels)");
        System.out.println("Please try again.");
    }

    @Override
    public void printWelcome() {
        String msg = "################################\n Welcome to the game Santorini! " +
                "\n################################";
        System.out.println(msg);
    }

    String getCurrPlayer(Player player) {
        String currPlayerStr;
        if (player == player1) {
            currPlayerStr = "Player 1";
        } else {
            currPlayerStr = "Player 2";
        }
        return currPlayerStr;
    }

    private Pos parsePos(String str) {
        String pos = str.substring(1, str.length()-1);
        String[] idxStrLst = pos.split(",");
        if (idxStrLst.length != 2) {
            return null;
        }
        try {
            int r = Integer.valueOf(idxStrLst[0]);
            int c = Integer.valueOf(idxStrLst[1]);
            return (new Pos(r,c));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Pos playerPick(Player player, int workerId) {
        String playerStr = getCurrPlayer(player);
        System.out.println("It's " + playerStr + "'s turn. " +
                "Please following the prompts and set up your worker!");
        printBoard(game.getBoard());
        System.out.println(playerStr + ": Set up worker " + workerId + ". Please indicate the " +
                "worker 1's initial position on the board below, " +
                "in the form of (x,y): ");

        Pos pos;
        while (true) {
            String posStr = scanner.nextLine();
            pos = parsePos(posStr);
            if (pos == null) {
                System.out.println("Wrong input format. Try again! ");
            } else {
                break;
            }
        }
        return pos;
    }

    @Override
    public void printBoard(Board board) {
        System.out.println("======== Current board: ===========");
        System.out.print(board.toString());
        System.out.println("===================================");

    }

    @Override
    public void printWinner(Player player) {
        String playerStr = getCurrPlayer(player);
        System.out.println("##########################");
        System.out.println("  " + playerStr + ", YOU WIN! ");
        System.out.println("##########################");

    }

    @Override
    public void printPosErr() {
        System.out.println("Error: invalid position.");
    }

    @Override
    public void printMoveErr() {
        System.out.println("Invalid move position. You can only move workers to adjacency blocks " +
                "(including diagonal positions) within the board. Also, you cannot move your worker " +
                "more than 1 level up. ");
        System.out.println("Please try again.");
    }

    @Override
    public Pos playerMove(Worker[] workers) {
        Player player = game.getPlayer();
        String playerStr = getCurrPlayer(player);
        System.out.println("It's " + playerStr + "'s turn. " +
                "Please following the prompts and MOVE your worker!");
        printBoard(game.getBoard());

        Worker workerA = player.getWorkerA();
        Worker workerB = player.getWorkerB();
        System.out.println("Worker A is at " + workerA.getPos().toString());
        System.out.println("Worker B is at " + workerB.getPos().toString());
        System.out.println("You can move one of these two workers.");
        System.out.println("Type 'A' if you want to move worker A, and 'B' if you want to move worker B");
        while (true) {
            String workerStr = scanner.nextLine();
            if (workerStr.equals("A")) {
                workers[0] = workerA;
                break;
            } else if (workerStr.equals("B")){
                workers[0] = workerB;
                break;
            } else {
                System.out.println("Error: invalid input. Please try again.");
            }
        }

        System.out.println("Now move your worker. Please indicate where you want to move the worker " +
                "in the form of (x,y): ");

        return getPos();

    }

    @Override
    public Pos playerBuild(Worker worker) {
        Player player = game.getPlayer();
        String playerStr = getCurrPlayer(player);
        System.out.println("It's still " + playerStr + "'s turn. " +
                "Please following the prompts and let your worker to BUILD!");

        System.out.println("Your worker's position is at " + worker.getPos().toString() +
                " Please indicate where you want to build " +
                "in the form of (x,y): ");

        return getPos();
    }

    private Pos getPos() {
        Pos pos;
        while (true) {
            String posStr = scanner.nextLine();
            pos = parsePos(posStr);
            if (pos == null) {
                System.out.println("Wrong input format. Try again! ");
            } else {
                break;
            }
        }
        return pos;
    }
}
