package dev.yocca.fleeca.core.di;

import dev.yocca.fleeca.core.di.annotations.Inject;
import dev.yocca.fleeca.core.di.annotations.Singleton;
import dev.yocca.fleeca.core.di.exceptions.CircularDependencyException;
import dev.yocca.fleeca.core.di.exceptions.DependencyCreationException;
import dev.yocca.fleeca.core.di.exceptions.DependencyNotFoundException;
import dev.yocca.fleeca.core.di.providers.Provider;
import dev.yocca.fleeca.core.di.providers.SingletonProvider;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class Container {
    private final Map<Class<?>, Provider<?>> providers = new ConcurrentHashMap<>();
    private final Map<Class<?>, Object> singletons = new ConcurrentHashMap<>();
    private final Map<Class<?>, Constructor<?>> constructorCache = new ConcurrentHashMap<>();
    private final ThreadLocal<Set<Class<?>>> resolutionStack = ThreadLocal.withInitial(HashSet::new);

    public <T> void register(Class<T> type, T instance) {
        providers.put(type, new SingletonProvider<>(instance));
    }

    public <T> void register(Class<T> type, Provider<T> provider) {
        providers.put(type, provider);
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> type) {
        if (!resolutionStack.get().add(type)) {
            throw new CircularDependencyException(
                    "Circular dependency detected while resolving " + type.getName() +
                    ". Resolution stack: " + resolutionStack.get()
            );
        }

        try {
            if (singletons.containsKey(type)) {
                return (T) singletons.get(type);
            }

            Provider<?> provider = providers.get(type);
            if (provider != null) {
                T instance =  (T) provider.get();
                if (type.isAnnotationPresent(Singleton.class)) {
                    singletons.put(type, instance);
                }
                return instance;
            }

            return createInstance(type);
        } finally {
            resolutionStack.get().remove(type);

            if (resolutionStack.get().isEmpty()) {
                resolutionStack.remove();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T createInstance(Class<T> type) {
        Constructor<?> constructor = findInjectableConstructor(type);
        if (constructor == null) {
            throw new DependencyNotFoundException("No injectable constructor found for type: " + type.getName());
        }

        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = resolve(parameterTypes[i]);
        }

        try {
            constructor.setAccessible(true);
            T instance = (T) constructor.newInstance(parameters);

            if (constructor.isAnnotationPresent(Singleton.class)) {
                register(type, instance);
            }

            return instance;
        } catch (Exception e) {
            throw new DependencyCreationException("Failed to create instance of " + type.getName(), e);
        }
    }

    private Constructor<?> findInjectableConstructor(Class<?> type) {
        Constructor<?> cached = constructorCache.get(type);
        if (cached != null) {
            return cached;
        }

        Constructor<?> injectConstructor = null;
        Constructor<?> defaultConstructor = null;

        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                if (injectConstructor != null) {
                    throw new DependencyCreationException("Multiple @Inject constructors found in " + type.getName());
                }
                injectConstructor = constructor;
            } else if (constructor.getParameterCount() == 0) {
                defaultConstructor = constructor;
            }
        }

        Constructor<?> selected = injectConstructor != null ? injectConstructor : defaultConstructor;
        if (selected != null) {
            constructorCache.put(type, selected);
        }

        return selected;
    }

    public boolean contains(Class<?> type) {
        return providers.containsKey(type);
    }
}
