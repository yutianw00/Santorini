package game;

import game.impl.GameImpl;
import game.utils.Pos;
import game.utils.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    State state;


    @Test
    public void setWorkerTest1() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        assertTrue(state.getNextAction() == Game.SETUP);
        assertTrue(state.getBoard().hasWorker(new Pos(0,1)));
        assertTrue(state.getPlayerId() == 1);
    }

    @Test
    public void setWorkerTest2Fail() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        state = game.setWorker(1, 2, new Pos(0,1));
        assertTrue(state == null);
    }

    @Test
    public void setWorkerTest2() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        state = game.setWorker(1, 2, new Pos(1,1));
        assertTrue(state.getNextAction() == Game.SETUP);
        assertTrue(state.getBoard().hasWorker(new Pos(0,1)));
        assertTrue(state.getBoard().hasWorker(new Pos(1,1)));
        assertTrue(state.getPlayerId() == 2);
    }

    @Test
    public void setWorkerTest3() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        state = game.setWorker(1, 2, new Pos(1,1));
        state = game.setWorker(2, 1, new Pos(1,2));
        state = game.setWorker(2, 2, new Pos(0,2));
        assertTrue(state.getNextAction() == Game.CHOOSEMOVE);
        assertTrue(state.getBoard().hasWorker(new Pos(0,1)));
        assertTrue(state.getBoard().hasWorker(new Pos(1,1)));
        assertTrue(state.getBoard().hasWorker(new Pos(1,2)));
        assertTrue(state.getBoard().hasWorker(new Pos(0,2)));
        assertTrue(state.getPlayerId() == 1);
    }

    @Test
    public void undoTest1() {
        game = new GameImpl();
        assert(game.undo() == null);
    }

    @Test
    public void undoTest2() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        state = game.setWorker(1, 2, new Pos(1,1));
        assertTrue(game.getHistory().size() == 3);
        State state0 = game.getHistory().get(0);
        game.undo();
        assertTrue(game.getHistory().size() == 2);
        State state1 = game.getHistory().get(0);
        assertTrue(state0 == state1);

    }

    @Test
    public void isFinishedTestFalse() {
        game = new GameImpl();
        assertFalse(game.isFinished());
    }

    @Test
    public void flipPlayer() {
        game = new GameImpl();
        assertTrue(game.getNextPlayerId() == 1);
        game.flipPlayer();
        assertTrue(game.getNextPlayerId() == 2);
    }

    private Game setupGame() {
        game = new GameImpl();
        state = game.setWorker(1, 1, new Pos(0,1));
        state = game.setWorker(1, 2, new Pos(1,1));
        state = game.setWorker(2, 1, new Pos(1,2));
        state = game.setWorker(2, 2, new Pos(0,2));
        return game;
    }

    @Test
    public void moveTrueTest() {
        Game game = setupGame();
        int prevHistoryLen = game.getHistory().size();
        State state = game.move(1, 1, new Pos(0,0));
        assertFalse(state == null);
        assertTrue(game.getBoard().hasWorker(new Pos(0,0)));
        assertFalse(game.getBoard().hasWorker(new Pos(0,1)));
        int currHistoryLen = game.getHistory().size();
        assertTrue(currHistoryLen == prevHistoryLen + 1);
    }

    @Test
    public void moveFalseTest() {
        Game game = setupGame();
        State state = game.move(1, 1, new Pos(1,1));
        assertTrue(state == null);
    }

    @Test
    public void buildTrueTest() {
        Game game = setupGame();
        int prevHistoryLen = game.getHistory().size();
        state = game.move(1, 1, new Pos(0,0));
        state = game.build(1, 1, new Pos(0,1));
        assertFalse(state == null);
        assertFalse(game.getBoard().hasWorker(new Pos(0,1)));
        assertTrue(game.getBoard().getGrid(new Pos(0,1)).getLevels() == 1);
        int currHistoryLen = game.getHistory().size();
        assertTrue(currHistoryLen == prevHistoryLen + 2);
    }

    @Test
    public void buildTrueFalse() {
        Game game = setupGame();
        state = game.move(1, 1, new Pos(0,0));
        state = game.build(1, 1, new Pos(1,1));
        assertTrue(state == null);
    }


}