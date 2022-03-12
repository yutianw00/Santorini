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
//        player = new PlayerImpl();
    }

    @Test
    public void setWorkerATest() {
        player.setWorkerA(new Pos(3,4));
        assert(player.getWorkerA().getPos().equals(new Pos(3,4)));
    }


    @Test
    public void setWorkerB() {
        player.setWorkerB(new Pos(0,0));
        assert(player.getWorkerB().getPos().equals(new Pos(0,0)));
    }

}