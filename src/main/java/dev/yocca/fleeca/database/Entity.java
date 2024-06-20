package dev.yocca.fleeca.database;

public abstract class Entity {
    public abstract String getTable();
    
    public String getKeyName() {
        return "id";
    }
}
