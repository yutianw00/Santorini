package game.godCards;

import game.utils.Pos;
import game.utils.State;

public interface GodPower {

    /**
     * return a description of the GodPower
     * @return description
     */
    String description();

    boolean checkWin(int playerId, int workerId, Pos pos);

    /**
     * check during the current game condition, if the player
     * can use his/her GodPower
     * @param state The current state of the game
     * @return true if god power can be used, false otherwise
     */
    boolean canDoAction(State state);

    /**
     * Taking the action using the god power
     * Side effect: the game global state variable (including next
     * turn's player, next action (move or build), etc.) may be changed
     * based on the ability of the god power
     * @param workerId The worker id
     * @param pos The position that the action would be performed on
     * @return
     */
    State action(int workerId, Pos pos);
}
