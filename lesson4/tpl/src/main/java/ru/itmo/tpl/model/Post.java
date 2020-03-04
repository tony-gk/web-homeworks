package ru.itmo.tpl.model;

public class Post {
    final private long user_id;
    final private String title;
    final private String text;
    final private long id;

    public Post(long id, long user_id, String title, String text) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
