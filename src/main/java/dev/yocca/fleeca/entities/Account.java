package dev.yocca.fleeca.entities;

import dev.yocca.fleeca.database.Entity;

import java.time.LocalDateTime;

public class Account extends Entity {

    private int id;
    private String owner;
    private String alias;
    private double balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account(String owner, String alias) {
        this.owner = owner;
        this.alias = alias;
    }

    public Account(Integer id, String owner, String alias, Double balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.owner = owner;
        this.alias = alias;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String getTable() {
        return "accounts";
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
