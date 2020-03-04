package ru.itmo.wp.form;

import javax.validation.constraints.NotBlank;

public class NoticeForm {
    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
