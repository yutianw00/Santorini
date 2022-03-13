package game.godCards;

import game.utils.State;

public interface GodPower {
    String description();
    boolean canDoAction(State state);
    State action();
}
