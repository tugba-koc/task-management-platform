package dev.tugba.taskapp.core.utilities.exceptions;

public class AlreadyExistsUserException extends RuntimeException {
    public AlreadyExistsUserException(String message) {
        super(message);
    }
}
