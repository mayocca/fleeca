package dev.yocca.fleeca.core.di.config;

import dev.yocca.fleeca.core.di.Container;

public class AppConfig {
    private final Container container;

    public AppConfig() {
        container = new Container();
        configureServices();
    }

    private void configureServices() {
        //
    }
}
