package game.godCards;

import game.Board;
import game.Game;
import game.History;
import game.Worker;
import game.utils.Pos;
import game.utils.State;

/**
 * Demeter:
 * Goddess of the Harvest
 *  Your Build: Your Worker may build one additional time,
 *  but not on the same space
 */
public class Demeter implements GodPower{
    private Game game;
    private int playerId;
    private Pos prevBuildPos;
    private boolean isUsing = false;

    public Demeter(int playerId, Game game) {
        this.game = game;
        this.playerId = playerId;
    }

    @Override
    public String description() {
        return "Demeter";
    }

    @Override
    public String toString() {
        return "Demeter {" + "playerId: " + playerId + "}";
    }

    @Override
    public boolean checkWin(int playerId, int workerId, Pos pos) {
        return false;
    }

    @Override
    public boolean canDoAction(State state) {
        /* The action can only be performed when the player with this
         * god power has just performed a successful build
         * As a result of the successful build, the player will be switched
         * to the other player, and the action will be set to MOVE
         */
        if (this.isUsing) {
            this.isUsing = false;
            return false;
        }
        if (state == null) {
            return false;
        }
        if (state.getPlayerId() == playerId) {
            return false;
        }
        if (state.getNextAction() != Game.CHOOSEMOVE) {
            return false;
        }
        return true;
    }

    /**
     * Demeter action: build one more time
     * This function make the builder to build one more time
     *
     * @param workerId the id of the worker who builds
     * @param pos the position of the newly built block
     *
     * @return The new {@link State} that results in this additional build,
     *  or null if the build is not successful (invalid operation)
     */
    @Override
    public State action(int workerId, Pos pos) {
        if (pos.equals(prevBuildPos)) {
            System.out.println("Demeter extra build: cannot build on the same position twice!");
            return null; // cannot build on the previous build position
        }

        game.setNextAction(Game.BUILD);
        State newState = game.build(this.playerId, workerId, pos);

        return newState;
    }

    @Override
    public State setupPower(int workerId) {
        game.setNextAction(Game.USEPOWER);
        game.flipPlayer();

        Board board = game.getBoard();
        int playerId = game.getNextPlayerId();
        Worker selectedWorker = board.getWorker(playerId, workerId);
        Board newBoard = board.chooseGrid(selectedWorker.getPos());

        this.isUsing = true;
        State newState = new State(newBoard, playerId, game.getNextAction());
        newState.usePower(game.getNextPlayerId());

        game.getHistory().add(newState);
        return newState;
    }

    @Override
    public void storeInfo(Pos pos) {
        this.prevBuildPos = pos;
    }

    @Override
    public boolean isUsing() {
        return this.isUsing;
    }
}
