package game;

import game.impl.PlayerImpl;
import game.impl.WorkerImpl;
import game.utils.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    Worker w11, w12;

    @Before
    public void setUp() {
        w11 = new WorkerImpl(null);
        w12 = new WorkerImpl(null);
        player = new PlayerImpl(w11, w12);
    }

    @Test
    public void setWorkerTest() {
        player.setWorker(new Pos(1,1), 1);
        assert(w11.getPos().equals(new Pos(1,1)));
    }

    @Test
    public void setWorkerTestFalse() {
        player.setWorker(new Pos(1,2), 1);
        assertFalse(w11.getPos().equals(new Pos(1,1)));
    }


}