package game;

import game.impl.WorkerImpl;
import game.utils.Pos;
import org.junit.Before;
import org.junit.Test;

public class WorkerTest {
    Worker worker;

    @Before
    public void setUp() {
        Pos pos = new Pos(3,4);
        worker = new WorkerImpl(pos);
    }

    @Test
    public void moveTestNew() {
        Pos newPos = new Pos(2,5);
        worker.setPos(newPos);
        assert(worker.getPos().equals(newPos));
    }

    @Test
    public void moveTestOrig() {
        Pos pos = new Pos(3,4);
        worker = new WorkerImpl(pos);
        Pos newPos = new Pos(3,4); // same position
        worker.setPos(newPos);
        assert(worker.getPos().equals(newPos));
    }

}