package dev.yocca.fleeca.core.di.providers;

@FunctionalInterface
public interface Provider<T> {
    T get();
}
