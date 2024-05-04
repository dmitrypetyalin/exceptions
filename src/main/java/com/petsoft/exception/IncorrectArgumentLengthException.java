package com.petsoft.exception;

/**
 * @author PetSoft
 * @date 04.05.2024 14:18
 */
public class IncorrectLengthException extends Exception {
    private final int length;

    public IncorrectLengthException(String message, int length) {
        super(message);
        this.length = length;
    }
}
