package game.godCards;

import game.Board;
import game.Game;
import game.utils.Pos;
import game.utils.State;

import java.util.List;

public class GodGame implements Game {

    Game game;
    GodPower god1, god2;

    public GodGame(Game game, String god1, String god2) {
        this.game = game;

        this.god1 = newGod(1, god1);
        this.god2 = newGod(2, god2);
    }

    private GodPower newGod(int playerId, String godName) {
        if (godName.equals("Demeter")) {
            return new Demeter(playerId, game);
        } else if (godName.equals("Minotaur")) {
            return new Minotaur(playerId, game);
        }
        return null; // shouldn't reach here
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
        return god1.isFinished() || god2.isFinished();
    }

    @Override
    public int getNextPlayerId() {
        return game.getNextPlayerId();
    }

    @Override
    public int getWinner() {
        return game.getWinner();
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
        game.move(playerId, workerId, pos);
        return null;
    }

    @Override
    public State build(int playerId, int workerId, Pos pos) {
        return game.build(playerId, workerId, pos);
    }

    @Override
    public State setWorker(int playerId, int workerId, Pos pos) {
        return game.setWorker(playerId, workerId, pos);
    }
}
