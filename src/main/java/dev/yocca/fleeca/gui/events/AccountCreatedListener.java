package dev.yocca.fleeca.gui.events;

import java.util.EventListener;

public interface AccountCreatedListener extends EventListener {
    void accountCreated(AccountCreatedEvent evt);
}
