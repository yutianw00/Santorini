package game.godCards;

import game.Board;
import game.Game;
import game.impl.GameImpl;
import game.utils.Pos;
import game.utils.State;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinotaurTest {

    State state;

    @Test
    public void canDoActionTestFalse1() {
        Game game = new GameImpl();
        GodPower demeter = new Minotaur(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestFalse2() {
        Game game = new GameImpl();
        GodPower demeter = new Minotaur(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestFalse3() {
        Game game = new GameImpl();
        GodPower demeter = new Minotaur(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        state = game.move(1, 2, new Pos(1,1));
        assertFalse(demeter.canDoAction(state));
    }

    @Test
    public void canDoActionTestTrue() {
        Game game = new GameImpl();
        GodPower demeter = new Minotaur(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        assertTrue(demeter.canDoAction(state));
    }

    @Test
    public void actionInValid() {
        Game game = new GodGame(new GameImpl(), "Minotaur", "Demeter");
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));

        // move to invalid position (0,2) is occupied
        state = game.usePower(1, 1, new Pos(0,1));
        assert(state == null);
    }

    @Test
    public void actionValid() {
        Game game = new GodGame(new GameImpl(), "Minotaur", "Demeter");
        GodPower demeter = new Demeter(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(3,3));

        state = game.usePower(1, 2, new Pos(0,2));
        assert(state != null);
        assert(state.getPlayerId() == 1);
        assert(state.getNextAction() == Game.BUILD);

        Board board = game.getBoard();
        assert(board.hasWorker(new Pos(0,3)));
        assert(board.hasWorker(new Pos(0,2)));
        assertFalse(board.hasWorker(new Pos(0,1)));

    }

}