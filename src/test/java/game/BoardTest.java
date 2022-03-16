package game;

import game.impl.BoardImpl;
import game.impl.GameImpl;
import game.impl.WorkerImpl;
import game.utils.Pos;
import game.utils.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;
    Worker w11, w12, w21, w22;

    @Before
    public void setup() {
        w11 = new WorkerImpl(new Pos(1,1));
        w12 = new WorkerImpl(new Pos(1,2));
        w21 = new WorkerImpl(new Pos(2,1));
        w22 = new WorkerImpl(new Pos(2,2));
        board = new BoardImpl(w11, w12, w21, w22);
    }

    @Test
    public void hasWorkerTestFalse() {
        assertFalse(board.hasWorker(new Pos(0,0)));
    }

    @Test
    public void hasWorkerTestTrue() {
        board.hasWorker(new Pos(1,2));
    }

    @Test
    public void checkMoveTestFalseNotMove() {
        assertFalse(board.checkMove(w11, new Pos(1,1)));
    }

    @Test
    public void checkMoveTestFalseMoveFar() {
        assertFalse(board.checkMove(w11, new Pos(1,3)));
    }

    @Test
    public void checkMoveTestTrueMoveDiagonal() {
        assertTrue(board.checkMove(w11, new Pos(0,0)));
    }

    @Test
    public void checkBuildTestTrueAdjacent() {
        assertTrue(board.checkMove(w11, new Pos(0,1)));
    }

    @Test
    public void checkBuildTestTrueDiagonal() {
        assertTrue(board.checkBuild(w11, new Pos(0,0)));
    }

    @Test
    public void checkBuildTestFalseHasBuilder() {
        assertFalse(board.checkBuild(w11, new Pos(1,1)));
    }

    @Test
    public void checkBuildTestFalseTooFar() {
        assertFalse(board.checkBuild(w11, new Pos(4,4)));
    }

    @Test
    public void buildTest() {
        Board newBoard = board.build(new Pos(0,0));
        Grid grid = newBoard.getGrid(new Pos(0,0));
        assertTrue(grid.getLevels() == 1);
    }

}