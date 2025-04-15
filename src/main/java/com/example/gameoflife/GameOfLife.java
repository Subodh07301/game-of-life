package com.example.gameoflife;

import com.example.gameoflife.model.Grid;
import com.example.gameoflife.pattern.PatternInitializer;
import com.example.gameoflife.rules.LifeRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for the Conway's Game of Life simulation.
 * Initializes the grid, applies a starting pattern, and simulates generations.
 */
public class GameOfLife {

    private static final Logger logger = LoggerFactory.getLogger(GameOfLife.class);

    private static final int SIZE = 25;          // Size of the square grid
    private static final int GENERATIONS = 10;   // Number of generations to simulate
    private static final int DELAY_MS = 500;     // Delay between generations in milliseconds

    /**
     * Main method that runs the Game of Life simulation.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Create a new grid of specified size
            Grid grid = new Grid(SIZE);

            // Initialize the grid with a Glider pattern
            PatternInitializer.initializeGlider(grid);
            logger.info("Initialized grid with Glider pattern.");

            // Run the simulation for a set number of generations
            for (int generation = 0; generation < GENERATIONS; generation++) {
                logger.info("Generation: {}", generation);
                grid.print(); // Display current generation on the console

                // Generate and set the next generation of the grid
                grid.setGrid(LifeRules.nextGeneration(grid));

                // Add a small delay for better visual separation
                Thread.sleep(DELAY_MS);
            }

            logger.info("Simulation completed successfully.");

        } catch (Exception e) {
            logger.error("An error occurred during Game of Life simulation: ", e);
        }
    }
}
