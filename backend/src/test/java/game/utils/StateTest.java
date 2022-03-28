package game.utils;

import game.Board;
import game.Game;
import game.Worker;
import game.impl.BoardImpl;
import game.impl.WorkerImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void testToString() {
        Worker w11 = new WorkerImpl(new Pos(1,1));
        Worker w12 = new WorkerImpl(new Pos(1,2));
        Worker w21 = new WorkerImpl(new Pos(2,1));
        Worker w22 = new WorkerImpl(new Pos(2,2));
        Board board = new BoardImpl(w11, w12, w21, w22);
        State state = new State(board, 1, Game.MOVE, 0);
        System.out.println(state.toString()); // judge manually
    }
}