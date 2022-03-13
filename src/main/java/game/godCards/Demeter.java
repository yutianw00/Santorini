package game.godCards;

import game.Game;
import game.utils.Pos;
import game.utils.State;

public class Demeter implements GodPower{
    private Game game;
    private int playerId;

    public Demeter(int playerId, Game game) {
        this.game = game;
        this.playerId = playerId;
    }

    @Override
    public String description() {
        return "Demeter: Goddess of the Harvest\n" +
                "Your Build: Your Worker may " +
                "build one additional time, but not " +
                "on the same space.";
    }

    @Override
    public String toString() {
        return "Demeter {" + "playerId: " + playerId + "}";
    }

    @Override
    public boolean canDoAction(State state) {
        /* The action can only be performed when the player with this
         * god power has just performed a successful build
         * As a result of the successful build, the player will be switched
         * to the other player, and the action will be set to MOVE
         */
        if (state.getPlayerId() == playerId) {
            return false;
        }
        if (state.getNextAction() != Game.MOVE) {
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
        game.setNextAction(Game.BUILD);
        game.flipPlayer();
        State newState = game.build(this.playerId, workerId, pos);
        return newState;
    }
}