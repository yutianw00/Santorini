package game.godCards;

import game.Board;
import game.Game;
import game.Grid;
import game.impl.GameImpl;
import game.utils.Pos;
import game.utils.State;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanTest {

    State state;

    @Test
    public void checkWinTestFalse() {
        Game game = new GameImpl();
        GodPower pan = new Pan(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        assertFalse(pan.checkWin(1, 1, new Pos(1,1)));
    }

    @Test
    public void checkWinTestTrue() {
        Game game = new GameImpl();
        GodPower pan = new Pan(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));
        Board board = game.getBoard();
        Grid grid1 = board.getGrid(new Pos(0,0));
        grid1.build();
        grid1.build();

        // in this case, player 1 worker 1 is moving from (0,0) of level 2 to (1,1) of level 0
        assertTrue(pan.checkWin(1, 1, new Pos(1,1)));
    }

    @Test
    public void canDoActionTest() {
        // can do Action is always false for Pan
        Game game = new GameImpl();
        GodPower pan = new Pan(1, game);
        state = game.setWorker(1, 1, new Pos(0,0));
        assertFalse(pan.canDoAction(state));
    }

    @Test
    public void actionInvalidTest() {
        Game game = new GodGame(new GameImpl(), "Pan", "Demeter");
        state = game.setWorker(1, 1, new Pos(0,0));
        state = game.setWorker(1, 2, new Pos(0,1));
        state = game.setWorker(2, 1, new Pos(0,2));
        state = game.setWorker(2, 2, new Pos(0,3));

        // move to invalid position (0,2) is occupied
        state = game.usePower(1, 1, new Pos(0,1));
        assert(state == null);
    }
}