package org.example.exception;

public class OperationUnknownException extends RuntimeException {
    public OperationUnknownException(String message) {
        super(message);
    }
}
