package com.example.gameoflife.rules;

import com.example.gameoflife.model.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class containing the core rules of Conway's Game of Life.
 */
public class LifeRules {

    private static final Logger logger = LoggerFactory.getLogger(LifeRules.class);

    /**
     * Calculates the next generation of the game grid based on the current state
     * using Conway's Game of Life rules:
     *
     * 1. Any live cell with 2 or 3 live neighbors survives.
     * 2. Any dead cell with exactly 3 live neighbors becomes a live cell.
     * 3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
     *
     * @param gridObj The current game grid wrapped in a Grid object.
     * @return A new 2D boolean array representing the next generation.
     */
    public static boolean[][] nextGeneration(Grid gridObj) {
        boolean[][] current = gridObj.getGrid();
        int size = gridObj.getSize();
        boolean[][] next = new boolean[size][size];

        logger.info("Computing next generation for grid of size {}", size);

        // Apply Game of Life rules to each cell
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int liveNeighbors = countLiveNeighbors(current, x, y, size);

                // Rule application:
                // Cell survives if it has 2 or 3 neighbors
                // Dead cell becomes alive if it has exactly 3 neighbors
                next[x][y] = (current[x][y] && (liveNeighbors == 2 || liveNeighbors == 3)) ||
                        (!current[x][y] && liveNeighbors == 3);
            }
        }

        logger.info("Next generation computed successfully.");
        return next;
    }

    /**
     * Counts the number of live neighbors around a specific cell.
     *
     * @param grid The 2D boolean grid representing the game state.
     * @param x The row index of the cell.
     * @param y The column index of the cell.
     * @param size The size of the grid.
     * @return The number of live (true) neighbors around the cell.
     */
    private static int countLiveNeighbors(boolean[][] grid, int x, int y, int size) {
        int count = 0;

        // Check the 3x3 surrounding area around the cell (x, y)
        for (int i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                if (i == x && j == y) continue; // Skip the cell itself
                if (grid[i][j]) count++;       // Count live neighbors
            }
        }

        return count;
    }
}