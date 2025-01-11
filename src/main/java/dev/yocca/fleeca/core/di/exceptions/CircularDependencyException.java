package dev.yocca.fleeca.core.di.exceptions;

public class CircularDependencyException extends RuntimeException {
    public CircularDependencyException(String message) {
        super(message);
    }
}
