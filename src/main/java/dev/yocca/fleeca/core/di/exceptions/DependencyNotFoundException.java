package dev.yocca.fleeca.core.di.exceptions;

public class DependencyNotFoundException extends RuntimeException {
    public DependencyNotFoundException(String message) {
        super(message);
    }
}
