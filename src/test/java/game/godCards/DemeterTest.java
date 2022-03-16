package game.godCards;

import game.Game;
import game.impl.GameImpl;
import game.utils.Pos;
import game.utils.State;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemeterTest {
    State state;

    @Test
    public void canDoActionTestFalse1() {
        Game game = new GameImpl();
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestFalse2() {
        Game game = new GameImpl();
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestFalse3() {
        Game game = new GameImpl();
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        state = game.move(1, 2, new Pos(1,1));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestFalse4() {
        Game game = new GameImpl();
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void actionInValid() {
        Game game = new GameImpl();
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));

        // move to invalid position
        state = game.usePower(1, 1, new Pos(3,3));
        assert(state == null);
    }

    @Test
    public void actionValid() {
        Game game = new GodGame(new GameImpl(), "Demeter", "Minotaur");
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        state = game.move(1, 1, new Pos(1,1));
        state = game.build(1,1, new Pos(0,0));

        // build one more time
        state = game.usePower(1, 1, new Pos(1,0));
        assert(state.getNextAction() == Game.MOVE);
        assert(state.getPlayerId() == 2);
    }
}