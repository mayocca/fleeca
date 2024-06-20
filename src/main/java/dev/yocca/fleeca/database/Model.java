package dev.yocca.fleeca.database;

public abstract class Model {
    public abstract String getTable();
    
    public String getKeyName() {
        return "id";
    }
}
