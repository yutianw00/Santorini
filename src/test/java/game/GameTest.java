package game;

import game.impl.GameImpl;
import game.utils.Pos;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

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