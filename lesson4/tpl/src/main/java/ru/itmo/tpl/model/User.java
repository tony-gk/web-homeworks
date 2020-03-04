package ru.itmo.tpl.model;

public class User {
    public enum Color {
        RED, GREEN, BLUE
    }

    private final long id;
    private final String handle;
    private final String name;
    private final Color color;


    public User(long id, String handle, String name, Color color) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }
}
