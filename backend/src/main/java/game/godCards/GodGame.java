package game.godCards;

//import game.*;
import game.Game;
import game.History;
import game.Player;
import game.Board;
import game.impl.HistoryImpl;
import game.utils.Pos;
import game.utils.State;

import java.util.List;

public class GodGame implements Game {

    private Game game;
    private GodPower god1, god2;
    private History<State> history = new HistoryImpl<>();

    public GodGame(Game game, String god1, String god2) {
        this.game = game;

        this.god1 = newGod(1, god1);
        this.god2 = newGod(2, god2);
        State originalState = createState(game.getBoard(), 1);
        originalState.addGod(this.god1, this.god2);
        history.addHistory(originalState);
    }

    @Override
    public State createState(Board board, int setWorkerId) {
        return game.createState(board, setWorkerId);
    }

    @Override
    public void restore(State state) {
        this.game.restore(state);
    }

    @Override
    public Player getPlayer(int playerId) {
        return game.getPlayer(playerId);
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
        State res = game.chooseMove(pos);
        if (res != null) {
            res.addGod(god1, god2);
            history.addHistory(res);
        }
        return res;
    }

    @Override
    public void setBoard(Board board) {
        game.setBoard(board);
    }

    @Override
    public State undo() {
        return history.undo();
    }

    @Override
    public List<State> getHistory() {
        return this.history.getHistory();
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
        return game.isFinished();
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
            return -1;
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
        return history.getLast();
    }

    @Override
    public State usePower(int playerId, int workerId, Pos pos) {
        State res;
        if (playerId == 1) {
            res = god1.action(workerId, pos);
        } else { // playerid = 2
            res = god2.action(workerId, pos);
        }
        if (res != null) {
            res.addGod(god1, god2);
            history.addHistory(res);
        }
        return res;
    }

    @Override
    public void setWinner(int playerId) {
        game.setWinner(playerId);
    }

    @Override
    public State move(int playerId, int workerId, Pos pos) {

        boolean p1Win = god1.checkWin(playerId, workerId, pos);
        boolean p2Win = god2.checkWin(playerId, workerId, pos);
        State res = game.move(playerId, workerId, pos);
        if (res != null) {
            // meaning the operation is performed successfully
            if (p1Win) {
                game.setNextAction(Game.FINISH);
                game.setWinner(1);
                res = game.createState(game.getBoard(), workerId);
            } else if (p2Win) {
                game.setNextAction(Game.FINISH);
                game.setWinner(2);
                res = game.createState(game.getBoard(), workerId);
            }
            res.addGod(god1, god2);
            history.addHistory(res);
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
        if (res != null) {
            res.addGod(god1, god2);
            history.addHistory(res);
        }
        return res;
    }

    @Override
    public State setWorker(int playerId, int workerId, Pos pos) {
        State res = game.setWorker(playerId, workerId, pos);
        if (res != null) {
            res.addGod(god1, god2);
            history.addHistory(res);
        }
        return res;
    }

    @Override
    public State setupPower(int playerId, int workerId) {
        State res;
        if (playerId == 1){
            res = god1.setupPower(workerId);
        } else {
            res = god2.setupPower(workerId);
        }
        res.addGod(god1, god2);
        history.addHistory(res);
        return res;
    }
}
