package com.example.gameoflife.exception;

/**
 * Custom checked exception thrown when an invalid Game of Life pattern is provided.
 * This could be used when a pattern is null, out of bounds, or violates setup constraints.
 */
public class InvalidPatternException extends Exception {

    /**
     * Constructs a new InvalidPatternException with a detailed error message.
     *
     * @param message Detailed message explaining the cause of the exception.
     */
    public InvalidPatternException(String message) {
        super(message); // Call the parent Exception constructor
    }
}
