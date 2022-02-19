package game;

import game.impl.GameImpl;
import game.impl.WorkerImpl;
import game.utils.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

    @Before
    public void setup() {

    }

    @Test
    public void setupWorkerTest() {
        game = new GameImpl();
        // in actual impl, the worker will be created by Player.setWorker
        Worker w11 = new WorkerImpl(new Pos(0,0));
        assertTrue(game.setupWorker(1, 1, w11));
    }

    @Test
    public void setupWorkerRepetitiveTest() {
        game = new GameImpl();
        Worker w11 = new WorkerImpl(new Pos(0,0));
        game.setupWorker(1, 1, w11);
        Worker w21 = new WorkerImpl(new Pos(0,0));
        assertFalse(game.setupWorker(2, 1, w21));
    }

    @Test
    public void isFinished() {
    }

    @Test
    public void getPlayer() {
    }

    @Test
    public void getWinner() {
    }

    @Test
    public void flipPlayer() {
    }
}