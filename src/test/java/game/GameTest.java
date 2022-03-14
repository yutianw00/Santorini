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
        assertTrue(state.getNextAction() == Game.MOVE);
        assertTrue(state.getBoard().hasWorker(new Pos(0,1)));
        assertTrue(state.getBoard().hasWorker(new Pos(1,1)));
        assertTrue(state.getBoard().hasWorker(new Pos(1,2)));
        assertTrue(state.getBoard().hasWorker(new Pos(0,2)));
        assertTrue(state.getPlayerId() == 1);
    }

    @Test
    public void undo() {
    }

    @Test
    public void isFinished() {
    }

    @Test
    public void flipPlayer() {
    }

    @Test
    public void move() {
    }

    @Test
    public void build() {
    }



//    @Test
//    public void isFinishedTestFalse() {
//        game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        game.flipPlayer();
//        Player p2 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(0,0));
//        p1.setWorkerB(new Pos(0,1));
//        p2.setWorkerA(new Pos(1,0));
//        p2.setWorkerB(new Pos(1,1));
//
//        assertFalse(game.isFinished());
//    }
//
//    @Test
//    public void flipPlayerTestFalse() {
//        game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        game.flipPlayer();
//        assertFalse(game.getCurrPlayer() == p1);
//    }
//
//    @Test
//    public void flipPlayerTestTrue() {
//        game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        game.flipPlayer();
//        game.flipPlayer();
//        assertTrue(game.getCurrPlayer() == p1);
//    }
}