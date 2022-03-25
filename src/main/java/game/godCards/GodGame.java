package game.godCards;

import game.Board;
import game.Game;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

import java.util.List;

public class GodGame implements Game {

    private Game game;
    private GodPower god1, god2;
    private int winnerId = -1; // no winner yet
    private boolean hasFinished = false;

    public GodGame(Game game, String god1, String god2) {
        this.game = game;

        this.god1 = newGod(1, god1);
        this.god2 = newGod(2, god2);
    }

    @Override
    public int getChosenWorkerId() {
        return game.getChosenWorkerId();
    }

    /**
     * create a new GodPower object based on the god name, using strategy pattern
     * @param playerId The player id that has the god power
     * @param godName the name of the god power
     * @return the newly created god power class
     */
    private GodPower newGod(int playerId, String godName) {
        if (godName.equals("Demeter")) {
            return new Demeter(playerId, game);
        } else if (godName.equals("Minotaur")) {
            return new Minotaur(playerId, game);
        } else if (godName.equals("Pan")) {
            return new Pan(playerId, game);
        } else {
            System.out.println("Error: God not exist!");
            return null;
        }
    }

    @Override
    public State chooseMove(Pos pos) {
        return game.chooseMove(pos);
    }

    @Override
    public State chooseBuild(Pos pos) {
        return game.chooseBuild(pos);
    }

    @Override
    public void setBoard(Board board) {
        game.setBoard(board);
    }

    @Override
    public boolean undo() {
        return game.undo();
    }

    @Override
    public List<State> getHistory() {
        return game.getHistory();
    }

    @Override
    public void setNextAction(int action) {
        game.setNextAction(action);
    }

    @Override
    public int getNextAction() {
        return game.getNextAction();
    }

    @Override
    public boolean isFinished() {
        return game.isFinished() || hasFinished;
    }

    @Override
    public int getNextPlayerId() {
        return game.getNextPlayerId();
    }

    @Override
    public int getWinner() {
        if (game.isFinished()) {
            return game.getWinner();
        } else {
            return this.winnerId;
        }

    }

    @Override
    public void flipPlayer() {
        game.flipPlayer();
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }

    @Override
    public State getCurrState() {
        return game.getCurrState();
    }

    @Override
    public State usePower(int playerId, int workerId, Pos pos) {
        if (playerId == 1) {
            if (god1.canDoAction(game.getCurrState())) {
                return god1.action(workerId, pos);
            } else {
                return null;
            }
        } else { // playerid = 2
            if (god2.canDoAction(game.getCurrState())) {
                return god2.action(workerId, pos);
            } else {
                return null;
            }
        }
    }

    @Override
    public State move(int playerId, int workerId, Pos pos) {

        boolean p1Win = god1.checkWin(playerId, workerId, pos);
        boolean p2Win = god2.checkWin(playerId, workerId, pos);
        State res = game.move(playerId, workerId, pos);
        if (res != null) {
            // meaning the operation is performed successfully
            if (p1Win) {
                winnerId = 1;
                hasFinished = true;
            } else if (p2Win) {
                winnerId = 2;
                hasFinished = true;
            }
        }
        return res;
    }

    @Override
    public State build(int playerId, int workerId, Pos pos) {
        State res = game.build(playerId, workerId, pos);
        if (res != null) {
            if (playerId == 1) {
                god1.storeInfo(pos);
            } else if (playerId == 2) {
                god2.storeInfo(pos);
            }
        }
        return res;
    }

    @Override
    public State setWorker(int playerId, int workerId, Pos pos) {
        return game.setWorker(playerId, workerId, pos);
    }
}
