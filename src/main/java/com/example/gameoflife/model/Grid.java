package com.example.gameoflife.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a 2D square grid for Conway's Game of Life.
 * Each cell is either alive (true) or dead (false).
 */
public class Grid {

    private static final Logger logger = LoggerFactory.getLogger(Grid.class);

    private final int size;          // Size of the grid (size x size)
    private boolean[][] grid;        // 2D array representing the state of the universe

    /**
     * Constructs a Grid of specified size.
     *
     * @param size the dimension of the square grid (must be positive).
     * @throws IllegalArgumentException if size is not positive.
     */
    public Grid(int size) {
        if (size <= 0) {
            logger.error("Attempted to create a grid with non-positive size: {}", size);
            throw new IllegalArgumentException("Grid size must be positive.");
        }
        this.size = size;
        this.grid = new boolean[size][size];
        logger.info("Created a new {}x{} grid.", size, size);
    }

    /**
     * Gets the current state of the grid.
     *
     * @return 2D boolean array representing the grid.
     */
    public boolean[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid to a new state.
     *
     * @param newGrid a new grid of the same size.
     * @throws IllegalArgumentException if dimensions do not match the original grid.
     */
    public void setGrid(boolean[][] newGrid) {
        if (newGrid.length != size || newGrid[0].length != size) {
            logger.error("Grid size mismatch. Expected: {}x{}, Found: {}x{}",
                    size, size, newGrid.length, newGrid[0].length);
            throw new IllegalArgumentException("Grid size mismatch.");
        }
        this.grid = newGrid;
        logger.debug("Grid updated successfully.");
    }

    /**
     * Gets the size of the grid (number of rows/columns).
     *
     * @return size of the square grid.
     */
    public int getSize() {
        return size;
    }

    /**
     * Prints the grid to the console using Unicode characters.
     * Alive cells are shown as "⬛", dead cells as "⬜".
     */
    public void print() {
        logger.info("Printing current grid state:");
        for (boolean[] row : grid) {
            for (boolean cell : row) {
                System.out.print(cell ? "⬛" : "⬜"); // Use black square for alive, white square for dead
            }
            System.out.println();
        }
        System.out.println();
    }
}