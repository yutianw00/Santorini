package game;

import game.impl.BoardImpl;
import game.impl.GameImpl;
import game.utils.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

//    Board board;
//    @Before
//    public void setup() {
////        board = new BoardImpl();
//    }
//
//    @Test
//    public void hasWorkerTestFalse() {
//        assertFalse(board.hasWorker(new Pos(0,0)));
//    }
//
//    @Test
//    public void hasWorkerTestTrue() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//        assertTrue(game.getBoard().hasWorker(new Pos(1,1)));
//    }
//
//    @Test
//    public void checkMoveTestTrue() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//        assertTrue(game.getBoard().checkMove(p1.getWorkerA(), new Pos(0,1)));
//    }
//
//    @Test
//    public void checkMoveTestFalseNotMove() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//        assertFalse(game.getBoard().checkMove(p1.getWorkerA(), new Pos(1,1)));
//    }
//
//    @Test
//    public void checkMoveTestFalseMoveFar() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//        assertFalse(game.getBoard().checkMove(p1.getWorkerA(), new Pos(1,3)));
//    }
//
//    @Test
//    public void checkMoveTestTrueMoveDiagonal() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//        assertTrue(game.getBoard().checkMove(p1.getWorkerA(), new Pos(2,2)));
//    }
//
//    @Test
//    public void checkBuildTestTrueAdjacent() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//
//        Board board = game.getBoard();
//        assertTrue(board.checkBuild(p1.getWorkerA(), new Pos(0,1)));
//    }
//
//    @Test
//    public void checkBuildTestTrueDiagonal() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//
//        Board board = game.getBoard();
//        assertTrue(board.checkBuild(p1.getWorkerA(), new Pos(2,2)));
//    }
//
//    @Test
//    public void checkBuildTestFalseHasBuilder() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//
//        Board board = game.getBoard();
//        assertFalse(board.checkBuild(p1.getWorkerA(), new Pos(1,1)));
//    }
//
//    @Test
//    public void checkBuildTestFalseTooFar() {
//        GameImpl game = new GameImpl();
//        Player p1 = game.getCurrPlayer();
//        p1.setWorkerA(new Pos(1,1));
//        game.getBoard().setWorker(1,1,p1.getWorkerA());
//
//        Board board = game.getBoard();
//        assertFalse(board.checkBuild(p1.getWorkerA(), new Pos(1,3)));
//    }
//
//    @Test
//    public void buildTest() {
//        GameImpl game = new GameImpl();
//        Board board = game.getBoard();
//        board.build(new Pos(2,2));
//        Grid grid = board.getGrid(new Pos(2,2));
//        assertTrue(grid.getLevels() == 1);
//    }

}