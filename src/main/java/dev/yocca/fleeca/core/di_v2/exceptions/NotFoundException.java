package dev.yocca.fleeca.core.di_v2.exceptions;

public class NotFoundException extends ContainerException {
    public NotFoundException(String message) {
        super(message, null);
    }
}
