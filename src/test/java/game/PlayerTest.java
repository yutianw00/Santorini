package game;

import game.impl.PlayerImpl;
import game.utils.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new PlayerImpl();
    }

    @Test
    public void setWorkerATest() {
        assertTrue(player.setWorkerA(new Pos(3,4)));
        assert(player.getWorkerA().getPos().equals(new Pos(3,4)));
    }

    @Test
    public void setWorkerATestInvalidInput1() {
        assertFalse(player.setWorkerA(new Pos(4,5)));
    }

    @Test
    public void setWorkerATestInvalidInput2() {
        assertFalse(player.setWorkerA(new Pos(-1,0)));
    }

    @Test
    public void setWorkerB() {
        assertTrue(player.setWorkerB(new Pos(0,0)));
        assert(player.getWorkerB().getPos().equals(new Pos(0,0)));
    }

}