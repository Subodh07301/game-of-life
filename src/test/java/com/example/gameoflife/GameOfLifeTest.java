package com.example.gameoflife;

import com.example.gameoflife.model.Grid;
import com.example.gameoflife.rules.LifeRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for verifying Game of Life rule implementation on different patterns.
 */
public class GameOfLifeTest {

    /**
     * Tests the Blinker pattern, an oscillator that alternates between vertical and horizontal lines.
     */
    @Test
    public void testNextGenerationWithBlinker() {
        Grid grid = new Grid(5);
        boolean[][] g = grid.getGrid();

        // Set horizontal blinker
        g[2][1] = true;
        g[2][2] = true;
        g[2][3] = true;

        grid.setGrid(g);
        boolean[][] next = LifeRules.nextGeneration(grid);

        // Expect vertical blinker in next generation
        assertTrue(next[1][2]);
        assertTrue(next[2][2]);
        assertTrue(next[3][2]);

        // Cells that should now be dead
        assertFalse(next[2][1]);
        assertFalse(next[2][3]);
    }

    /**
     * Tests the Block pattern, a stable configuration that should remain unchanged.
     */
    @Test
    public void testBlockStillLife() {
        Grid grid = new Grid(4);
        boolean[][] g = grid.getGrid();

        // Create a 2x2 block (still life)
        g[1][1] = true;
        g[1][2] = true;
        g[2][1] = true;
        g[2][2] = true;

        grid.setGrid(g);
        boolean[][] next = LifeRules.nextGeneration(grid);

        // The block should remain unchanged
        assertArrayEquals(g, next);
    }

    /**
     * Tests that an empty grid remains empty.
     */
    @Test
    public void testEmptyGridRemainsEmpty() {
        Grid grid = new Grid(3);
        boolean[][] initial = grid.getGrid();

        boolean[][] next = LifeRules.nextGeneration(grid);

        // All cells should remain dead
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertFalse(next[i][j]);
            }
        }

        assertArrayEquals(initial, next);
    }

    /**
     * Tests the Glider pattern's first generation after initialization.
     */
    @Test
    public void testGliderFirstStep() {
        Grid grid = new Grid(10);
        boolean[][] g = grid.getGrid();

        // Initial Glider pattern
        g[1][2] = true;
        g[2][3] = true;
        g[3][1] = true;
        g[3][2] = true;
        g[3][3] = true;

        grid.setGrid(g);
        boolean[][] next = LifeRules.nextGeneration(grid);

        // Validate specific expected live cells in the next generation
        assertTrue(next[2][1]);
        assertTrue(next[2][3]);
        assertTrue(next[3][2]);
        assertTrue(next[3][3]);
        assertTrue(next[4][2]);
    }
}
