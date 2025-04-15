package com.example.gameoflife.pattern;

import com.example.gameoflife.exception.InvalidPatternException;
import com.example.gameoflife.model.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to initialize predefined patterns for Conway's Game of Life.
 */
public class PatternInitializer {

    private static final Logger logger = LoggerFactory.getLogger(PatternInitializer.class);

    /**
     * Initializes the "Glider" pattern in the center of the given grid.
     *
     * The Glider pattern:
     * ⬜⬛⬜
     * ⬜⬜⬛
     * ⬛⬛⬛
     *
     * @param grid The grid to populate with the Glider pattern.
     * @throws InvalidPatternException if the pattern does not fit within the grid boundaries.
     */
    public static void initializeGlider(Grid grid) throws InvalidPatternException {
        int mid = grid.getSize() / 2;  // Center position in the grid
        boolean[][] g = grid.getGrid();

        try {
            // Assigning Glider pattern relative to center
            g[mid][mid + 1] = true;
            g[mid + 1][mid + 2] = true;
            g[mid + 2][mid] = true;
            g[mid + 2][mid + 1] = true;
            g[mid + 2][mid + 2] = true;

            logger.info("Glider pattern initialized at center of grid.");

            grid.setGrid(g);  // Update the grid

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Error placing Glider pattern: {}", e.getMessage());
            throw new InvalidPatternException("Failed to place Glider in grid: " + e.getMessage());
        }
    }
}
