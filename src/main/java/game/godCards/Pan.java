package game.godCards;

import game.Board;
import game.Game;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

public class Pan implements GodPower{

    private Game game;
    private int playerId;
    private int otherPlayerId;

    public Pan(int playerId, Game game) {
        this.game = game;
        this.playerId = playerId;
        this.otherPlayerId = (playerId == 1) ? 2 : 1;
    }

    @Override
    public String description() {
        return "Pan: God of the Wild\n" +
                "Win Condition: You also win if " +
                "your Worker moves down two or " +
                "more levels.";
    }

    @Override
    public boolean checkWin(int playerId, int workerId, Pos pos) {
        Board board = game.getBoard();
        Worker worker = board.getWorker(playerId, workerId);
        int prevLevel = board.getGrid(worker.getPos()).getLevels();
        int newLevel = board.getGrid(pos).getLevels();
        return (newLevel == prevLevel - 2);
    }

    @Override
    public boolean canDoAction(State state) {
        /* Pan has no active actions, so it can never do any action */
        return false;
    }

    @Override
    public State action(int workerId, Pos pos) {
        /* Pan has no active actions */
        return null;
    }

    @Override
    public void storeInfo(Pos pos) {
        // no need to store, dummy
    }
}
