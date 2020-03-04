package ru.itmo.wp.model.domain;

import java.util.Date;

public class TemplateTalk {
    private long id;
    private String sourceUserLogin;
    private String targetUserLogin;
    private String text;
    private Date creationTime;

    public TemplateTalk(long id, String text, Date creationTime) {
       this.id = id;
       this.text = text;
       this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceUserLogin() {
        return sourceUserLogin;
    }

    public void setSourceUserLogin(String sourceUserLogin) {
        this.sourceUserLogin = sourceUserLogin;
    }

    public String getTargetUserLogin() {
        return targetUserLogin;
    }

    public void setTargetUserLogin(String targetUserLogin) {
        this.targetUserLogin = targetUserLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
