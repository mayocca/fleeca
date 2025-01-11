package dev.yocca.fleeca.core.di.exceptions;

public class DependencyCreationException extends RuntimeException {
    public DependencyCreationException(String message) {
        super(message);
    }

    public DependencyCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
