package dev.tugba.taskapp.core.utilities.exceptions;

public class AccountCodeNotFoundException extends RuntimeException {
    public AccountCodeNotFoundException(String message) {
        super(message);
    }
}
