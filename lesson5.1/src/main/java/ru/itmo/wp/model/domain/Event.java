package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Event extends Entity implements Serializable {
    public enum Type {
        ENTER, LOGOUT
    }

    private long userId;
    private Type type;

    public Event() {
    }

    public Event(long userId, Type type) {
        this.userId = userId;
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public Type getType() {
        return type;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
