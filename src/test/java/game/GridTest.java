package game;

import game.impl.GridImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;

    @Test
    public void buildTest() {
        grid = new GridImpl();
        grid.build();
        assert(grid.getLevels() == 1);
    }

    @Test
    public void buildTwiceTest() {
        grid = new GridImpl();
        grid.build();
        grid.build();
        assert(grid.getLevels() == 2);
    }

    @Test
    public void isCompleteTrueTest() {
        grid = new GridImpl();
        grid.build();
        grid.build();
        grid.build();
        grid.build();
        assertTrue(grid.isComplete());
    }

    @Test
    public void isCompleteFalseTest() {
        grid = new GridImpl();
        assertFalse(grid.isComplete());
        grid.build();
        grid.build();
        assertFalse(grid.isComplete());
    }
}