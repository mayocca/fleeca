package dev.yocca.fleeca.gui.events;

import dev.yocca.fleeca.entities.Account;

import java.util.EventObject;

public class AccountCreatedEvent extends EventObject {
    private Account account;

    public AccountCreatedEvent(Object source, Account account) {
        super(source);
    }

    public Account getAccount() {
        return account;
    }
}
